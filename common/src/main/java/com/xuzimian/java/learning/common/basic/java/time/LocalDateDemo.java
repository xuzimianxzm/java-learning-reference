package com.xuzimian.globaldemo.common.basic.java.time;

import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * LocalDate: 只会年月日。
 * @program: global-demo
 * @description:
 * @author: xzm
 * @create: 2019-02-28 09:43
 **/
public class LocalDateDemo {


    public void testLocalDateGetData() {
        System.out.println(LocalDate.now().getYear());
        System.out.println(LocalDate.now().getMonthValue());
        System.out.println(LocalDate.now().getMonth().getValue());
        System.out.println(LocalDate.now().getDayOfMonth());
        System.out.println(LocalDate.now().getDayOfWeek());
        System.out.println(LocalDate.now().getDayOfYear());
    }

    @Test
    public void testLocalDateFormat() {
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        System.out.println(LocalDate.now().format(DateTimeFormatter.ofPattern("MMdd")));
    }


    @Test
    public void testLocalDateParse() {
        // error LocalDate.parse("20200715") or LocalDate.parse("2020/07/15")
        System.out.println(LocalDate.parse("2020-07-15"));

        System.out.println(LocalDate.parse("20200715", DateTimeFormatter.ofPattern("yyyyMMdd")));
    }


    /**
     * date 转换为 LocalDate
     */
    @Test
    public void testLocalDateConvertDate() {
        Date date = new Date();
        System.out.println(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
    }

    /**
     * LocalDate转换为 date
     */
    @Test
    public void testDateConvertLocalDate() {
        System.out.println(Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()));
    }

    /**
     * 获取日期中相差的天数
     */
    @Test
    public void testDaySubtract() {
        LocalDate to = LocalDate.now();
        LocalDate from = to.plusYears(1).plusDays(1);
        System.out.println(ChronoUnit.DAYS.between(to, from));
    }

    /***
     * 比较两个LocalDate.now() 对象是否相等
     * @return
     */
    @Test
    public void testEqualTwoLocalDateByNow() {
        LocalDate now1 = LocalDate.now();
        LocalDate now2 = LocalDate.now();
        System.out.println(now1.toString() + "=" + now2.toString() + ":" + now1.equals(now2));

        try {
            Thread.sleep(600);
            LocalDate now3 = LocalDate.now();
            System.out.println(now1.toString() + "=" + now3.toString() + ":" + now1.equals(now3));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
