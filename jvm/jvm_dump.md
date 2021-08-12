## Dump

- Dump: 就是对程序运行时内存上的信息进行转储, 让我们可以查看程序当时的运行情况. Dump 对于调优和排错是非常有用的工具.

- heap dump： Java 运行时对象分配在堆内存上, Heap dump 就是对堆内存进行转储。heap dump 文件是一个二进制文件，它保存了某一时刻 JVM 堆中对象使用情况，是指定时刻的 Java 堆栈的快照，是一种镜像文件。

- thread dump: Thread dump 转储的是线程相关的内存数据 (例如该线程的调用栈)。Thread dump 文件记录了当时 JVM 中线程运行的情况，Thread dumps 能帮助我们判断 CPU 峰值、死锁、内存异常、应用反应迟钝、响应时间变长和其他系统问题。 Thread dump 有时候也被称为 javacore。

- Core Dump:上面提到的 Heap dump 和 Thread dump 都是和 Java 直接相关的, Core dump 则是操作系统提供的, 所有程序在意外退出时, 操作系统都可以生成 Core dump.

### JavaCore 和 Heap Dump 的区别

- JavaCore 是关于 CPU 的:
  JavaCore 文件主要保存的是 Java 应用各线程在某一时刻的运行的位置，即 JVM 执行到哪一个类、哪一个方法、哪一个行上。它是一个文本文件，打开后可以看到每一个线程的执行栈，以 stack trace 的显示。通过对 JavaCore 文件的分析可以得到应用是否“卡”在某一点上，即在某一点运行的时间太长，例如数据库查询，长期得不到响应，最终导致系统崩溃等情况。

  ```sh
  # 如果你用的是 bash
  ulimit -c unlimited
  # or 如果你像我一样用的是 zsh
  limit coredumpsize unlimited
  ```

  ulimit/limit 是设置 dump 的大小的, 默认为 0 也就是不 dump. 可以使用下面的命令来查看当前设置的大小:确认打开后, 我们可以使用

  ```sh
  kill -ABRT <pid>
  ```

  来生成 Core dump. 不过需要注意的是, 使用这种方法只 有在当前 Terminal 下运行的 Java 程序才能生成 Core dump. 也就是说, 你必须在打开了 Core dump 的 Terminal 下运行 Java 程序, 这样 kill -ABRT <pid> 才会生成 Core dump. 如果你 Java 程序运行在一个没有打开 Core dump 的 Terminal 下, 那么即使你的 kill -ABRT <pid> 运行在打开了 Core dump 的 Terminal 下, 这时候 Core dump 也是不会生成的.
  Java 自带的 jstack 和 jmap 也可以用来分析 Core dump:

  ```sh
  jstack <executable> <core dump file>
  jmap <executable> <core dump file>
  ```

  这里的 <executable> 指的是你运行 Java 程序时使用的 java,.

- HeapDump 文件是关于内存的:
  HeapDump 文件是一个二进制文件，它保存了某一时刻 JVM 堆中对象使用情况，这种文件需要相应的工具进行分析，如 IBM Heap Analyzer 这类工具。这类文件最重要的作用就是分析系统中是否存在内存溢出的情况。
  可以使用自带的 jstack 生成 Thread dump:
  ```sh
  jstack <pid> >> thread.dump
  ```
  Thread dump 就是个文本文件格式, 直接打开查看就可以了.Intellij IDEA 提供 Stacktrace 的分析, 我们可以用它来分析 Thread dump, 这样可以方便的知道某个线程运行到哪里.

## Java Dump tools

JDK 的 bin 目彔下,包含了 java 命令及其他实用工具

- jps: 查看本机的 Java 中进程信息。

- jstack: 是一个抓取 thread dump 文件的有效的命令行工具，它位于 JDK 目录里的 bin 文件夹下（JDK_HOME\bin），以下是抓取 dump 文件的命令：
  提供最快最简单的方法来捕获线程转储。 但是，从 Java 8 开始可以使用更好的替代方案

  ```sh
  jstack -l  <pid> > <file-path>
  ```

