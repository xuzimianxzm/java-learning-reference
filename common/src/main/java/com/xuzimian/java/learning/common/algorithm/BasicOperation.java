package com.xuzimian.globaldemo.common.algorithm;

import org.junit.jupiter.api.Test;

/**
 * @program: global-demo
 * @description: 基础运算测试
 * @author: xzm
 * @create: 2019-08-26 10:05
 **/
public class BasicOperation {

    /**
     * 测试取余运算
     */
    @Test
    public void testRemainderOperation() {
        System.out.println(" 0 % 10 : " + (0 % 10));
        System.out.println(" 1 % 10 : " + (1 % 10));
        System.out.println(" 2 % 10 : " + (2 % 10));
        System.out.println(" 10 % 10 : " + (10 % 10));
        System.out.println(" 11 % 10 : " + (11 % 10));
        System.out.println(" 20 % 10 : " + (20 % 10));
        System.out.println(" 21 % 10 : " + (21 % 10));
        System.out.println(" 29 % 10 : " + (29 % 10));
    }

    /**
     * 测试除法运算
     */
    @Test
    public void testDivisionOperation() {
        System.out.println(" 10 / 1000 : " + (10 / 1000));
        System.out.println(" 11 / 1000 : " + (11 / 1000));
        System.out.println(" 12 / 1000 : " + (12 / 1000));
        System.out.println(" 1000 / 1000 : " + (1000 / 100010));
        System.out.println(" 1001 / 1000 : " + (1001 / 1000));
        System.out.println(" 2001 / 1000 : " + (2001 / 1000));
        System.out.println(" 3110 / 1000 : " + (3110 / 1000));
        System.out.println(" 3210 / 1000 : " + (3210 / 1000));
        System.out.println(" 321000 / 1000 : " + (321000 / 1000));
        System.out.println(" 321001 / 1000 : " + (321001 / 1000));
    }
}
