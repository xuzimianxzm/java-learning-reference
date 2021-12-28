package com.xuzimian.java.learning.load.balance

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest


@SpringBootApplication
class NacosGrayProviderServerApplication

fun main(args: Array<String>) {
    SpringApplication.run(NacosGrayProviderServerApplication::class.java, *args)
}

@RestController
internal class EchoController {
    @GetMapping("/")
    fun echo(request: HttpServletRequest): String {
        return "gray:" + request.localAddr + ":" + request.localPort
    }
}