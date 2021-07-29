package com.xuzimian.java.learning.spring.sleuth.controller

import com.xuzimian.java.learning.spring.sleuth.service.NewSpanService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HomeController {
    private val log: Logger = LoggerFactory.getLogger(HomeController::class.java)

    @Autowired
    lateinit var newSpanService: NewSpanService

    @RequestMapping("/")
    fun home(): String? {
        log.info("Hello world!")
        newSpanService.home()
        return "Hello World!"
    }
}