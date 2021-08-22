package com.xuzimian.java.learning.load.balance.interceptor

import com.xuzimian.java.learning.load.balance.RibbonRequestContextHolder
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.io.IOException
import java.lang.Boolean
import kotlin.ByteArray
import kotlin.Throws


class GrayRestTemplateInterceptor : ClientHttpRequestInterceptor {

    @Throws(IOException::class)
    override fun intercept(
        request: HttpRequest,
        body: ByteArray,
        execution: ClientHttpRequestExecution
    ): ClientHttpResponse {
        if (request.headers.containsKey("Gray")) {
            val value = request.headers.getFirst("Gray")
            if (value == "true") {
                RibbonRequestContextHolder.currentContext.put("Gray", Boolean.TRUE.toString())
                val attributes = RequestContextHolder.getRequestAttributes() as ServletRequestAttributes?
                attributes!!.setAttribute("Gray", Boolean.TRUE.toString(), 0)
            }
        }
        return execution.execute(request, body)
    }
}