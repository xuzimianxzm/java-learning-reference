package com.xuzimian.globaldemo.common.disruptor.principle;

/**
 * 伪共享:
 *
 * 针对处在同一个缓存行内的数据，假设线程1修改了其中的一个数据a后，线程2想要读取数据a，
 * 因为a已经被修改了，因此缓存行失效，需要从主内存中重新读取。
 * 这种无法充分使用缓存行特性的现象，称为伪共享。
 * 当多线程修改互相独立的变量时，如果这些变量共享同一个缓存行，就会无意中影响彼此的性能，这就是伪共享。
 *
 * @program: global-demo
 * @description: 伪共享:对于伪共享，一般的解决方案是，增大数组元素的间隔使得由不同线程存取的元素位于不同的缓存行上，以空间换时间。
 * @author: xzm
 * @create: 2019-05-30 11:18
 **/
public class FalseSharingDemo implements Runnable{
    public final static long ITERATIONS = 500L * 1000L * 100L;
    private int arrayIndex = 0;

    private static ValueNoPadding[] longsNoPaddingArr;

    private static ValuePadding[] longsPaddingArr;

    private boolean padding;

    public FalseSharingDemo(final int arrayIndex, boolean padding) {
        this.arrayIndex = arrayIndex;
        this.padding = padding;
    }

    public static void main(final String[] args) throws Exception {
        for(int i=1;i<10;i++){
            System.gc();
            final long start = System.currentTimeMillis();
            runTestNoPadding(i);
            System.out.println("NoPadding Thread num "+i+" duration = " + (System.currentTimeMillis() - start));
        }

        for(int i=1;i<10;i++){
            System.gc();
            final long start = System.currentTimeMillis();
            runTestPadding(i);
            System.out.println("Padding Thread num "+i+" duration = " + (System.currentTimeMillis() - start));
        }

    }

    private static void runTestPadding(int NUM_THREADS) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        longsPaddingArr = new ValuePadding[NUM_THREADS];
        for (int i = 0; i < longsPaddingArr.length; i++) {
            longsPaddingArr[i] = new ValuePadding();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharingDemo(i, true));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    private static void runTestNoPadding(int NUM_THREADS) throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];
        longsNoPaddingArr = new ValueNoPadding[NUM_THREADS];
        for (int i = 0; i < longsNoPaddingArr.length; i++) {
            longsNoPaddingArr[i] = new ValueNoPadding();
        }
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharingDemo(i, false));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            if (padding) {
                longsPaddingArr[arrayIndex].value = 0L;
            } else {
                longsNoPaddingArr[arrayIndex].value = 0L;
            }
        }
    }

    public final static class ValuePadding {
        protected long p1, p2, p3, p4, p5, p6, p7;
        protected volatile long value = 0L;
        protected long p9, p10, p11, p12, p13, p14;
        protected long p15;
    }
    public final static class ValueNoPadding {
        // protected long p1, p2, p3, p4, p5, p6, p7;
        protected volatile long value = 0L;
        // protected long p9, p10, p11, p12, p13, p14, p15;
    }
}