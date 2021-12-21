package com.xuzimian.globaldemo.common.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;

/**
 * Disruptor 是一个高性能的异步处理框架。
 * Disruptor 是 LMAX 在线交易平台的关键组成部分，LMAX平台使用该框架对订单处理速度能达到600万TPS，
 * 除金融领域之外，其他一般的应用中都可以用到Disruptor，它可以带来显著的性能提升。其实 Disruptor 与其说是一个框架，
 * 不如说是一种设计思路，这个设计思路对于存在“并发、缓冲区、生产者—消费者模型、事务处理”这些元素的程序来说，
 * Disruptor提出了一种大幅提升性能（TPS）的方案。
 * <p>
 * 考虑到在消息交换过程中，使用队列产生的延迟和基于 RAID 或 SSD 的磁盘系统操作处于同一数量级，
 * 同时在多线程的实现中传统方法（队列或者处理节点）容易引起冲突以及 CPU 底层设计的 mechanical sympathy 原则，
 * 具备一定的优化空间。LMAX 设计了一个环形数组，充分考虑 CPU 缓存可能的伪共享问题，为生产者配置可用buffer和消费者配置了单独的序列号，
 * 避免冲突，实现无锁化和 CAS，同时基于事件驱动模型和多种等待策略(WaitStrategy ), 充分利用 CPU 资源的高性能队列，
 * 与 JDK 的阻塞队列相比，极大提高了吞吐量。
 *
 * @program: global-demo
 * @description: 代码实现的功能：每10ms向disruptor中插入一个元素，消费者读取数据，并打印到终端。
 * @author: xzm
 * @create: 2019-05-30 13:36
 **/
public class DisruptorDemo {

    public static void main(String[] args) throws Exception {


        // 生产者的线程工厂
        ThreadFactory threadFactory = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "simpleThread");
            }
        };

        // RingBuffer生产工厂,初始化RingBuffer的时候使用
        EventFactory<Element> factory = new EventFactory<Element>() {
            @Override
            public Element newInstance() {
                return new Element();
            }
        };

        // 处理Event的handler
        EventHandler<Element> handler = new EventHandler<Element>() {
            @Override
            public void onEvent(Element element, long sequence, boolean endOfBatch) {
                System.out.println("Element: " + element.get());
            }
        };

        // 阻塞策略
        BlockingWaitStrategy strategy = new BlockingWaitStrategy();

        // 指定RingBuffer的大小
        int bufferSize = 16;

        // 创建disruptor，采用单生产者模式
        Disruptor<Element> disruptor = new Disruptor(factory, bufferSize, threadFactory, ProducerType.SINGLE, strategy);

        // 设置EventHandler
        disruptor.handleEventsWith(handler);

        // 启动disruptor的线程
        disruptor.start();

        RingBuffer<Element> ringBuffer = disruptor.getRingBuffer();

        for (int l = 0; true; l++) {
            // 获取下一个可用位置的下标
            long sequence = ringBuffer.next();
            try {
                // 返回可用位置的元素
                Element event = ringBuffer.get(sequence);
                // 设置该位置元素的值
                event.set(l);
            } finally {
                ringBuffer.publish(sequence);
            }
            Thread.sleep(10);
        }
    }

    // 队列中的元素
    static class Element {

        private int value;

        public int get() {
            return value;
        }

        public void set(int value) {
            this.value = value;
        }

    }
}
