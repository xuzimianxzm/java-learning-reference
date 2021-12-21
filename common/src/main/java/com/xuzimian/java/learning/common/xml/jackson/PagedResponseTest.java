package com.xuzimian.globaldemo.common.xml.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

public class PagedResponseTest {

    @Test
    public void test() throws JsonProcessingException {
        List<User> users = Lists.newArrayList(new User(1, "xxxx"), new User(1, "nnnnn"));
        PagedResponse<User> page = new PagedResponse(2, 2, 1, users);

        XmlMapper xmlMapper = new XmlMapper();
        String value = xmlMapper.writeValueAsString(page);

        System.out.println(value);
    }


    private class User {
        private int age;
        private String name;

        public User(int age, String name) {
            this.age = age;
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
