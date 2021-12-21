package com.xuzimian.globaldemo.common.basic.java.util.function;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * @program: global-demo
 * @description: Lambda 表达式Consumer的使用演示
 * @author: xzm
 * @create: 2019-02-12 14:26
 **/
public class ConsumerDemo {

    @Test
    public void testConsumer() {
        //方式1：
        Consumer<String> consumerStr = s -> {
            System.out.println(s);
        };
        consumerStr.accept("Hello World.");

        //方式2：
        consumer("你好",d-> System.out.println(d));
    }

    private void consumer(String say,Consumer<String> doSomething) {
        //doSomething...
        doSomething.accept(say);
    }


}
