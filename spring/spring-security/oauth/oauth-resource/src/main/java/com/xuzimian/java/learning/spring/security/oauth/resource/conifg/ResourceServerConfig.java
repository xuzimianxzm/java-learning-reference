package com.xuzimian.java.learning.spring.security.oauth.resource.conifg;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    public static final String RESOURCE_ID = "resourceserver";
    private static final Logger logger = LoggerFactory.getLogger(ResourceServerConfig.class);

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        super.configure(resources);
        resources.resourceId(RESOURCE_ID);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        logger.info("ResourceServerConfig中配置HttpSecurity对象执行");
        // 只有/user端口作为资源服务器的资源
        http.requestMatchers().antMatchers("/resource")
                .and()
                .authorizeRequests()
                // .antMatchers("/resource").access("#oauth2.hasScope('profile')")
                .anyRequest().authenticated();

    }
}
