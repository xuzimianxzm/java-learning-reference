package com.xuzimian.java.learning.spring.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.xuzimian.java.learning.spring.security")
@SpringBootApplication
public class SessionWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(SessionWebApplication.class, args);
    }
}
