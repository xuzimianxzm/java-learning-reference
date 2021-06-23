package com.xuzimian.java.learning.common.exception.thread;

/**
 * https://www.jianshu.com/p/9811a1089783 -------:
 *
 * Thread interruption
 * 每一个线程都拥有一个 interrupted status，初始值是 false。当一个线程被另一个线程调用 Thread.interrupt() 而中止时，jvm 默默地做了一下事情：
 *
 * 1. 如果被中止线程正阻塞在 Thread.sleep()，Thread.join()，或者 Object.wait() 这样的阻塞方法上，那么它将 unblock 并抛出 InterruptedException 异常。
 *
 * 2. 否则 interrupt() 方法只是将 interrupted status 置为 true。运行在这个线程的阻塞方法之后的代码块，就可以通过 Thread.isInterrupted() 来检查该线程是否已被 interrupted，
 * 来决定执行哪部分代码逻辑（因为程序运行到这部分代码块可能是因为被 interrupted 而提前往下执行到了这里，也可能是因为阻塞方法被成功执行完了而往下执行到了这里）
 * 所以本质上，当一个线程通过调用 Thread.interrupt() 方法去中止另一个线程时，另一个线程并不是马上中止（主要看线程对 interruption 的处理，即这个线程意识到 interruption 了，
 * 是否提前退出 (return early) 取决于它自己）。阻塞方法通过捕获 InterruptionException 异常来意识到 interruption 事件，而非阻塞的任务则通过轮询 interrupted status 来
 * 意识是 interruption 事件。
 */
public class ThreadInterruptDemo {

    public static void main(String[] args) {
        System.out.println("初始中断状态：" + Thread.currentThread().isInterrupted());
        System.out.println("终止当前线程...");
        Thread.currentThread().interrupt();
        System.out.println("执行完interrupt方法后，中断状态：" + Thread.currentThread().isInterrupted());

        System.out.println();
        System.out.println("首次调用interrupted方法返回结果：" + Thread.currentThread().interrupted());
        System.out.println("此时中断状态：" + Thread.currentThread().isInterrupted());
        System.out.println("第二次调用interrupted方法返回结果：" + Thread.currentThread().interrupted());
        System.out.println("此时中断状态：" + Thread.currentThread().isInterrupted());

        System.out.println();
        System.out.println("再次调用interrupt");
        Thread.currentThread().interrupt();
        System.out.println("再调用interrupted方法返回结果：" + Thread.currentThread().interrupted());
        System.out.println("此时中断状态：" + Thread.currentThread().isInterrupted());
    }
}
