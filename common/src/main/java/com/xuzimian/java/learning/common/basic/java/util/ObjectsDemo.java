package com.xuzimian.globaldemo.common.basic.java.util;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;
import java.util.Objects;

/**
 * @program: global-demo
 * @description: 测试java.util.Objects 类
 * @author: xzm
 * @create: 2019-02-22 11:17
 **/
public class ObjectsDemo {

    @Test
    public void testEquals() {
        Object obj1 = "1";
        Object obj2 = "1";
        System.out.println(Objects.equals(obj1, obj2));


        String str1 = "1";
        String str2 = "1";
        System.out.println(Objects.equals(str1, str2));

        int i=0;
        Integer integer=null;
        System.out.println(Objects.equals(i, integer));

        System.out.println(Objects.equals(i, integer));


        integer=0;
        System.out.println(Objects.equals(i, integer));

    }

    @Test
    public void testEqualsStringAndInt() {
        Map<String,Object> map= Maps.newHashMap();
        map.put("1","1");

        System.out.println("String to Object(String) :"+ Objects.equals("1", map.get("1")));


        map.put("2",1);

        System.out.println("String to Object(int) :"+ Objects.equals("1", map.get("2")));
        System.out.println("String to Object(String.valueOf) :"+ Objects.equals("1",String.valueOf( map.get("2"))));
    }

    @Test
    public void testEqualsChar() {
        Object obj1 = '1';
        char char2 = '1';
        System.out.println(Objects.equals(obj1, char2));

    }
}
