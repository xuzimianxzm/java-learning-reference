package com.xuzimian.globaldemo.common.basic.java.util;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @program: global-demo
 * @description:
 * @author: xzm
 * @create: 2019-02-12 14:58
 **/
public class OptionalDemo {

    @Test
    public void test() {
        Map<String, String> maps = Maps.newHashMap();
        maps.put("key1", "v1");
        maps.put("key2", "v2");
        maps.put("key3", "v3");


        System.out.println(Optional.ofNullable(maps).map(d -> d.get("key0")).orElseGet(() -> "no key"));
        System.out.println(Optional.ofNullable(maps).map(d -> d.get("key3")).orElseGet(() -> "no key"));
        System.out.println(Optional.ofNullable(maps).flatMap(d -> Optional.ofNullable(d.get("key3"))).orElseGet(() -> "no key"));
    }


    @Test
    public void test1() {
        Map<String, Object> result = getDataMap();

        try {
            List<String> list = (List<String>) result.get("result_list");

            list.forEach(d -> {
                System.out.println(d);
            });
        } catch (Exception ex) {

        }


        Optional.ofNullable(getDataMap())
                .map(d -> (List<String>) d.get("result_list"))
                .orElseGet(() -> Lists.newArrayList())
                .forEach(d -> System.out.println(d)
                );



    }


    private Map<String, Object> getDataMap() {
        List<String> list = Lists.newArrayList();

        Map<String, Object> map = Maps.newHashMap();

        return map;
    }

}
