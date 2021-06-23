package com.xuzimian.java.learning.spring.security.oauth.server.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

@Profile("jdbc")
@SpringBootApplication
public class AuthorizationserverApplicationJDBC {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationserverApplicationJDBC.class, args);
    }

}
