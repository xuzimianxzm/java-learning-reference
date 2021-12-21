package com.xuzimian.globaldemo.common.basic.java.time;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;

/**
 * @program: global-demo
 * @description:
 * @author: xzm
 * @create: 2019-09-21 14:37
 **/
public class PeriodDemo {

    @Test
    public void test(){
        LocalDate from =LocalDate.of(2015, Month.JANUARY,1); // 2015-01-01 00:00:00
        LocalDate now =LocalDate.now(); //当前时间
        Period period=Period.between(from,now);

        System.out.println("天数："+period.getDays());
        System.out.println("月数："+period.getMonths());
        System.out.println("年数："+period.getYears());
    }
}
