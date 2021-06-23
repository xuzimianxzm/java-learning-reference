package com.xuzimian.java.learning.spring.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;

@Configuration
public class EmbededRedisConfig {

    private final int redisPort;
    private RedisServer redisServer;

    public EmbededRedisConfig(@Value("${spring.redis.port}") int redisPort) {
        this.redisPort = redisPort;
    }

    @PostConstruct
    public void startRedis() throws IOException {
        redisServer = new RedisServer(redisPort);
        redisServer.start();
    }

    @PreDestroy
    public void stopRedis() {
        redisServer.stop();
    }
}