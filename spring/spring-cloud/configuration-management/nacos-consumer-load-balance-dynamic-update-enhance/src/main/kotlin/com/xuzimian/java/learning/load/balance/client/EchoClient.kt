package com.xuzimian.java.learning.load.balance.client

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "nacos-traffic-service")
interface EchoClient {
    @GetMapping("/")
    fun echo(@RequestHeader("Gray") gray: String): String
}