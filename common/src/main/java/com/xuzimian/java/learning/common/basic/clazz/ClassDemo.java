package com.xuzimian.globaldemo.common.basic.clazz;

import org.junit.Test;

import java.sql.Date;

/**
 * @program: global-demo
 * @description: Class对象的方法测试
 * @author: xzm
 * @create: 2019-05-28 11:26
 **/
public class ClassDemo {

    /**
     * 判定此 Class 对象所表示的类或接口与指定的 Class 参数所表示的类或接口是否相同，或是否是其超类或超接口。
     * 如果是则返回 true；否则返回 false。如果该 Class 表示一个基本类型，且指定的 Class 参数正是该 Class 对象，
     * 则该方法返回 true；否则返回 false。
     */
    @Test
    public void testIsAssignableFrom() {
        Date date = new Date(2001, 1, 3);
        System.out.println(java.util.Date.class.isAssignableFrom(date.getClass()));
    }

    /**
     * 测试class的getResource 资源路径
     */
    @Test
    public void testGetResource() {
        System.out.println(this.getClass().getResource(""));
        System.out.println(this.getClass().getResource("/"));
        System.out.println(this.getClass().getResource("./"));
        System.out.println(this.getClass().getResource("Order.xml"));
        System.out.println(this.getClass().getResource("/Order.xml"));
        System.out.println(this.getClass().getResource("./Order.xml"));

        System.out.println(this.getClass().getResource("/config/env.propertites"));

    }

}
