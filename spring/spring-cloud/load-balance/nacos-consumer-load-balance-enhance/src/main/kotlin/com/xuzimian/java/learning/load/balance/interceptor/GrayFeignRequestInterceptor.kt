package com.xuzimian.java.learning.load.balance.interceptor

import com.xuzimian.java.learning.load.balance.RibbonRequestContextHolder
import feign.RequestInterceptor
import feign.RequestTemplate
import java.lang.Boolean

class GrayFeignRequestInterceptor : RequestInterceptor {
    override fun apply(template: RequestTemplate) {
        if (template.headers().containsKey("Gray")) {
            val value = template.headers()
                .get("Gray")
                ?.iterator()
                ?.next()
            if (value == "true") {
                RibbonRequestContextHolder.currentContext.put("Gray", Boolean.TRUE.toString())
            }
        }
    }
}