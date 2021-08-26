package com.xuzimian.java.learning.load.balance.interceptor

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "traffic.rule")
class TrafficRule {
    var type: String? = null
    var name: String? = null
    var value: String? = null
    fun setRateLimiterName(name: String?) {
        this.name = name
    }

    override fun toString(): String {
        return "TrafficRule{" +
                "type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}'
    }
}