package com.xuzimian.globaldemo.common.basic.java.util.stream;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.jupiter.api.Test;

public class ListToStreamDemo {


    /**
     * 测试Java8 的 Stream 将集合按某个属性分组。
     */
    @Test
    public void testListStreamGroupBy() {
        List<Map<String, String>> list = getMaps();
        Map<String,List<Map<String, String>>> groupMap = list.stream().collect(Collectors.groupingBy((Map<String, String> d) -> d.get("type"), Collectors.toList()));

        for(String key : groupMap.keySet()){
            System.out.println("key: "+key);
            System.out.println(groupMap.get(key));
        }

    }

    private List<Map<String, String>> getMaps() {
        List<Map<String, String>> list = Lists.newArrayList();
         Random random=new Random();
        Map<String, String> map;
        for (int n = 1; n <= 12; n++) {
            map = Maps.newHashMap();
            map.put("type",String.valueOf(random.nextInt(5)));
            map.put("name","xzm"+ n);
            map.put("age", String.valueOf(random.nextInt(100)));
            list.add(map);
        }

        return list;
    }


    /**
     * 测试foreach循环
     */
    @Test
    public void testForEach() {
        List<String> list = Lists.newArrayList();
        list.add("中文..");
        list.add("中文..");
        list.add("中文..");
        list.add("中文..");
        list.add("中文..");
        list.add("中文..");
        list.forEach(d -> System.out.println(d));
    }

    /**
     * 测试针对一个null集合执行for each循环
     * 结果抛异常:java.lang.NullPointerException
     */
    @Test
    public void testForEachByNull() {
        List<String> list = null;

        for (String item : list) {
            System.out.println(item);
        }
    }


    /**
     * 测试从空列表 findFirst() 返回Optional<T>对象，对其调用get()方法会抛出异常
     * 结果抛异常: java.bootutils.NoSuchElementException: No value present
     */
    @Test
    public void testFindFirstOfEmptyList() {
        System.out.println(Lists.newArrayList().stream().findFirst().get());
    }

    /**
     * 测试从空列表 findAny() 返回Optional<T>对象，对其调用get()方法会抛出异常
     * 结果抛异常: java.bootutils.NoSuchElementException: No value present
     */
    @Test
    public void testFindAnyOfEmptyList() {
        System.out.println(Lists.newArrayList().stream().findAny().get());
    }
}
