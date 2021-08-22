package com.xuzimian.java.learning.load.balance

import com.xuzimian.java.learning.load.balance.config.GrayLoadBalancerRule
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.netflix.ribbon.RibbonClients
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
//@EnableDiscoveryClient(autoRegister = false)
@RibbonClients(defaultConfiguration = [GrayLoadBalancerRule::class])
class NacosConsumerRibbonenhanceApplication

fun main(args: Array<String>) {
    SpringApplication.run(NacosConsumerRibbonenhanceApplication::class.java, *args)
}