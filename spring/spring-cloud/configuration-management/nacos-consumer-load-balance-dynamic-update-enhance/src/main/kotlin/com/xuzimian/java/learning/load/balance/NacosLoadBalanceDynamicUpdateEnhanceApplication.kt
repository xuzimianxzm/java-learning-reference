package com.xuzimian.java.learning.load.balance

import com.xuzimian.java.learning.load.balance.config.GrayLoadBalancerRule
import com.xuzimian.java.learning.load.balance.interceptor.TrafficRule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cloud.netflix.ribbon.RibbonClients
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
@EnableConfigurationProperties(TrafficRule::class)
@RibbonClients(defaultConfiguration = [GrayLoadBalancerRule::class])
class NacosLoadBalanceDynamicUpdateEnhanceApplication

fun main(args: Array<String>) {
    SpringApplication.run(NacosLoadBalanceDynamicUpdateEnhanceApplication::class.java, *args)
}