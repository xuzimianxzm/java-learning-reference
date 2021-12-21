### 背景
Disruptor是英国外汇交易公司LMAX开发的一个高性能队列，研发的初衷是解决内存队列的延迟问题（在性能测试中发现竟然与I/O操作处于同样的数量级）。基于Disruptor开发的系统单线程能支撑每秒600万订单，2010年在QCon演讲后，获得了业界关注。2011年，企业应用软件专家Martin Fowler专门撰写长文介绍。同年它还获得了Oracle官方的Duke大奖。

目前，包括Apache Storm、Camel、Log4j 2在内的很多知名项目都应用了Disruptor以获取高性能。在美团点评技术团队它也有不少应用，有的项目架构借鉴了它的设计机制。本文从实战角度剖析了Disruptor的实现原理。

需要特别指出的是，这里所说的队列是系统内部的内存队列，而不是Kafka这样的分布式队列。另外，本文所描述的Disruptor特性限于3.3.4。

### 加锁
现实编程过程中，加锁通常会严重地影响性能。线程会因为竞争不到锁而被挂起，等锁被释放的时候，线程又会被恢复，这个过程中存在着很大的开销，并且通常会有较长时间的中断，因为当一个线程正在等待锁时，它不能做任何其他事情。如果一个线程在持有锁的情况下被延迟执行，例如发生了缺页错误、调度延迟或者其它类似情况，那么所有需要这个锁的线程都无法执行下去。如果被阻塞线程的优先级较高，而持有锁的线程优先级较低，就会发生优先级反转。

Disruptor论文中讲述了一个实验：

这个测试程序调用了一个函数，该函数会对一个64位的计数器循环自增5亿次。
机器环境：2.4G 6核
运算： 64位的计数器累加5亿次
MethodTime (ms)Single thread300Single thread with CAS5,700Single thread with lock10,000Single thread with volatile write4,700Two threads with CAS30,000Two threads with lock224,000
CAS操作比单线程无锁慢了1个数量级；有锁且多线程并发的情况下，速度比单线程无锁慢3个数量级。可见无锁速度最快。

单线程情况下，不加锁的性能 > CAS操作的性能 > 加锁的性能。

在多线程情况下，为了保证线程安全，必须使用CAS或锁，这种情况下，CAS的性能超过锁的性能，前者大约是后者的8倍。

综上可知，加锁的性能是最差的。

### 伪共享
什么是共享
下图是计算的基本结构。L1、L2、L3分别表示一级缓存、二级缓存、三级缓存，越靠近CPU的缓存，速度越快，容量也越小。所以L1缓存很小但很快，并且紧靠着在使用它的CPU内核；L2大一些，也慢一些，并且仍然只能被一个单独的CPU核使用；L3更大、更慢，并且被单个插槽上的所有CPU核共享；最后是主存，由全部插槽上的所有CPU核共享。

