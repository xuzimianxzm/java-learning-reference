package com.xuzimian.java.learning.load.balance.config

import com.xuzimian.java.learning.load.balance.interceptor.GrayFeignRequestInterceptor
import com.xuzimian.java.learning.load.balance.interceptor.GrayRestTemplateInterceptor
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate

/**
 *
 * 单路由使用：
 * `@RibbonClients(defaultConfiguration = {GrayRule.class})`
 *
 *
 * 多路由使用：
 * `@RibbonClients(value = { @RibbonClient(name = "A", configuration = GrayRuleA.class), @RibbonClient(name = "B", configuration = GrayRuleB.class) })`
 *
 */
@Configuration
class InterceptorConfig {

    @Bean
    @LoadBalanced
    fun restTemplate(): RestTemplate {
        val restTemplate = RestTemplate()
        restTemplate.interceptors.add(GrayRestTemplateInterceptor())
        return restTemplate
    }

    @Bean
    fun requestInterceptor(): GrayFeignRequestInterceptor {
        return GrayFeignRequestInterceptor()
    }
}