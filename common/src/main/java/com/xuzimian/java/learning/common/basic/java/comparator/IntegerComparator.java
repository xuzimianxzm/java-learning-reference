package com.xuzimian.globaldemo.common.basic.java.comparator;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * Comparator 是升序排序方式，小的前，大的在后。
 * Comparator compare方法返回1 表示参数1大，应该排在参数2后面，返回0表示一样大，返回-1(或小于0)表示参数1小，应该排在参数2的前面。
 */
public class IntegerComparator {
    public static void main(String[] args) {
        List<Integer> integerList = Lists.newArrayList(5, 1, 6, 7, 4, 9, 8, 2, 3);
        integerList.stream().sorted().forEach(it -> System.out.print(it));
    }
}
