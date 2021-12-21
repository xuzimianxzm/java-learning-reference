package com.xuzimian.globaldemo.common.basic.grammar;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @program: global-demo
 * @description: 基础语法demo
 * @author: xzm
 * @create: 2019-03-15 11:21
 **/
public class TernaryExpressionDemo {

    @Test
    public void testIntegerFor() {
        Integer i = 630;

        Integer i2 = i > 0 ? i / 1000 : i;
        System.out.println(i2);
    }


    /**
     * 测试三元表达式返回两种（实际）类型，
     * 注意：上溯造型为根类型后，编译没问题，但是如果进行类型强转的化就会产生问题。
     * 因为实际类型会根据运行时参数条件而不一致
     */
    @Test
    public void testTwoClassTypeForThreeElementExpression() {
        int i = 0;
        Object obj1 = i > 2 ? 0 : BigDecimal.valueOf(i);
        Object obj2 = i < 2 ? 0 : BigDecimal.valueOf(i);
        System.out.println(obj1.getClass());
        System.out.println(obj2.getClass());

        BigDecimal d1 = (BigDecimal) obj1;
        BigDecimal d2 = (BigDecimal) obj2;

    }

    @Test
    public void testCastForNull() {
        Object o1 = "强转null对象类型";
        Object o2 = null;
        System.out.println((String) o1);
        System.out.println((String) o2);
    }
}
