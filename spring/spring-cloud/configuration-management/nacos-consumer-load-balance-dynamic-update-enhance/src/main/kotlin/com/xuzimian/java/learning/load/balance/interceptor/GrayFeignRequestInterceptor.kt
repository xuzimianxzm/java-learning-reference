package com.xuzimian.java.learning.load.balance.interceptor

import com.xuzimian.java.learning.load.balance.RibbonRequestContextHolder.currentContext
import feign.RequestInterceptor
import feign.RequestTemplate
import org.springframework.beans.factory.annotation.Autowired
import java.lang.Boolean


class GrayFeignRequestInterceptor : RequestInterceptor {
    @Autowired
    private lateinit var rule: TrafficRule

    override fun apply(template: RequestTemplate) {
        if (rule.type.equals("header", ignoreCase = true)) {
            if (template.headers().containsKey(rule.name)) {
                val value = template.headers()[rule.name]!!.iterator().next()
                if (value == rule.value) {
                    currentContext.put("Gray", Boolean.TRUE.toString())
                }
            }
        } else if (rule.type.equals("param", ignoreCase = true)) {
            if (template.queries().containsKey(rule.name)) {
                val value = template.queries()[rule.name]!!.iterator().next()
                if (value == rule.value) {
                    currentContext.put("Gray", Boolean.TRUE.toString())
                }
            }
        }
    }
}