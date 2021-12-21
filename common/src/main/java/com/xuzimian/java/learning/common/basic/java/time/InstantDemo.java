package com.xuzimian.globaldemo.common.basic.java.time;

import org.junit.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Instant:获取秒数,用于表示时间戳。
 */
public class InstantDemo {

    @Test
    public void test() {
        Instant instant = Instant.now();
        System.out.println("秒数：" + instant.getEpochSecond());
        System.out.println("毫秒数：" + instant.toEpochMilli());
    }

    @Test
    public void testStringToInstant() {
        assertThrows(DateTimeParseException.class, () -> Instant.parse("2020-5-14T00:00:00Z"));
        System.out.println(Instant.parse("2020-05-14T00:00:00Z"));
        assertThrows(DateTimeParseException.class, () -> Instant.parse("Thu May 14 11:50:19 CST 2020"));

        LocalDate date = LocalDate.parse("2013-05-01").atStartOfDay().toLocalDate();
        System.out.println(date.atStartOfDay(ZoneId.systemDefault()).toInstant());

        LocalDateTime.parse("04:30 PM, Sat 5/12/2018");

    }
}
