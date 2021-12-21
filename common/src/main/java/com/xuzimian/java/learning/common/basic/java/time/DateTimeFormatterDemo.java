package com.xuzimian.globaldemo.common.basic.java.time;

import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * DateTimeFormatter: 日期时间格式化器。
 * 与SimpleDateFormat相比,DateTimeFormatter是线程安全的。
 */
public class DateTimeFormatterDemo {

    @Test
    public void testFormation() {
        LocalDate date = LocalDate.of(2019, 9, 14);

        System.out.println("DateTimeFormatter.BASIC_ISO_DATE 格式化 :"+  date.format(DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println("DateTimeFormatter.ISO_LOCAL_DATE  格式化 :"+  date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println("DateTimeFormatter 自定义格式化  格式化 :"+  date.format(DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
    }

    @Test
    public  void testParse(){
        System.out.println("DateTimeFormatter.BASIC_ISO_DATE 解析 :"+  LocalDate.parse("20190914",DateTimeFormatter.BASIC_ISO_DATE));
        System.out.println("DateTimeFormatter 自定义 解析 :"+  LocalDate.parse("2019年09月14日",DateTimeFormatter.ofPattern("yyyy年MM月dd日")));
    }
}
