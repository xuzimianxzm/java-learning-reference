package com.xuzimian.globaldemo.common.basic.java.util.date;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * 注意:
 * 1.java.util.date 和 java.util.Calendar 类都是非线程安全的。
 *
 * 2. SimpleDateFormat 是非线程安全的，其calendar是共享变量，且未做线程安全控制。
 * 当多个线程同时使用相同的SimpleDateFormat对象(如用static修饰的SimpleDateFormat)
 * 调用format方法时，多个线程会同时调用calendar.setTime方法，可能一个线程刚好设置
 * 好time值，另外一个线程马上吧设置的time值给修改了。
 *
 */
public class DateDemo {


    /**
     * 比较是否同一年的同一个月
     */
    @Test
    public void testCompareSameYearMonth() throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date1 = sf.parse("2019-07-14 06:15");
        Date date2 = sf.parse("2019-07-14 23:55");


        System.out.println("Year；" + date1.getYear() + " month:" + date2.getMonth());
        if (date1.getYear() == date2.getYear()) {
            System.out.println(date1.getMonth() == date2.getMonth());

        }
    }

    /**
     * 日期比较器
     */
    @Test
    public void testDateComparator() throws ParseException {
        List<Date> dates = getDates();

        //倒序排列比较器
        Comparator<Date> descComparator = new Comparator<Date>() {
            @Override
            public int compare(Date o1, Date o2) {
                return o1.after(o2) ? -1 : 1;
            }
        };

        System.out.println(dates);
        dates.sort(descComparator);
        System.out.println(dates);

        System.out.println("------------------------------");

        Comparator<Date> ascComparator = (d1, d2) -> d1.getTime() - d2.getTime() > 0 ? 1 : -1;
        dates = getDates();
        System.out.println(dates);
        dates.sort(ascComparator);
        System.out.println(dates);


    }


    /**
     * 格式化：SimpleDateFormat 是非线程安全的，其calendar是共享变量，且未做线程安全控制。
     *
     *
     * @throws ParseException
     */
    @Test
    public void simpleDateFormat() throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月");
        System.out.println(sf.parse("2017年11月"));

        SimpleDateFormat sf1 = new SimpleDateFormat("yy年MM月dd");
        System.out.println(sf1.parse("17年11月03"));
        System.out.println(sf1.parse("00年11月03"));
    }

    /**
     * Date类的after比较
     */
    @Test
    public void testAfter() throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date largeDate = sf.parse("2000-01-02");
        Date smallDate = sf.parse("2000-01-01");
        System.out.println(largeDate.after(smallDate));
        System.out.println(smallDate.after(largeDate));
    }

    /**
     * Date类的after比较对象是null
     * 结果抛异常:java.lang.NullPointerException
     */
    @Test
    public void testAfterForNull() throws ParseException {
        System.out.println(new Date().after(null));
    }

    /**
     * 日期相加天数
     *
     * @throws ParseException
     */
    @Test
    public void testDateAddDays() throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sf.parse("2000-01-02");
        System.out.println(sf.format(DateUtils.addDays(date, 3)));
    }

    @Test
    public void testDateUtilsByTruncatedCompareTo() throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sf.parse("2018-01-01");
        Date date2 = sf.parse("2019-01-03");
        System.out.println(DateUtils.truncatedCompareTo(date2, date1, Calendar.MINUTE));
    }


    /**
     * 获取Date日期中天数
     * getDay方法返回的天数：实际返回的是这个星期的第几天
     * DateUtils.getFragmentInDays:根据第二个参数返回一年中的第几天，一个月中的第几天
     */
    @Test
    public void testDateDays() throws ParseException {
        Date date = new Date();
        System.out.println(date.getDay());
        System.out.println(DateUtils.getFragmentInDays(date, Calendar.YEAR));
        System.out.println(DateUtils.getFragmentInDays(date, Calendar.MONDAY));

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = sf.parse("2018-01-01");
        Date date2 = sf.parse("2019-01-01");

        System.out.println("date1:" + DateUtils.getFragmentInDays(date1, Calendar.YEAR));
        System.out.println("date2:" + DateUtils.getFragmentInDays(date2, Calendar.YEAR));
    }

    @Test
    public void testDateFormat() throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String dateStr = "2019-07-07T23:50:00.000Z".substring(0, 16).replace("T", " ");

        System.out.println(sf.parse(dateStr));
    }

    /**
     * 获取一个乱序时间集合
     *
     * @return
     * @throws ParseException
     */
    private List<Date> getDates() throws ParseException {
        List<Date> dates = Lists.newArrayList();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        dates.add(sf.parse("2000-01-09"));
        dates.add(sf.parse("2000-01-03"));
        dates.add(sf.parse("2000-01-05"));
        return dates;
    }


}
