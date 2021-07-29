package com.xuzimian.java.learning.spring.sleuth.controller

import com.xuzimian.java.learning.spring.sleuth.service.NewSpanService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tracer")
class TracerController {
    private val log: Logger = LoggerFactory.getLogger(TracerController::class.java)

    @Autowired
    lateinit var newSpanService: NewSpanService

    @GetMapping("/span/{tax}")
    fun createSubSpan(@PathVariable tax: String) {
        log.info("create span")
        return newSpanService.createSubSpan(tax)
    }
}