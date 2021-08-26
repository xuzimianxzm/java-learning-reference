package com.xuzimian.java.learning.load.balance.interceptor

import com.xuzimian.java.learning.load.balance.RibbonRequestContextHolder.currentContext
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import java.io.IOException
import java.lang.Boolean
import kotlin.ByteArray
import kotlin.Throws


class GrayRestTemplateInterceptor : ClientHttpRequestInterceptor {
    private lateinit var rule: TrafficRule

    @Throws(IOException::class)
    override fun intercept(
        request: HttpRequest,
        body: ByteArray,
        execution: ClientHttpRequestExecution
    ): ClientHttpResponse {
        if (rule.type.equals("header", ignoreCase = true)) {
            if (request.headers.containsKey(rule.name)) {
                val value = request.headers[rule.name]!!.iterator().next()
                if (value == rule.value) {
                    currentContext.put("Gray", Boolean.TRUE.toString())
                }
            }
        } else if (rule.type.equals("param", ignoreCase = true)) {
            val query = request.uri.query
            val queryKV = query.split("&").toTypedArray()
            for (queryItem in queryKV) {
                val queryInfo = queryItem.split("=").toTypedArray()
                if (queryInfo[0].equals(rule.name, ignoreCase = true) && queryInfo[1] == rule.value) {
                    currentContext.put("Gray", Boolean.TRUE.toString())
                }
            }
        }
        return execution.execute(request, body)
    }
}