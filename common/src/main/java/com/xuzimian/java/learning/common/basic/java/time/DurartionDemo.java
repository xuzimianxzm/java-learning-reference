package com.xuzimian.globaldemo.common.basic.java.time;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

/**
 * Duration: 表示一个时间段。
 */
public class DurartionDemo {

    @Test
    public void test(){
        LocalDateTime from =LocalDateTime.of(2015, Month.JANUARY,1,00,0,0); // 2015-01-01 00:00:00
        LocalDateTime now =LocalDateTime.now(); //当前时间
        Duration duration=Duration.between(from,now);
        System.out.println("天数："+duration.toDays());
        System.out.println("小时数："+duration.toHours());
        System.out.println("分钟数："+duration.toMinutes());
        System.out.println("秒数："+duration.getSeconds());
        System.out.println("毫秒数："+duration.toMillis());
        System.out.println("纳秒数："+duration.toNanos());
    }
}
