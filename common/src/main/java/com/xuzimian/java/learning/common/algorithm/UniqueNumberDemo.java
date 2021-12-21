package com.xuzimian.globaldemo.common.algorithm;

import org.junit.jupiter.api.Test;

public class UniqueNumberDemo {


    @Test
    public void testSystemTimeMillis(){
        System.out.println(getSystemTimeMillis());
    }
    /**
     * 来自zookeeper的会话sessionid生成算法
     */
    public long getSystemTimeMillis() {
        long machineId = 3; //该编号可以通过定一个方法参数随便传一个数值进来
        long nextSid = 0;
        long currentTimeMillis = System.currentTimeMillis();
        nextSid = (currentTimeMillis << 24) >>> 8;
        return nextSid = nextSid | (machineId << 56);
    }
}
