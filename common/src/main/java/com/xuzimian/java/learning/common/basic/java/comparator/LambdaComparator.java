package com.xuzimian.globaldemo.common.basic.java.comparator;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;

public class LambdaComparator {
    public static void main(String[] args) {
        List<User> users = Lists.newArrayList(
                new User("aaa", 12, true),
                new User("bba", 18, false),
                new User("aba", 24, true),
                new User("aba", 25, true),
                new User("bbc", 23, false)
        );

        users.stream()
                .sorted(Comparator.comparing(User::getGender, (u1, u2) -> u1 ? 1 : (u2 ? -1 : 0))
                        .thenComparing(User::getName, (u1, u2) -> u2.compareTo(u1))
                        .thenComparing(User::getAge, (u1, u2) -> u1.compareTo(u2)))
                .forEach(it -> System.out.println(it));
    }
}
