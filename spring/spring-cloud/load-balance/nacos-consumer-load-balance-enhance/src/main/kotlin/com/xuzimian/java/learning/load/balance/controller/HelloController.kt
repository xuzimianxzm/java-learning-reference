package com.xuzimian.java.learning.load.balance.controller

import com.xuzimian.java.learning.load.balance.client.EchoClient
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import javax.servlet.http.HttpServletRequest

@RestController
class HelloController {
    @Autowired
    private lateinit var restTemplate: RestTemplate

    @Autowired
    private lateinit var echoClient: EchoClient

    private val serviceName = "nacos-traffic-service"

    @GetMapping("/echo")
    fun echo(request: HttpServletRequest): String? {
        val headers = HttpHeaders()
        if (StringUtils.isNotEmpty(request.getHeader("Gray"))) {
            headers.add("Gray", if (request.getHeader("Gray") == "true") "true" else "false")
        }
        val entity = HttpEntity<String>(headers)
        return restTemplate.exchange("http://$serviceName/", HttpMethod.GET, entity, String::class.java)
            .getBody()
    }

    @GetMapping("/echoFeign")
    fun echoFeign(request: HttpServletRequest): String {
        if (StringUtils.isNotEmpty(request.getHeader("Gray"))) {
            if (request.getHeader("Gray") == "true") {
                return echoClient.echo("true")
            }
        }
        return echoClient.echo("false")
    }
}