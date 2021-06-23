package com.xuzimian.java.learning.common.concurrent;

import java.util.concurrent.CountDownLatch;

public class VolatileReferenceObjectDemo {
    private final static int threadCount = 1000;
    private static volatile Object referenceObject;
    private static volatile int number = 0;


    public static void main(String[] args) throws InterruptedException {
        testObject();
//        testNumber();
//        testSync();
    }

    public static void testObject() throws InterruptedException {
        CountDownLatch startGate = new CountDownLatch(threadCount);

        var runnable = new Runnable() {
            @Override
            public void run() {
                startGate.countDown();
                if (referenceObject == null) {
                    System.out.println("object :" + referenceObject);
                    referenceObject = "init";
                } else {
                    System.out.println("else object :" + referenceObject);
                }
            }
        };

        for (int n = 0; n <= threadCount; n++) {
            new Thread(runnable).start();
        }
        startGate.await();
    }

    public static void testNumber() throws InterruptedException {
        CountDownLatch startGate = new CountDownLatch(threadCount);

        var runnable = new Runnable() {
            @Override
            public void run() {
                startGate.countDown();
                if (number == 0) {
                    System.out.println(" number :" + number);
                    number = 1;
                } else {
                    System.out.println("else number :" + number);
                }
            }
        };

        for (int n = 0; n <= threadCount; n++) {
            new Thread(runnable).start();
        }
        startGate.await();
    }

    public static void testSync() throws InterruptedException {
        CountDownLatch startGate = new CountDownLatch(threadCount);

        var runnable = new Runnable() {
            @Override
            public void run() {
                startGate.countDown();
                synchronized (VolatileReferenceObjectDemo.class) {
                    if (number == 0) {
                        System.out.println("Sync number :" + number);
                        number = 1;
                    } else {
                        System.out.println("Sync else number :" + number);
                    }
                }
            }
        };

        for (int n = 0; n <= threadCount; n++) {
            new Thread(runnable).start();
        }
        startGate.await();
    }
}
