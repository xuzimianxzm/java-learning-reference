package com.xuzimian.java.learning.load.balance.config

import com.alibaba.cloud.nacos.ribbon.NacosServer
import com.netflix.client.config.IClientConfig
import com.netflix.loadbalancer.AbstractLoadBalancerRule
import com.netflix.loadbalancer.Server
import com.xuzimian.java.learning.load.balance.RibbonRequestContextHolder
import org.springframework.util.StringUtils
import java.lang.Boolean.TRUE
import java.util.*

class GrayLoadBalancerRule : AbstractLoadBalancerRule() {
    private val random = Random()

    override fun initWithNiwsConfig(clientConfig: IClientConfig) {}

    override fun choose(key: Any?): Server {
        var grayInvocation = false
        return try {
            val grayTag = RibbonRequestContextHolder.currentContext["Gray"]
            if (!StringUtils.isEmpty(grayTag) && grayTag == TRUE.toString()) {
                grayInvocation = true
            }
            val serverList = this.loadBalancer.reachableServers
            val grayServerList: MutableList<Server> = ArrayList()
            val normalServerList: MutableList<Server> = ArrayList()
            for (server in serverList) {
                val nacosServer = server as NacosServer
                if (nacosServer.metadata.containsKey("gray") && nacosServer.metadata["gray"] == "true") {
                    grayServerList.add(server)
                } else {
                    normalServerList.add(server)
                }
            }
            if (grayInvocation) {
                grayServerList[random.nextInt(grayServerList.size)]
            } else {
                normalServerList[random.nextInt(normalServerList.size)]
            }
        } finally {
            RibbonRequestContextHolder.clearContext()
        }
    }
}