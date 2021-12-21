package com.xuzimian.globaldemo.common.basic.java.util.function;

import org.junit.Test;

import java.util.stream.Stream;

/**
 * @program: global-demo
 * @description: Stream Demo
 * @author: xzm
 * @create: 2019-09-03 17:25
 **/
public class StreamDemo {

    @Test
    public void test() {
        Stream.of(new String[]{"a", "b", "c"}).forEach(d -> System.out.print(d));

        System.out.println();

        Stream.of("a", "b", "c").forEach(d -> System.out.print(d));
    }
}
