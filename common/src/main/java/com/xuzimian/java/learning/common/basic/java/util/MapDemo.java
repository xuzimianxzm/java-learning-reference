package com.xuzimian.globaldemo.common.basic.java.util;


import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: global-demo
 * @description: Map demo
 * @author: xzm
 * @create: 2019-07-12 14:36
 **/
public class MapDemo {

    /**
     * Map 的 put()方法，会返回被替换的key的value,如果是第一次put一个key，就会返回null
     */
    @Test
    public void testPut() {
        Map<String, Object> map = new HashMap<>();

        Object putVal = map.put("X", "123");
        System.out.println(putVal);

        putVal = map.put("X", "1234");
        System.out.println(putVal);
    }
}
