package com.xuzimian.java.learning.spring.sleuth.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.sleuth.Span
import org.springframework.cloud.sleuth.SpanName
import org.springframework.cloud.sleuth.Tracer
import org.springframework.cloud.sleuth.annotation.NewSpan
import org.springframework.stereotype.Service

@Service
@SpanName("New Span Service")
class NewSpanService {
    private val log: Logger = LoggerFactory.getLogger(NewSpanService::class.java)

    @Autowired
    lateinit var tracer: Tracer

    fun createSubSpan(tax: String) {
        val newSpan: Span = tracer.nextSpan().name("calculateTax")
        try {
            tracer.withSpan(newSpan.start()).use { ws ->
                newSpan.tag("taxValue", tax)
                newSpan.event("taxCalculated")
                Thread.sleep(1000L)
                log.info("你将看到这条日志上的spanId将和其他的不一样，它有自己独立的spanId")
            }
        } finally {
            newSpan.end()
            log.info("I'm in the original span")
        }
    }

    @NewSpan("home index")
    fun home() {

    }
}