![avatar](https://gitee.com/xuzimian/Image/raw/master/GlobalDemo/FalseSharing.png)

当CPU执行运算的时候，它先去L1查找所需的数据、再去L2、然后是L3，如果最后这些缓存中都没有，所需的数据就要去主内存拿。走得越远，运算耗费的时间就越长。所以如果你在做一些很频繁的事，你要尽量确保数据在L1缓存中。

另外，线程之间共享一份数据的时候，需要一个线程把数据写回主存，而另一个线程访问主存中相应的数据。

下面是从CPU访问不同层级数据的时间概念:

从CPU到大约需要的CPU周期大约需要的时间主存约60-80nsQPI 总线传输(between sockets, not drawn)约20nsL3 cache约40-45 cycles约15nsL2 cache约10 cycles约3nsL1 cache约3-4 cycles约1ns寄存器1 cycle
可见CPU读取主存中的数据会比从L1中读取慢了近2个数量级。

### 缓存行
Cache是由很多个cache line组成的。每个cache line通常是64字节，并且它有效地引用主内存中的一块儿地址。一个Java的long类型变量是8字节，因此在一个缓存行中可以存8个long类型的变量。

CPU每次从主存中拉取数据时，会把相邻的数据也存入同一个cache line。

在访问一个long数组的时候，如果数组中的一个值被加载到缓存中，它会自动加载另外7个。因此你能非常快的遍历这个数组。事实上，你可以非常快速的遍历在连续内存块中分配的任意数据结构。

### Disruptor的设计方案
Disruptor通过以下设计来解决队列速度慢的问题：

> *  环形数组结构
为了避免垃圾回收，采用数组而非链表。同时，数组对处理器的缓存机制更加友好。

> *  元素位置定位
数组长度2^n，通过位运算，加快定位的速度。下标采取递增的形式。不用担心index溢出的问题。index是long类型，即使100万QPS的处理速度，也需要30万年才能用完。

> *  无锁设计
每个生产者或者消费者线程，会先申请可以操作的元素在数组中的位置，申请到之后，直接在该位置写入或者读取数据。

下面忽略数组的环形结构，介绍一下如何实现无锁设计。整个过程通过原子变量CAS，保证操作的线程安全。

### 生产者和消费者
Disruptor通过环形数组结构来解决队列速度慢的问题，那具体针对生产者和消费者，它是如何保证数据读写一致性的呢？

###  一个生产者写数据
生产者单线程写数据的流程比较简单： 
> 1. 申请写入m个元素； 
> 2. 若是有m个元素可以写入，则返回最大的序列号。这儿主要判断是否会覆盖未读的元素； 
> 3. 若是返回的正确，则生产者开始写入元素。

![avatar](https://gitee.com/xuzimian/Image/raw/master/GlobalDemo/single_producer.png)
###  多个生产者
多个生产者的情况下，会遇到“如何防止多个线程重复写同一个元素”的问题。Disruptor的解决方法是，每个线程获取不同的一段数组空间进行操作。这个通过CAS很容易达到。只需要在分配元素的时候，通过CAS判断一下这段空间是否已经分配出去即可。

但是会遇到一个新问题：如何防止读取的时候，读到还未写的元素。Disruptor在多个生产者的情况下，引入了一个与Ring Buffer大小相同的buffer：available Buffer。当某个位置写入成功的时候，便把availble Buffer相应的位置置位，标记为写入成功。读取的时候，会遍历available Buffer，来判断元素是否已经就绪。

下面分读数据和写数据两种情况介绍。

#### 读数据
生产者多线程写入的情况会复杂很多：
> 1. 申请读取到序号n； 
> 2. 若writer cursor >= n，这时仍然无法确定连续可读的最大下标。从reader cursor开始读取available Buffer，一直查到第一个不可用的元素，然后返回最大连续可读元素的位置； 
> 3. 消费者读取元素。

如下图所示，读线程读到下标为2的元素，三个线程Writer1/Writer2/Writer3正在向RingBuffer相应位置写数据，写线程被分配到的最大元素下标是11。

读线程申请读取到下标从3到11的元素，判断writer cursor>=11。然后开始读取availableBuffer，从3开始，往后读取，发现下标为7的元素没有生产成功，于是WaitFor(11)返回6。

然后，消费者读取下标从3到6共计4个元素。
![avatar](https://gitee.com/xuzimian/Image/raw/master/GlobalDemo/reader_data_by_many_producer.jpg)

#### 写数据
多个生产者写入的时候： 1. 申请写入m个元素； 2. 若是有m个元素可以写入，则返回最大的序列号。每个生产者会被分配一段独享的空间； 3. 生产者写入元素，写入元素的同时设置available Buffer里面相应的位置，以标记自己哪些位置是已经写入成功的。

如下图所示，Writer1和Writer2两个线程写入数组，都申请可写的数组空间。Writer1被分配了下标3到下表5的空间，Writer2被分配了下标6到下标9的空间。

Writer1写入下标3位置的元素，同时把available Buffer相应位置置位，标记已经写入成功，往后移一位，开始写下标4位置的元素。Writer2同样的方式。最终都写入完成。

![avatar](https://gitee.com/xuzimian/Image/raw/master/GlobalDemo/write_data_by_many_producer.jpg)

防止不同生产者对同一段空间写入的代码，如下所示：

``` java
public long tryNext(int n) throws InsufficientCapacityException
{
    if (n < 1)
    {
        throw new IllegalArgumentException("n must be > 0");
    }

    long current;
    long next;

    do
    {
        current = cursor.get();
        next = current + n;

        if (!hasAvailableCapacity(gatingSequences, n, current))
        {
            throw InsufficientCapacityException.INSTANCE;
        }
    }
    while (!cursor.compareAndSet(current, next));

    return next;
}
```
通过do/while循环的条件cursor.compareAndSet(current, next)，来判断每次申请的空间是否已经被其他生产者占据。假如已经被占据，该函数会返回失败，While循环重新执行，申请写入空间。


### 总结
Disruptor的高性能一方面是在于它没有用很重的锁，仅仅通过CPU的CAS就保证了操作的原子性；另一方面是在于它的数据结构RingBuffer（也包括Available）和Cursor的设计巧妙；当然还有它的等待策略、线程池等等。
