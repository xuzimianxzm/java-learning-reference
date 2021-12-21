package com.xuzimian.globaldemo.common.basic.java.comparator;

import com.google.common.collect.Lists;

import java.util.Comparator;
import java.util.List;

public class MultipleComparator {

    public static void main(String[] args) {
        List<User> users = Lists.newArrayList(
                new User("aaa", 12, true),
                new User("bba", 18, false),
                new User("aba", 24, true),
                new User("aba", 25, true),
                new User("bbc", 23, false)
        );

        users.stream()
                .sorted(new UserGenderComparator()
                        .thenComparing(new UserNameComparator())
                        .thenComparing(new UserAgeComparator()))
                .forEach(it -> System.out.println(it));
    }

    public static class UserGenderComparator implements Comparator<User> {
        @Override
        public int compare(User user1, User user2) {
            if (user1.getGender()) {
                return 1;
            }

            if (user2.getGender()) {
                return -1;
            }

            return 0;
        }
    }

    public static class UserNameComparator implements Comparator<User> {
        @Override
        public int compare(User user1, User user2) {
            return user2.getName().compareTo(user1.getName());
        }
    }

    public static class UserAgeComparator implements Comparator<User> {
        @Override
        public int compare(User user1, User user2) {
            return user1.getAge().compareTo(user2.getAge());
        }
    }
}
