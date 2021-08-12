package com.xuzimian.java.learning.spring.boot.admin

import de.codecentric.boot.admin.server.config.EnableAdminServer
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@EnableAutoConfiguration
@EnableAdminServer
@Import(SecuritySecureConfig::class)
class SpringBootAdminApplication

fun main(args: Array<String>) {
    runApplication<SpringBootAdminApplication>(*args)
}