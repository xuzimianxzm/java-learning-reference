package com.xuzimian.globaldemo.common.basic;

import org.junit.Test;

/**
 * @program: global-demo
 * @description: ThreadLocal Demo
 * @author: xzm
 * @create: 2019-05-29 12:04
 **/
public class ThreadLocalDemo {


    /**
     * ThreadLocal 如需存入多个对象到线程中，就必须创建对应个数的 ThreadLocal，
     * 因为ThreadLocal 是以 ThreadLocal 实例(this) 做key 存入 当前线程Thread
     * 的ThreadLocalMap 对象中( ThreadLocalMa,Entity 数组)。
     */
    @Test
    public void testSetTwo(){
        ThreadLocal<String> userthreadLocal1=new ThreadLocal<>();
        ThreadLocal<String> userthreadLocal2=new ThreadLocal<>();

        userthreadLocal1.set("xzm");
        userthreadLocal2.set("xzm1");

        System.out.println(userthreadLocal1.get());
        System.out.println(userthreadLocal2.get());
    }

}
