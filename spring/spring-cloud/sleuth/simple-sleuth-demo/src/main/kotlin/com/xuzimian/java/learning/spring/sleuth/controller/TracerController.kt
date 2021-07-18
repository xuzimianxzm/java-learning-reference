package com.xuzimian.java.learning.spring.sleuth.controller

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.sleuth.Span
import org.springframework.cloud.sleuth.Tracer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/tracer")
class TracerController {
    private val log: Logger = LoggerFactory.getLogger(TracerController::class.java)

    @Autowired
    lateinit var tracer: Tracer

    @GetMapping("/span/{tax}")
    fun createSubSpan(@PathVariable tax: String) {
        val newSpan: Span = tracer.nextSpan().name("calculateTax")
        try {
            tracer.withSpan(newSpan.start()).use { ws ->
                newSpan.tag("taxValue", tax)
                newSpan.event("taxCalculated")
            }
            log.info("request parameter:$tax")
        } finally {
            newSpan.end()
        }
    }
}