- Kill -3: 有一部分生产环境的机器只包含 JRE 环境，因此就不能使用 jstack 工具了，在这种情况下，我们可以使用 kill -3 的方式。当使用 kill -3 生成 dump 文件时，
  dump 文件会被输出到标准错误流。假如你的应用运行在 tomcat 上，dump 内容将被发送到<TOMCAT_HOME>/logs/catalina.out 文件里。

- jmap: 命令是 JDK 提供的用于生成堆内存信息的工具,制作堆 Dump。
  ```sh
  jmap -dump:live,format=b,file=heap.hprof <pid>
  ```
- jcmd: jcmd 是一个非常完整的工具，它通过向 JVM 发送命令请求来工作。我们必须在运行 Java 进程的同一台机器上使用它。它的许多命令之一是 GC.heap_dump. 我们只需指定进程的 pid 和输出文件路径，就可以使用它获取堆转储：

  ```sh
  jcmd <pid> GC.heap_dump <file-path>
  ```

  推荐用于 Java 8 及更高版本。 一个有多种用途的工具——捕获线程转储 (jstack)、堆转储 (jmap)、系统属性和命令行参数 (jinfo)

- jstat: 性能监控工具。

- jhat: 内存分析工具,是 JDK 自带的用于分析 JVM Heap Dump 文件的工具，使用下面的命令可以将堆文件的分析结果以 HTML 网页的形式进行展示：
  ```sh
  jhat <heap-dump-file>
  ```
- jconsole: 简易的可视化控制台,是 JDK 提供的一个基于 GUI 查看 JVM 系统信息的工具，既可以管理本地的 JVM，也可以管理远程的 JVM

- JVisualVM：Java VisualVM 是一个可以提供 JVM 信息的图形界面工具。它位于 JDK_HOME\bin\jvisualvm.exe 文件里。从 JDK6 Update7 开始，它被包含进 JDK 里。这个工具可以从本地或者远程运行的 JVM 里抓取 dump 文件。

- JMC: Java Mission Control (JMC) 是一个能从本地或生产环境中收集和分析数据的工具，从 Oracle JDK 7 Update 40 开始，它被包含进 JDK 里，它可以从 JVM 里生成 dump 文件。JMC 位于 JDK_HOME\bin\jmc.exe 文件里：运行该工具之后，你可以看到运行在本地的 Java 进程，它也可以连接到远程机器。

- ThreadMXBean: 从 JDK 1.5 开始，ThreadMXBean 被引入。这是 JVM 的管理接口，使用这个接口你仅需要少量的代码就能生成 dump 文件，以下是使用 ThreadMXBean 生成 dump 文件的主要实现：

  ```java
  public void  dumpThreadDump() {
   ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
   for (ThreadInfo ti : threadMxBean.dumpAllThreads(true, true)) {
       System.out.print(ti.toString());
   }
  }
  ```

- APM Tool – App Dynamics: 一些应用性能监控工具提供了生成 dump 文件的功能。

- Eclipse Memory Analyzer(MAT): 是 Eclipse 提供的一款用于 Heap Dump 分析的工具，用来辅助发现内存泄漏减少内存占用，从数以百万计的对象中快速计算出对象的 Retained Size，查看并自动生成一个 Leak Suspect（内存泄露可疑点）报表。

- JProfiler: 是由 ej-technologies GmbH 公司开发的一款性能瓶颈分析工具。是收费工具。

## 为何会内存溢出

1)JVM 内存过小，

2)程序不严密，

3)产生过多的垃圾无法回收。

## Out Of Memory

```java
//Dump;
public class Test5 {
    Byte[] array = new Byte[1024*1024];//1MB

    public static void main(String[] args) {
        ArrayList<Test5> list = new ArrayList<>();
        int count = 0;

        //Throwable
          //Exception
          //Error
        try {
            while (true) {
                list.add(new Test5());
                count = count +1;
            }
        }catch (Error e){
            System.out.println(count);
            e.printStackTrace();
        }
    }
}
```

java.lang.OutOfMemoryError，由于堆内存溢出是 Error，不是 Exception，需要用 Error 来捕获，在实际开发中有时很难检查出错误，需要使用工具来分析。

先配置 Java 虚拟机，在 VM Options 中添加配置:

```sh
-Xms1m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError
```

而如果不指定选项“XX:HeapDumpPath”，则在当前目录下生成 dump 文件。
