package com.xuzimian.java.learning.spring.sleuth.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {
    private val log: Logger = LoggerFactory.getLogger(HomeController::class.java)

    @RequestMapping("/")
    fun home(): String? {
        log.info("Hello world!")
        return "Hello World!"
    }
}