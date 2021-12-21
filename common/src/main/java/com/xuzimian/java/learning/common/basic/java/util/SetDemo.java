package com.xuzimian.globaldemo.common.basic.java.util;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * @program: global-demo
 * @description:
 * @author: xzm
 * @create: 2019-08-01 17:22
 **/
public class SetDemo {

    @Test
    public void testSetAddSameSlement() {
        Set<String> set = new HashSet<>();
        String val = "1";
        set.add("1");
        set.add("1");
        set.add(val);
        System.out.println(set.size());
    }


}
