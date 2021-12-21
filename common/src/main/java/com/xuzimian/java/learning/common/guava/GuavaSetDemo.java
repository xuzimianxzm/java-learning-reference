package com.xuzimian.globaldemo.common.guava;

import com.google.common.collect.*;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GuavaSetDemo {


    @Test
    public void testTable() {
        Table<String, String, String> datas = HashBasedTable.create();
        datas.put("xzm", "name", "xzm");
        datas.put("xzm", "age", "18");
        datas.put("xzm", "sex", "男");
        datas.put("li", "name", "li");

        datas.rowKeySet().forEach(d -> {
            System.out.println(d);
        });
    }

    @Test
    public void testMapsDifference() {
        Map<String, Integer> map1 = Maps.newHashMap();
        Map<String, Integer> map2 = Maps.newHashMap();

        map1.put("k1", 1);
        map1.put("k2", 2);
        map1.put("k3", 3);
        map1.put("k4", 4);
        map1.put("k5", 5);

        map2.put("k3", 3);
        map2.put("k4", 6);
        map2.put("k5", 7);
        map2.put("k6", 8);
        map2.put("k7", 9);
        map2.put("k8", 1);

        MapDifference<String, Integer> diff = Maps.difference(map1, map2);
        System.out.println("testMapsDifference.entriesInCommon: " + diff.entriesInCommon());
        System.out.println("testMapsDifference.entriesOnlyOnLeft: " + diff.entriesOnlyOnLeft());
        System.out.println("testMapsDifference.entriesOnlyOnRight: " + diff.entriesOnlyOnRight());
        System.out.println("testMapsDifference.entriesDiffering: " + diff.entriesDiffering());
    }

    /**
     * 抛出空指针异常
     */
    @Test
    public void testListsTransformByNull() {
        Lists.transform(getNullList(), d -> d.get("no key"));
    }

    /**
     * 测试结果：
     * 1.Lists.transform()产生的集合不影响原集合 。
     * 2.原集合修改，不影响Lists.transform()产生的集合 。
     * 3.Lists.transform()产生的集合中元素不能被修改。
     * 3.Lists.transform()产生的集合可以删除元素，不能添加元素。
     */
    @Test
    public void testListsTransformProblem() {
        List<Map<String, String>> list = getList();
        list.forEach(d->System.out.print( " "+d.get("name") +" "));
        System.out.println("------------原集合list ---------------");
        System.out.println();

        List<Map<String, String>> list1=Lists.transform(getList(), d ->{
            d.put("name","----");
            return d;
        });
        System.out.println("------------Lists.transform  后产生的list1 ---------------");
        list1.forEach(d->System.out.print(" "+d.get("name") +" "));
        System.out.println();

        System.out.println("------------Lists.transform  后产生的list1的原list ---------------");
        list.forEach(d->System.out.print(" "+d.get("name") +" "));
        System.out.println();

        System.out.println("------------list1 change 后的list1 ---------------");
        list1.forEach(d->d.put("name","list1 改变"));
        list1.forEach(d->System.out.print(" "+d.get("name") +" "));
        System.out.println();

        System.out.println("------------list  change 后的list1 ---------------");
        list.forEach(d->d.put("name","list 改变"));
        list1.forEach(d->System.out.print(" "+d.get("name") +" "));
        System.out.println();

        System.out.println("------------list change 后的list ---------------");
        list.forEach(d->System.out.print(" "+d.get("name") +" "));
        System.out.println();

        System.out.println("------------list1 remove 数据后list1 ---------------");
        list1.remove(0);
        list1.remove(0);
        list1.remove(0);
        list1.forEach(d->System.out.print(" "+d.get("name") +" "));
        System.out.println();

        System.out.println("------------list1 remove 数据后list ---------------");
        list.forEach(d->System.out.print(" "+d.get("name") +" "));
        System.out.println();

        System.out.println("------------list1 add 数据后 ---------------");
        list1.add(new HashMap<String,String>());


    }


    private List<Map<String, String>> getList() {
        List<Map<String, String>> list = Lists.newArrayList();

        Map<String, String> map;
        for (int n = 1; n <= 12; n++) {
            map = Maps.newHashMap();
            map.put("name", "xzm" + n);
            list.add(map);
        }

        return list;
    }

    private List<Map<String, String>> getNullList() {
        return null;
    }
}
