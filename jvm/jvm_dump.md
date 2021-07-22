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
  ````sh
  kill -ABRT <pid> 
  ````
  来生成 Core dump. 不过需要注意的是, 使用这种方法只 有在当前 Terminal 下运行的 Java 程序才能生成 Core dump. 也就是说, 你必须在打开了 Core dump 的 Terminal 下运行 Java 程序, 这样 kill -ABRT <pid> 才会生成 Core dump. 如果你 Java 程序运行在一个没有打开 Core dump 的 Terminal 下, 那么即使你的 kill -ABRT <pid> 运行在打开了 Core dump 的 Terminal 下, 这时候 Core dump 也是不会生成的.
  Java 自带的 jstack 和 jmap 也可以用来分析 Core dump:
  ````sh
  jstack <executable> <core dump file>
  jmap <executable> <core dump file>
  ````
  这里的 <executable> 指的是你运行 Java 程序时使用的 java,.

- HeapDump 文件是关于内存的:
  HeapDump 文件是一个二进制文件，它保存了某一时刻 JVM 堆中对象使用情况，这种文件需要相应的工具进行分析，如 IBM Heap Analyzer 这类工具。这类文件最重要的作用就是分析系统中是否存在内存溢出的情况。
  可以使用自带的 jstack 生成 Thread dump:
  ````sh
  jstack <pid> >> thread.dump
  ````
  Thread dump 就是个文本文件格式, 直接打开查看就可以了.Intellij IDEA 提供 Stacktrace 的分析, 我们可以用它来分析 Thread dump, 这样可以方便的知道某个线程运行到哪里.

## JDK tools

JDK 的 bin 目彔下,包含了 java 命令及其他实用工具

- jps: 查看本机的 Java 中进程信息。
- jstack: 打印线程的栈信息,制作线程 Dump。
- jmap: 打印内存映射,制作堆 Dump。
- jstat: 性能监控工具。
- jhat: 内存分析工具。
- jconsole: 简易的可视化控制台。
- jvisualvm: 功能强大的控制台。

## Jprofile

JProfiler 是由 ej-technologies GmbH 公司开发的一款性能瓶颈分析工具。
