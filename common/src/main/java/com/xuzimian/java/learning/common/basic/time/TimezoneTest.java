package com.xuzimian.java.learning.common.basic.time;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;


public class TimezoneTest {

    @Test
    void timezoneIdTest() {
        ZoneId.SHORT_IDS.forEach((key, value) -> {
            var zoneId = ZoneId.of(value);
            System.out.println(zoneId);
        });
    }
}
