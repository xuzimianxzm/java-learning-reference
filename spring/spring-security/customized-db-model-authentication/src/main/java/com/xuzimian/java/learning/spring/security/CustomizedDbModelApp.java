package com.xuzimian.java.learning.spring.security;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xuzimian.java.learning.spring.security")
public class CustomizedDbModelApp {
    public static void main(String[] args) {
        SpringApplication.run(CustomizedDbModelApp.class, args);
    }
}
