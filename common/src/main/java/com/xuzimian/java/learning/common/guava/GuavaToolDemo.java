package com.xuzimian.globaldemo.common.guava;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GuavaToolDemo {

    @Test
    public void testSplitter() {
        Splitter splitter = Splitter.on(".");
        List<String> keys = splitter.splitToList("xzm.ori");
        System.out.println(keys);
    }

    @Test
    public void testPreconditionsCheckArgument(int i) {
        Preconditions.checkArgument(5 > i, "必须小于5", i);
        System.out.println("testPreconditionsCheckArgument成功");
    }

    @Test
    public void testObjects() {
        System.out.println("testObjects:'a'=='a'" + Objects.equal("a", "a"));
        System.out.println("testObjects:List=='a'" + Objects.equal(Lists.newArrayList(), "a"));
        System.out.println("testObjects:Integer==Character" + Objects.equal(new Integer(3), new Character('3')));
        System.out.println("testObjects:'a'=='a'" + Objects.equal("a", "a"));
        System.out.println("testObjects.toStringHelper : " + Objects.toStringHelper("MyObject").add("x", 1).toString());
        System.out.println("testObjects.toStringHelper : " + Objects.hashCode(new Object(), "sssss", 156, 16.75f));
    }


    @Test
    public void testComparisonChainCompare() {
        System.out.println("testObjects:'10'> '168' " + ComparisonChain.start().compare(10, 168).result());
    }
}
