package com.xuzimian.globaldemo.common.basic.java.util;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap 中保存的值是有顺序的，还可以自动删除最前面保存的值。
 * @program: global-demo
 * @description: LinkedHashMapDemo
 * @author: xzm
 * @create: 2019-06-10 14:42
 **/
public class LinkedHashMapDemo {

    /**
     * LinkedHashMap 中有一个 protected 级别的 removeEldestEntry方法，如果方法返回true,
     * Map中最前面添加的内容将被删除，它是在添加属性的put或putAll方法被调用后自动调用的。
     * 这个功能主要是用在缓存中，用来限定缓存的最大数量，以防止缓存无线的增长。当新的值添加后，
     * 如果缓存达到了上线，最开头的值就会被删除。
     *
     * 但是要实现这以功能需要覆盖removeEldestEntry方法，当这个方法返回true时就标识到达了上限，
     * 返回false就表示未到达上限。
     */
    @Test
    public void testRemoveEldestEntry() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>() {

            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                //限定Map中的最大元素数量不超过3个
                if (size() > 3) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        linkedHashMap.put("x1", "x1");
        linkedHashMap.put("x2", "x2");
        linkedHashMap.put("x3", "x3");

        System.out.println(linkedHashMap);

        linkedHashMap.put("x4", "x4");

        System.out.println(linkedHashMap);
    }


}
