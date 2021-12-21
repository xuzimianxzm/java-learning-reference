package com.xuzimian.globaldemo.common.basic.java.time;

import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * LocalTime: 只会获取时分秒。
 */
public class LocalTimeDemo {

    /**
     * LocalTimeDemo.now() 对象是否相等
     */
    @Test
    public void testEqualTwoLocalTimeByNow() {
        LocalTime now1 = LocalTime.now();
        LocalTime now2 = LocalTime.now();
        System.out.println(now1.toString() + "=" + now2.toString() + ":" + now1.equals(now2));

        try {
            Thread.sleep(600);
            LocalTime now3 = LocalTime.now();
            System.out.println(now1.toString() + "=" + now3.toString() + ":" + now1.equals(now3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLocalTimeParse() {
        System.out.println(LocalTime.parse("18991230T11:00:00000", DateTimeFormatter.ofPattern("yyyyMMdd'T'HH:mm:ssSSS")));

        System.out.println(LocalTime.parse("11:00"));

        System.out.println(LocalTime.parse("11:00", DateTimeFormatter.ISO_TIME));

        assertThrows(DateTimeParseException.class, () -> LocalTime.parse("18991230T11:00:00000",
                DateTimeFormatter.ISO_DATE_TIME));

        assertThrows(DateTimeParseException.class, () -> LocalTime.parse("18991230T11:00:00000",
                DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        assertThrows(DateTimeParseException.class, () -> LocalTime.parse("18991230T11:00:00000",
                DateTimeFormatter.ISO_LOCAL_DATE));

        assertThrows(DateTimeParseException.class, () -> LocalTime.parse("18991230T11:00:00000",
                DateTimeFormatter.ISO_INSTANT));

        assertThrows(DateTimeParseException.class, () -> LocalTime.parse("18991230T11:00:00000",
                DateTimeFormatter.ISO_TIME));

        assertThrows(DateTimeParseException.class, () -> LocalTime.parse("18991230T11:00:00000",
                DateTimeFormatter.ISO_LOCAL_TIME));

        assertThrows(DateTimeParseException.class, () -> LocalTime.parse("18991230T11:00:00000",
                DateTimeFormatter.ISO_OFFSET_DATE));

        assertThrows(DateTimeParseException.class, () -> LocalTime.parse("18991230T11:00:00000",
                DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        assertThrows(DateTimeParseException.class, () -> LocalTime.parse("18991230T11:00:00000",
                DateTimeFormatter.ISO_ORDINAL_DATE));

        assertThrows(DateTimeParseException.class, () -> LocalTime.parse("18991230T11:00:00000",
                DateTimeFormatter.ISO_WEEK_DATE));

        assertThrows(DateTimeParseException.class, () -> LocalTime.parse("18991230T11:00:00000",
                DateTimeFormatter.ISO_OFFSET_TIME));

        assertThrows(DateTimeParseException.class, () -> LocalTime.parse("18991230T11:00:00000",
                DateTimeFormatter.BASIC_ISO_DATE));

        assertThrows(DateTimeParseException.class, () -> LocalTime.parse("18991230T11:00:00000",
                DateTimeFormatter.ISO_ZONED_DATE_TIME));
    }
}
