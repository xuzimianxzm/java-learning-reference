package com.xuzimian.java.learning.spring.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

@EnableRedisHttpSession
public class HttpSessionConfig {

    /**
     * SpringSessionBackedSessionRegistry 是Spring Security提供的用于在集群环境下控制会话并发的会话注册表实现类
     *
     * @return
     */
    @Bean
    public SpringSessionBackedSessionRegistry sessionRegistry(FindByIndexNameSessionRepository sessionRepository) {
        return new SpringSessionBackedSessionRegistry(sessionRepository);
    }

    /**
     * Spring Security在HttpSessionEventPublisher类中实现HttpSessionListener接口
     *
     * @return
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
