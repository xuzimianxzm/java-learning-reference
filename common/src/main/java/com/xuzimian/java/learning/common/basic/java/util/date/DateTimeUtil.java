package com.xuzimian.globaldemo.common.basic.java.util.date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 四海华彩-大隐网络
 */
public class DateTimeUtil {

    /**
     * 标准的一年天数
     */
    public static final int STANDARD_DAYS_OF_YEAR = 365;

    /**
     * 标准的一周天数
     */
    public static final int STANDARD_DAYS_OF_WEEK = 7;

    /**
     * 一秒的毫秒数
     */
    public static final long MILLIS_NUM_IN_SEC = 1000L;

    /**
     * 百
     */
    public static final long HUNDRED = 100L;

    /**
     * 十
     */
    public static final long TEN = 10L;

    /**
     * 一秒的毫秒数
     */
    public static final double MILLIS_NUM_IN_SEC_D = 1000D;

    /**
     * 小时最大值
     */
    public static final int MAX_HOUR_NUM = 23;

    /**
     * 分钟最大值
     */
    public static final int MAX_MINUTE_NUM = 59;

    /**
     * 秒最大值
     */
    public static final int MAX_SECOND_NUM = 59;

    /**
     * 一分钟里的秒数
     */
    public static final long SECS_NUM_IN_MIN = 60;

    /**
     * 一小时里的分钟数
     */
    public static final long MINS_NUM_IN_HOUR = 60;

    /**
     * 一天里的小时数
     */
    public static final long HOURS_NUM_IN_DAY = 24;

    /**
     * 一小时里的秒数
     */
    public static final long SECS_NUM_IN_HOUR = MINS_NUM_IN_HOUR * SECS_NUM_IN_MIN;

    /**
     * 一天里的秒数
     */
    public static final long SECS_NUM_IN_DAY = HOURS_NUM_IN_DAY * SECS_NUM_IN_HOUR;

    public static final String FORMAT_YYYYMMDD = "yyyy-MM-dd";

    public static final String FORMAT_HHMMSS = "HH:mm:ss";

    public static final String FORMAT_YYYYMMDDHHMMSS = FORMAT_YYYYMMDD + " " + FORMAT_HHMMSS;

    public static final String FORMAT_YYYYMMDDHHMMSSSSS = FORMAT_YYYYMMDDHHMMSS + ",SSS";

    public static final String FORMAT_YYYYMMDD_NO_BREAK = "yyyyMMdd";

    public static final String FORMAT_HHMMSS_NO_BREAK = "HHmmss";

    public static final String FORMAT_YYYYMMDDHHMMSS_NO_BREAK = FORMAT_YYYYMMDD_NO_BREAK + FORMAT_HHMMSS_NO_BREAK;

    public static final String FORMAT_YYYYMMDDHHMMSSSSS_NO_BREAK = FORMAT_YYYYMMDDHHMMSS_NO_BREAK + "SSS";

    public static final String FORMAT_DEFAULT = FORMAT_YYYYMMDDHHMMSS;

    public static final long MS_REF_EPOCH = 0;

    public static final Date DATE_REF_EPOCH = new Date(MS_REF_EPOCH);

    /**
     * 2000-01-01 00:00:00的epoch毫秒数（946656000000），用来做计时或序列标准，用以缩短标记空间<br />
     */
    public static final long MS_REF_2000 = toDateOrNull("20000101", FORMAT_YYYYMMDD_NO_BREAK).getTime();

    /**
     * 2000-01-01 00:00:00的epoch百毫秒数（9466560000），用来做计时或序列标准，用以缩短标记空间<br />
     */
    public static final long HUNDRED_MS_REF_2000 = MS_REF_2000 / HUNDRED;

    /**
     * 2000-01-01 00:00:00的epoch十毫秒数（94665600000），用来做计时或序列标准，用以缩短标记空间<br />
     */
    public static final long TEN_MS_REF_2000 = MS_REF_2000 / TEN;

    /**
     * 2000-01-01 00:00:00的epoch秒数（946656000），用来做计时或序列标准，用以缩短标记空间<br />
     */
    public static final long SEC_REF_2000 = MS_REF_2000 / MILLIS_NUM_IN_SEC;

    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeUtil.class);

    private static final String ERR_MSG_INVALID_FORMAT = "Invalid format.";

    private static final String ERR_MSG_PARSE_FAILED = "Parse date failed.";

    private static final int NO_BREAK_DAY_LENGTH = FORMAT_YYYYMMDD_NO_BREAK.length();

    private static final int NO_BREAK_TIME_LENGTH = FORMAT_HHMMSS_NO_BREAK.length();

    private static final int WEEK_DAY_MON_BASE_SUNDAY = 7;

    private static final int DAY_INT_YEAR_CLEAR_MARK = 10000;

    private static final int DAY_INT_YEAR_START_MD = 101;

    private static final int DAY_INT_YEAR_END_MD = 1231;

    private static final int DAY_INT_MONTH_CLEAR_MARK = 100;

    private static final int TIME_INT_HOUR_CLEAR_MARK = 10000;

    private static final int TIME_INT_MINUTE_CLEAR_MARK = 100;

    /**
     * 将字符串解析为Date对象，当失败时抛出异常
     * @param dateString
     * @param format
     * @return
     * @throws ParseException
     */
    public static Date toDate(String dateString, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(dateString);
    }

    /**
     * 将字符串解析为Date对象，当失败时返回null
     * <p>
     * <strong>注意，异常只会以<code>debug</code>级别打印，如果需要关注异常，则因调用{@link DateTimeUtil#toDate(String, String)}方法</strong>
     * </p>
     * @param dateString
     * @param format
     * @return
     */
    public static Date toDateOrNull(String dateString, String format) {
        if (StringUtils.isBlank(dateString)) {
            return null;
        }
        try {
            return new SimpleDateFormat(format).parse(dateString);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(ERR_MSG_INVALID_FORMAT, e);
            return null;
        } catch (ParseException e) {
            LOGGER.debug(ERR_MSG_PARSE_FAILED, e);
            return null;
        }
    }

    /**
     * 以默认格式（{@value #FORMAT_DEFAULT}）将字符串解析为Date对象，当失败时抛出异常
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date toDate(String dateString) throws ParseException {
        return toDate(dateString, FORMAT_DEFAULT);
    }

    /**
     * 以默认格式（{@value #FORMAT_DEFAULT}）将字符串解析为Date对象，当失败时返回null
     * <p>
     * <strong>注意，异常只会以<code>debug</code>级别打印，如果需要关注异常，则因调用{@link DateTimeUtil#toDate(String)}方法</strong>
     * </p>
     * @param dateString
     * @return
     */
    public static Date toDateOrNull(String dateString) {
        return toDateOrNull(dateString, FORMAT_DEFAULT);
    }

    /**
     * 以连接符分隔的日期格式（{@value #FORMAT_YYYYMMDD}）将字符串解析为Date对象，当失败时抛出异常
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date toDateWithHyphenDay(String dateString) throws ParseException {
        return toDate(dateString, FORMAT_YYYYMMDD);
    }

    /**
     * 以连接符分隔的日期格式（{@value #FORMAT_YYYYMMDD}）将字符串解析为Date对象，当失败时返回null
     * <p>
     * <strong>注意，异常只会以<code>debug</code>级别打印，如果需要关注异常，则应调用{@link DateTimeUtil#toDateWithHyphenDay(String)}
     * 方法</strong>
     * </p>
     * @param dateString
     * @return
     */
    public static Date toDateOrNullWithHyphenDay(String dateString) {
        return toDateOrNull(dateString, FORMAT_YYYYMMDD);
    }

    /**
     * 以无分隔的日期格式（{@value #FORMAT_YYYYMMDD_NO_BREAK}）将字符串解析为Date对象，当失败时抛出异常
     * @param dateString
     * @return
     * @throws ParseException
     */
    public static Date toDateWithNoBreakDay(String dateString) throws ParseException {
        return toDate(dateString, FORMAT_YYYYMMDD_NO_BREAK);
    }

    /**
     * 以无分隔的日期格式（{@value #FORMAT_YYYYMMDD_NO_BREAK}）将字符串解析为Date对象，当失败时返回null
     * <p>
     * <strong>注意，异常只会以<code>debug</code>级别打印，如果需要关注异常，则应调用{@link DateTimeUtil#toDateWithNoBreakDay(String)}
     * 方法</strong>
     * </p>
     * @param dateString
     * @return
     */
    public static Date toDateOrNullWithNoBreakDay(String dateString) {
        return toDateOrNull(dateString, FORMAT_YYYYMMDD_NO_BREAK);
    }

    /**
     * 以无分隔的日期格式（{@value #FORMAT_YYYYMMDD_NO_BREAK}）将整数解析为Date对象，当失败时抛出异常
     * @param dateInt
     * @return
     * @throws ParseException
     */
    public static Date toDateWithNoBreakDay(int dateInt) throws ParseException {
        return toDate(Integer.toString(dateInt), FORMAT_YYYYMMDD_NO_BREAK);
    }

    /**
     * 以无分隔的日期格式（{@value #FORMAT_YYYYMMDD_NO_BREAK}）将整数解析为Date对象，当失败时返回null
     * <p>
     * <strong>注意，异常只会以<code>debug</code>级别打印，如果需要关注异常，则应调用{@link DateTimeUtil#toDateWithNoBreakDay(int)}方法</strong>
     * </p>
     * @param dateInt
     * @return
     */
    public static Date toDateOrNullWithNoBreakDay(int dateInt) {
        return toDateOrNull(Integer.toString(dateInt), FORMAT_YYYYMMDD_NO_BREAK);
    }

    /**
     * 格式化Date对象，如果Date对象为null，则返回null
     * @param date
     * @param format
     * @return
     */
    public static String toString(Date date, String format) {
        if (null == date) {
            return null;
        }
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 格式化Date对象，如果Date对象为null或格式化失败，则返回null
     * @param date
     * @param format
     * @return
     */
    public static String toStringOrNull(Date date, String format) {
        if (null == date) {
            return null;
        }
        try {
            return new SimpleDateFormat(format).format(date);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(ERR_MSG_INVALID_FORMAT, e);
            return null;
        }
    }

    /**
     * 格式化Date对象，如果Date对象为null或格式化失败，则返回空白字符串
     * @param date
     * @param format
     * @return
     */
    public static String toStringOrEmpty(Date date, String format) {
        if (null == date) {
            return "";
        }
        try {
            return new SimpleDateFormat(format).format(date);
        } catch (IllegalArgumentException e) {
            LOGGER.debug(ERR_MSG_INVALID_FORMAT, e);
            return "";
        }
    }

    /**
     * 以默认格式（{@value #FORMAT_DEFAULT}）格式化Date对象，如果Date对象为null，则返回null
     * @param date
     * @return
     */
    public static String toString(Date date) {
        return toString(date, FORMAT_DEFAULT);
    }

    /**
     * 以默认格式（{@value #FORMAT_DEFAULT}）格式化Date对象，如果Date对象为null或格式化失败，则返回null
     * @param date
     * @return
     */
    public static String toStringOrNull(Date date) {
        return toStringOrNull(date, FORMAT_DEFAULT);
    }

    /**
     * 以默认格式（{@value #FORMAT_DEFAULT}）格式化Date对象，如果Date对象为null或格式化失败，则返回空白字符串
     * @param date
     * @return
     */
    public static String toStringOrEmpty(Date date) {
        return toStringOrNull(date, FORMAT_DEFAULT);
    }

    /**
     * 以无分隔格式（{@value #FORMAT_YYYYMMDD_NO_BREAK}）格式化Date对象，只取日期，如果Date对象为null或格式化失败，则返回null
     * @param date
     * @return
     */
    public static String toStringOrNullWithNoBreakDay(Date date) {
        return toStringOrNull(date, FORMAT_YYYYMMDD_NO_BREAK);
    }

    /**
     * 以无分隔格式（{@value #FORMAT_YYYYMMDD_NO_BREAK}）格式化Date对象，只取日期，如果Date对象为null或格式化失败，则返回空白字符串
     * @param date
     * @return
     */
    public static String toStringOrEmptyWithNoBreakDay(Date date) {
        return toStringOrNull(date, FORMAT_YYYYMMDD_NO_BREAK);
    }

    /**
     * 以无分隔格式（{@value #FORMAT_HHMMSS_NO_BREAK}）格式化Date对象，只取时间，如果Date对象为null或格式化失败，则返回null
     * @param date
     * @return
     */
    public static String toStringOrNullWithNoBreakTime(Date date) {
        return toStringOrNull(date, FORMAT_HHMMSS_NO_BREAK);
    }

    /**
     * 以无分隔格式（{@value #FORMAT_HHMMSS_NO_BREAK}）格式化Date对象，只取时间，如果Date对象为null或格式化失败，则返回空白字符串
     * @param date
     * @return
     */
    public static String toStringOrEmptyWithNoBreakTime(Date date) {
        return toStringOrNull(date, FORMAT_HHMMSS_NO_BREAK);
    }

    /**
     * 以无分隔格式（{@value #FORMAT_YYYYMMDDHHMMSS_NO_BREAK}）格式化Date对象，如果Date对象为null或格式化失败，则返回null
     * @param date
     * @return
     */
    public static String toStringOrNullWithNoBreakFormat(Date date) {
        return toStringOrNull(date, FORMAT_YYYYMMDDHHMMSS_NO_BREAK);
    }

    /**
     * 以无分隔格式（{@value #FORMAT_YYYYMMDDHHMMSS_NO_BREAK}）格式化Date对象，如果Date对象为null或格式化失败，则返回空白字符串
     * @param date
     * @return
     */
    public static String toStringOrEmptyWithNoBreakFormat(Date date) {
        return toStringOrNull(date, FORMAT_YYYYMMDDHHMMSS_NO_BREAK);
    }

    /**
     * 以无分隔格式（{@value #FORMAT_YYYYMMDDHHMMSS_NO_BREAK}）格式化Date对象，日期和时间分开，如果Date对象为null或格式化失败，则返回null
     * @param date
     * @return [day, time]
     */
    public static String[] toStringOrNullWithNoBreakParts(Date date) {
        String full = toStringOrNull(date, FORMAT_YYYYMMDDHHMMSS_NO_BREAK);
        return null == full ? null
                : new String[] { full.substring(0, NO_BREAK_DAY_LENGTH), full.substring(NO_BREAK_DAY_LENGTH) };
    }

    /**
     * 以无分隔格式（{@value #FORMAT_YYYYMMDDHHMMSS_NO_BREAK}）获取当前时间整数格式表示，日期和时间分开，如果Date对象为null或格式化失败，则返回null
     * @return [day, time]
     */
    public static int[] toIntOrNullWithNoBreakParts(Date date) {
        String[] stringArr = toStringOrNullWithNoBreakParts(date);
        return null == stringArr ? null
                : new int[] { Integer.parseInt(stringArr[0]), Integer.parseInt(stringArr[1]) };
    }

    /**
     * 以无分隔格式（{@value #FORMAT_YYYYMMDDHHMMSSSSS_NO_BREAK}）格式化Date对象，日期和时间分开，如果Date对象为null或格式化失败，则返回null
     * @param date
     * @return [day, time]
     */
    public static String[] toStringOrNullWithNoBreakMillsParts(Date date) {
        String full = toStringOrNull(date, FORMAT_YYYYMMDDHHMMSSSSS_NO_BREAK);
        return null == full ? null
                : new String[] { full.substring(0, NO_BREAK_DAY_LENGTH), full.substring(NO_BREAK_DAY_LENGTH) };
    }

    /**
     * 以无分隔格式（{@value #FORMAT_YYYYMMDDHHMMSSSSS_NO_BREAK}）获取当前时间整数格式表示，日期和时间分开，如果Date对象为null或格式化失败，则返回null
     * @return [day, time]
     */
    public static int[] toIntOrNullWithNoBreakMillsParts(Date date) {
        String[] stringArr = toStringOrNullWithNoBreakMillsParts(date);
        return null == stringArr ? null
                : new int[] { Integer.parseInt(stringArr[0]), Integer.parseInt(stringArr[1]) };
    }

    /**
     * 把数字转化为时间字符串（{@value #FORMAT_HHMMSS_NO_BREAK}）
     * @param time
     * @return
     */
    public static String toTimeString(int time) {
        String timeString = Integer.toString(time);
        int timeStringLength = timeString.length();
        int diff = NO_BREAK_TIME_LENGTH - timeStringLength;
        if (diff > 0) {
            StringBuilder sBuilder = new StringBuilder(NO_BREAK_TIME_LENGTH);
            for (int i = 0; i < diff; i++) {
                sBuilder.append('0');
            }
            sBuilder.append(timeString);
            timeString = sBuilder.toString();
        } else if (diff < 0) {
            throw new IllegalArgumentException(timeString + " over " + NO_BREAK_TIME_LENGTH + " length");
        }
        return timeString;
    }

    /**
     * 转换时间格式，失败则返回原串
     * @param dateString
     * @param fromFormat
     * @param toFormat
     * @return
     */
    public static String transDateFormat(String dateString, String fromFormat, String toFormat) {
        if (!StringUtils.isBlank(dateString)) {
            Date date = toDateOrNull(dateString, fromFormat);
            if (null != date) {
                return toStringOrNull(date, toFormat);
            }
        }
        return dateString;
    }

    /**
     * 以默认格式（{@value #FORMAT_DEFAULT}）获取当前时间字符串
     * @return
     */
    public static String getCurrentDateString() {
        return toString(new Date(), FORMAT_DEFAULT);
    }

    /**
     * 获取当前时间字符串
     * @param format
     * @return
     */
    public static String getCurrentDateString(String format) {
        return toString(new Date(), format);
    }

    /**
     * 以无分隔格式（{@value #FORMAT_YYYYMMDDHHMMSS_NO_BREAK}）获取当前时间字符串，只取日期
     * @return
     */
    public static String getCurrentDateStringWithNoBreakDay() {
        return toStringOrNullWithNoBreakDay(new Date());
    }

    /**
     * 以无分隔格式（{@value #FORMAT_YYYYMMDDHHMMSS_NO_BREAK}）获取当前时间字符串，只取时间
     * @return
     */
    public static String getCurrentDateStringWithNoBreakTime() {
        return toStringOrNullWithNoBreakTime(new Date());
    }

    /**
     * 以无分隔格式（{@value #FORMAT_YYYYMMDDHHMMSS_NO_BREAK}）获取当前时间字符串
     * @return
     */
    public static String getCurrentDateStringWithNoBreakFormat() {
        return toStringOrNullWithNoBreakFormat(new Date());
    }

    /**
     * 以无分隔格式（{@value #FORMAT_YYYYMMDDHHMMSS_NO_BREAK}）获取当前时间字符串
     * @return [day, time]
     */
    public static String[] getCurrentDateStringWithNoBreakParts() {
        return toStringOrNullWithNoBreakParts(new Date());
    }

    /**
     * 以无分隔格式（{@value #FORMAT_YYYYMMDDHHMMSSSSS_NO_BREAK}）获取当前时间字符串
     * @return [day, time]
     */
    public static String[] getCurrentDateStringWithNoBreakMillsParts() {
        return toStringOrNullWithNoBreakMillsParts(new Date());
    }

    /**
     * 以无分隔格式（{@value #FORMAT_YYYYMMDDHHMMSS_NO_BREAK}）获取当前时间字符串，只取日期
     * @return
     */
    public static int getCurrentDateIntWithNoBreakDay() {
        String string = toStringOrNullWithNoBreakDay(new Date());
        return Integer.parseInt(string);
    }

    /**
     * 以无分隔格式（{@value #FORMAT_YYYYMMDDHHMMSS_NO_BREAK}）获取当前时间字符串，只取时间
     * @return
     */
    public static int getCurrentDateIntWithNoBreakTime() {
        String string = toStringOrNullWithNoBreakTime(new Date());
        return Integer.parseInt(string);
    }

    /**
     * 以无分隔格式（{@value #FORMAT_YYYYMMDDHHMMSS_NO_BREAK}）获取当前时间字符串（整数格式）
     * @return [day, time]
     */
    public static int[] getCurrentDateIntWithNoBreakParts() {
        return toIntOrNullWithNoBreakParts(new Date());
    }

    /**
     * 以无分隔格式（{@value #FORMAT_YYYYMMDDHHMMSSSSS_NO_BREAK}）获取当前时间字符串（整数格式）
     * @return [day, time]
     */
    public static int[] getCurrentDateIntWithNoBreakMillsParts() {
        return toIntOrNullWithNoBreakMillsParts(new Date());
    }

    /**
     * 获取当前的秒数（相对epoch）
     * @return
     */
    public static int getCurrentSecs() {
        return (int) (System.currentTimeMillis() / MILLIS_NUM_IN_SEC);
    }

    /**
     * 将毫秒转为秒
     * @return
     */
    public static int millisToSecs(long millis) {
        return (int) (millis / MILLIS_NUM_IN_SEC);
    }

    /**
     * 创建指定日期的日历
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Calendar createCalender(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);
        return calendar;
    }

    /**
     * 创建指定时间的日历
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Calendar createCalender(int year, int month, int day, int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, hour, minute, second);
        return calendar;
    }

    /**
     * 创建指定日期的时间对象
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static Date createDate(int year, int month, int day) {
        return createCalender(year, month, day).getTime();
    }

    /**
     * 创建指定时间的时间对象
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date createDate(int year, int month, int day, int hour, int minute, int second) {
        return createCalender(year, month, day, hour, minute, second).getTime();
    }

    /**
     * 将Date对象转换为日历对象
     * @param date
     * @return
     */
    public static Calendar toCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 将Date对象转换为指定地区的日历对象
     * @param date
     * @param locale
     * @return
     */
    public static Calendar toCalendar(Date date, Locale locale) {
        Calendar calendar = Calendar.getInstance(locale);
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 将Date对象转换为指定时区的日历对象
     * @param date
     * @param timeZone
     * @return
     */
    public static Calendar toCalendar(Date date, TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance(timeZone);
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 将Date对象转换为指定地区及时区的日历对象
     * @param date
     * @param timeZone
     * @param locale
     * @return
     */
    public static Calendar toCalendar(Date date, TimeZone timeZone, Locale locale) {
        Calendar calendar = Calendar.getInstance(timeZone, locale);
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 计算一年有多少天
     * @param startDate 开始时间
     * @return 一年的天数
     */
    public static int getDaysOfYear(Date startDate) {
        // 按开始时间创建日历
        Calendar calendar = toCalendar(startDate);
        // 获取开始时间在该年是第几天
        int startDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        // 往后翻一年
        calendar.add(Calendar.YEAR, 1);
        // 获取结束时间在该年（开始时间的下一年）是第几天
        int endDayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        // 一年的天数 = 365天 + （一年中第几天的偏差）
        int yearDays = STANDARD_DAYS_OF_YEAR + (endDayOfYear - startDayOfYear);
        // 返回结果
        return yearDays;
    }

    /**
     * 根据基准日历获取日历
     * @param baseCalendar 基准日历
     * @param calendarField 日历字段，使用{@link Calendar}的<code>field</code>常量表示
     * @param diff 时间差，以日历字段为单位，正数向更晚时间，负数向更早时间
     * @return
     */
    public static Calendar getCalendarFromBase(Calendar baseCalendar, int calendarField, int diff) {
        baseCalendar.add(calendarField, diff);
        return baseCalendar;
    }

    /**
     * 根据基准日历获取时间
     * @param baseCalendar 基准日历
     * @param calendarField 日历字段，使用{@link Calendar}的<code>field</code>常量表示
     * @param diff 时间差，以日历字段为单位，正数向更晚时间，负数向更早时间
     * @return
     */
    public static Date getDateFromBase(Calendar baseCalendar, int calendarField, int diff) {
        return getCalendarFromBase(baseCalendar, calendarField, diff).getTime();
    }

    /**
     * 根据基准时间获取日历
     * @param baseDate 基准时间
     * @param calendarField 日历字段，使用{@link Calendar}的<code>field</code>常量表示
     * @param diff 时间差，以日历字段为单位，正数向更晚时间，负数向更早时间
     * @return
     */
    public static Calendar getCalendarFromBase(Date baseDate, int calendarField, int diff) {
        // 按开始时间创建日历
        Calendar calendar = toCalendar(baseDate);
        calendar.add(calendarField, diff);
        return calendar;
    }

    /**
     * 根据基准时间获取时间
     * @param baseDate 基准时间
     * @param calendarField 日历字段，使用{@link Calendar}的<code>field</code>常量表示
     * @param diff 时间差，以日历字段为单位，正数向更晚时间，负数向更早时间
     * @return
     */
    public static Date getDateFromBase(Date baseDate, int calendarField, int diff) {
        return getCalendarFromBase(baseDate, calendarField, diff).getTime();
    }

    /**
     * 调整日历，将指定字段后的精度清除
     * @param calendar 原始日历
     * @param field 精确到该精度，使用{@link Calendar}的<code>field</code>常量表示
     * @return 清除精度后的日历
     */
    public static Calendar trimToAccuracy(Calendar calendar, int field) {
        switch (field) {
            case Calendar.YEAR:
                calendar.set(Calendar.MONTH, 0);
            case Calendar.MONTH:
                calendar.set(Calendar.DAY_OF_MONTH, 1);
            case Calendar.DAY_OF_MONTH:
            case Calendar.DAY_OF_WEEK:
            case Calendar.DAY_OF_YEAR:
            case Calendar.DAY_OF_WEEK_IN_MONTH:
                calendar.set(Calendar.HOUR_OF_DAY, 0);
            case Calendar.HOUR_OF_DAY:
                calendar.set(Calendar.MINUTE, 0);
            case Calendar.MINUTE:
                calendar.set(Calendar.SECOND, 0);
            case Calendar.SECOND:
                calendar.set(Calendar.MILLISECOND, 0);
            case Calendar.MILLISECOND:
                break;
            case Calendar.WEEK_OF_YEAR:
            case Calendar.WEEK_OF_MONTH:
                calendar.set(Calendar.DAY_OF_WEEK, 1);
                calendar.set(Calendar.HOUR_OF_DAY, 0);
                calendar.set(Calendar.MINUTE, 0);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);
                break;
            default:
                break;
        }
        return calendar;
    }

    /**
     * 调整时间，将指定字段后的精度清除
     * @param date 原始时间
     * @param field 精确到该精度，使用{@link Calendar}的<code>field</code>常量表示
     * @return 清除精度后的时间
     */
    public static Calendar trimToAccuracy(Date date, int field) {
        return trimToAccuracy(toCalendar(date), field);
    }

    /**
     * 调整时间，将年后的精度清除（就是该年第一天0时）
     * @param date 原始时间
     * @return 清除精度后的时间
     */
    public static Calendar trimToYear(Date date) {
        return trimToAccuracy(toCalendar(date), Calendar.YEAR);
    }

    /**
     * 调整时间，将月份后的精度清除（就是该月第一天0时）
     * @param date 原始时间
     * @return 清除精度后的时间
     */
    public static Calendar trimToMonth(Date date) {
        return trimToAccuracy(toCalendar(date), Calendar.MONTH);
    }

    /**
     * 调整时间，将日期后的精度清除（就是当天0时）
     * @param date 原始时间
     * @return 清除精度后的时间
     */
    public static Calendar trimToDay(Date date) {
        return trimToAccuracy(toCalendar(date), Calendar.DAY_OF_MONTH);
    }

    /**
     * 调整时间，将小时后的精度清除（就是该小时0分）
     * @param date 原始时间
     * @return 清除精度后的时间
     */
    public static Calendar trimToHour(Date date) {
        return trimToAccuracy(toCalendar(date), Calendar.HOUR_OF_DAY);
    }

    /**
     * 调整时间，将分钟后的精度清除（就是该分钟0秒）
     * @param date 原始时间
     * @return 清除精度后的时间
     */
    public static Calendar trimToMinute(Date date) {
        return trimToAccuracy(toCalendar(date), Calendar.MINUTE);
    }

    /**
     * 调整时间，将秒后的精度清除（就是清除毫秒信息）
     * @param date 原始时间
     * @return 清除精度后的时间
     */
    public static Calendar trimToSecond(Date date) {
        return trimToAccuracy(toCalendar(date), Calendar.SECOND);
    }

    /**
     * 调整时间，将周后的精度清除（就是本周第一天0时）
     * @param date 原始时间
     * @return 清除精度后的时间
     */
    public static Calendar trimToWeek(Date date) {
        return trimToAccuracy(toCalendar(date), Calendar.WEEK_OF_YEAR);
    }

    /**
     * 根据基准日历获取时间
     * @param baseCalendar 基准日历
     * @param baseAccuracy 基准日历精度，日历字段，使用{@link Calendar}的<code>field</code>常量表示
     * @param rollUnit 滚动单位，日历字段，使用{@link Calendar}的<code>field</code>常量表示
     * @param diff 时间差，以日历字段为单位，正数向更晚时间，负数向更早时间
     * @return
     */
    public static Calendar getCalendarFromBase(Calendar baseCalendar, int baseAccuracy, int rollUnit, int diff) {
        return getCalendarFromBase(trimToAccuracy(baseCalendar, baseAccuracy), rollUnit, diff);
    }

    /**
     * 根据基准时间获取时间
     * @param baseDate 基准时间
     * @param baseAccuracy 基准时间精度，日历字段，使用{@link Calendar}的<code>field</code>常量表示
     * @param rollUnit 滚动单位，日历字段，使用{@link Calendar}的<code>field</code>常量表示
     * @param diff 时间差，以日历字段为单位，正数向更晚时间，负数向更早时间
     * @return
     */
    public static Date getDateFromBase(Date baseDate, int baseAccuracy, int rollUnit, int diff) {
        return getCalendarFromBase(trimToAccuracy(baseDate, baseAccuracy), rollUnit, diff).getTime();
    }

    /**
     * 获取基准时间前的一个时间单位的时间，如“前一秒”<br />
     * 昨天的最后一秒可以这样获取：<code>getDateFrontOfBase(new date(), Calendar.DAY_OF_YEAR, Calendar.SECOND)</code>
     * @param baseDate 基准时间
     * @param baseAccuracy 基准时间精度，日历字段，使用{@link Calendar}的<code>field</code>常量表示
     * @param rollUnit 滚动单位，日历字段，使用{@link Calendar}的<code>field</code>常量表示
     * @return
     */
    public static Date getDateFrontOfBase(Date baseDate, int baseAccuracy, int rollUnit) {
        return getDateFromBase(baseDate, baseAccuracy, rollUnit, -1);
    }

    /**
     * 获取指定时间及指定精度的开始时间
     * @param date 原始时间
     * @param field 精确到该精度，使用{@link Calendar}的<code>field</code>常量表示
     * @return 调整精度后的时间
     */
    public static Calendar getStartOfDate(Date date, int field) {
        return trimToAccuracy(date, field);
    }

    /**
     * 获取指定时间及指定精度的结束时间
     * @param date 原始时间
     * @param field 精确到该精度，使用{@link Calendar}的<code>field</code>常量表示
     * @return 调整精度后的时间
     */
    public static Calendar getEndOfDate(Date date, int field) {
        Calendar calendar = trimToAccuracy(date, field);
        calendar.add(field, 1);
        calendar.add(Calendar.MILLISECOND, -1);
        return calendar;
    }

    /**
     * 获取指定时间当年的开始时间（就是该年第一天0时）。
     * @param date
     * @return
     */
    public static Calendar getStartOfYear(Date date) {
        return getStartOfDate(date, Calendar.YEAR);
    }

    /**
     * 获取指定时间当年的结束时间（就是该年最后一天0时最后一秒）。
     * @param date
     * @return
     */
    public static Calendar getEndOfYear(Date date) {
        return getEndOfDate(date, Calendar.YEAR);
    }

    /**
     * 获取指定时间当月的开始时间（就是该月第一天0时）。
     * @param date
     * @return
     */
    public static Calendar getStartOfMonth(Date date) {
        return getStartOfDate(date, Calendar.MONTH);
    }

    /**
     * 获取指定时间当月的结束时间（就是该月最后一天0时最后一秒）。
     * @param date
     * @return
     */
    public static Calendar getEndOfMonth(Date date) {
        return getEndOfDate(date, Calendar.MONTH);
    }

    /**
     * 获取指定时间当天的开始时间（00:00:00）。
     * @param date
     * @return
     */
    public static Calendar getStartOfDay(Date date) {
        return getStartOfDate(date, Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取指定时间当天的结束时间（23:59:59）。
     * @param date
     * @return
     */
    public static Calendar getEndOfDay(Date date) {
        return getEndOfDate(date, Calendar.DAY_OF_YEAR);
    }

    /**
     * 获取指定时间该小时的开始时间（就是该小时0分）。
     * @param date
     * @return
     */
    public static Calendar getStartOfHour(Date date) {
        return getStartOfDate(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取指定时间该小时的结束时间（就是该小时最后一秒）。
     * @param date
     * @return
     */
    public static Calendar getEndOfHour(Date date) {
        return getEndOfDate(date, Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取指定时间该分钟的开始时间（就是该分钟0秒）。
     * @param date
     * @return
     */
    public static Calendar getStartOfMinute(Date date) {
        return getStartOfDate(date, Calendar.MINUTE);
    }

    /**
     * 获取指定时间该分钟的结束时间（就是该分钟最后一秒）。
     * @param date
     * @return
     */
    public static Calendar getEndOfMinute(Date date) {
        return getEndOfDate(date, Calendar.MINUTE);
    }

    /**
     * 获取指定时间该秒的开始时间（就是该秒0毫秒）。
     * @param date
     * @return
     */
    public static Calendar getStartOfSecond(Date date) {
        return getStartOfDate(date, Calendar.SECOND);
    }

    /**
     * 获取指定时间该秒的结束时间（就是该秒999毫秒）。
     * @param date
     * @return
     */
    public static Calendar getEndOfSecond(Date date) {
        return getEndOfDate(date, Calendar.SECOND);
    }

    /**
     * 获取指定时间该周的开始时间（就是该周第一天0时）。
     * @param date
     * @return
     */
    public static Calendar getStartOfWeek(Date date) {
        return getStartOfDate(date, Calendar.WEEK_OF_YEAR);
    }

    /**
     * 获取指定时间该周的结束时间（就是该周最后一天0时最后一秒）。
     * @param date
     * @return
     */
    public static Calendar getEndOfWeek(Date date) {
        return getEndOfDate(date, Calendar.WEEK_OF_YEAR);
    }

    /**
     * date的下一天
     * @param date 参考时间
     * @return 下一天
     */
    public static Date getNextDate(Date date) {
        Calendar calendar = toCalendar(date);
        calendar.add(Calendar.DAY_OF_YEAR, 1);
        return calendar.getTime();
    }

    public static boolean isEpoch(Date date) {
        return null != date && 0 == date.getTime();
    }

    public static boolean isEpochOrNull(Date date) {
        return null == date || 0 == date.getTime();
    }

    /**
     * 获取从现在到2000-01-01 00:00:00的毫秒差
     * @return
     */
    public static long getCurrentMillisFromRef2000() {
        return System.currentTimeMillis() - MS_REF_2000;
    }

    /**
     * 获取从现在到2000-01-01 00:00:00的百毫秒差
     * @return
     */
    public static long getCurrentHundredMillisFromRef2000() {
        return getCurrentMillisFromRef2000() / HUNDRED;
    }

    /**
     * 获取从现在到2000-01-01 00:00:00的十毫秒差
     * @return
     */
    public static long getCurrentTenMillisFromRef2000() {
        return getCurrentMillisFromRef2000() / TEN;
    }

    /**
     * 获取从现在到2000-01-01 00:00:00的秒差
     * @return
     */
    public static long getCurrentSecsFromRef2000() {
        return getCurrentMillisFromRef2000() / MILLIS_NUM_IN_SEC;
    }

    /**
     * 获取从指定时间到2000-01-01 00:00:00的毫秒差
     * @param date 指定时间
     * @return
     */
    public static long getMillisFromRef2000(Date date) {
        return date.getTime() - MS_REF_2000;
    }

    /**
     * 获取从指定时间到2000-01-01 00:00:00的百毫秒差
     * @param date 指定时间
     * @return
     */
    public static long getHundredMillisFromRef2000(Date date) {
        return getMillisFromRef2000(date) / HUNDRED;
    }

    /**
     * 获取从指定时间到2000-01-01 00:00:00的十毫秒差
     * @param date 指定时间
     * @return
     */
    public static long getTenMillisFromRef2000(Date date) {
        return getMillisFromRef2000(date) / TEN;
    }

    /**
     * 获取从指定时间到2000-01-01 00:00:00的秒差
     * @param date 指定时间
     * @return
     */
    public static long getSecsFromRef2000(Date date) {
        return getMillisFromRef2000(date) / MILLIS_NUM_IN_SEC;
    }

    /**
     * 把以2000-01-01 00:00:00为基准的毫秒数转化为时间对象
     * @param ref2000Millis 以2000-01-01 00:00:00为基准的毫秒数
     * @return
     */
    public static Date getDateOfRef2000Millis(long ref2000Millis) {
        return new Date(ref2000Millis + MS_REF_2000);
    }

    /**
     * 把以2000-01-01 00:00:00为基准的百毫秒数转化为时间对象
     * @param ref2000HundredMillis 以2000-01-01 00:00:00为基准的百毫秒数
     * @return
     */
    public static Date getDateOfRef2000HundredMillis(long ref2000HundredMillis) {
        return new Date((ref2000HundredMillis + HUNDRED_MS_REF_2000) * HUNDRED);
    }

    /**
     * 把以2000-01-01 00:00:00为基准的十毫秒数转化为时间对象
     * @param ref2000TenMillis 以2000-01-01 00:00:00为基准的十毫秒数
     * @return
     */
    public static Date getDateOfRef2000TenMillis(long ref2000TenMillis) {
        return new Date((ref2000TenMillis + TEN_MS_REF_2000) * TEN);
    }

    /**
     * 把以2000-01-01 00:00:00为基准的秒数转化为时间对象
     * @param ref2000Secs 以2000-01-01 00:00:00为基准的秒数
     * @return
     */
    public static Date getDateOfRef2000Secs(long ref2000Secs) {
        return new Date((ref2000Secs + SEC_REF_2000) * MILLIS_NUM_IN_SEC);
    }

    /**
     * 计算两个日期的天数间隔（比较只精确到日期，也即按当天的0时比较）
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 天数间隔
     */
    public static int getDaysPeriod(Date startDate, Date endDate) {
        Calendar startCalender = trimToDay(startDate);
        Calendar endCalender = trimToDay(endDate);
        long startDateEpoch = startCalender.getTimeInMillis();
        long endDateEpoch = endCalender.getTimeInMillis();
        // 秒差
        long diffSecs = endDateEpoch / MILLIS_NUM_IN_SEC - startDateEpoch / MILLIS_NUM_IN_SEC;
        // 天数间隔
        return (int) (diffSecs / SECS_NUM_IN_DAY);
    }

    /**
     * 计算两个日期的天数间隔（比较只精确到日期，也即按当天的0时比较）
     * @param startDayString 开始时间（{@value #FORMAT_YYYYMMDD_NO_BREAK}格式的字符串）
     * @param endDayString 结束时间（{@value #FORMAT_YYYYMMDD_NO_BREAK}格式的字符串）
     * @return 天数间隔
     * @throws ParseException
     */
    public static int getDaysPeriod(String startDayString, String endDayString) throws ParseException {
        Date startDate = toDateWithNoBreakDay(startDayString);
        Date endDate = toDateWithNoBreakDay(endDayString);
        long startDateEpoch = startDate.getTime();
        long endDateEpoch = endDate.getTime();
        // 秒差
        long diffSecs = endDateEpoch / MILLIS_NUM_IN_SEC - startDateEpoch / MILLIS_NUM_IN_SEC;
        // 天数间隔
        return (int) (diffSecs / SECS_NUM_IN_DAY);
    }

    /**
     * 计算两个日期的天数间隔（比较只精确到日期，也即按当天的0时比较）
     * @param startDayInt 开始时间（{@value #FORMAT_YYYYMMDD_NO_BREAK}格式的字符串对应的整数）
     * @param endDayInt 结束时间（{@value #FORMAT_YYYYMMDD_NO_BREAK}格式的字符串对应的整数）
     * @return 天数间隔
     * @throws ParseException
     */
    public static int getDaysPeriod(int startDayInt, int endDayInt) throws ParseException {
        Date startDate = toDateWithNoBreakDay(startDayInt);
        Date endDate = toDateWithNoBreakDay(endDayInt);
        long startDateEpoch = startDate.getTime();
        long endDateEpoch = endDate.getTime();
        // 秒差
        long diffSecs = endDateEpoch / MILLIS_NUM_IN_SEC - startDateEpoch / MILLIS_NUM_IN_SEC;
        // 天数间隔
        return (int) (diffSecs / SECS_NUM_IN_DAY);
    }

    /**
     * 获取时间对象对应的星期几（周日为1，参照常量{@link Calendar#DAY_OF_WEEK}，如{@link Calendar#SUNDAY}、{@link Calendar#MONDAY}等）
     * @param date
     * @return
     * @see Calendar#DAY_OF_WEEK
     * @see Calendar#SUNDAY
     * @see Calendar#MONDAY
     * @see Calendar#TUESDAY
     * @see Calendar#WEDNESDAY
     * @see Calendar#THURSDAY
     * @see Calendar#FRIDAY
     * @see Calendar#SATURDAY
     */
    public static int getWeekDay(Date date) {
        Calendar calendar = toCalendar(date);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获取时间对象对应的星期几（周一为1，周日为7）
     * @param date
     * @return
     */
    public static int getWeekDayMonBase(Date date) {
        Calendar calendar = toCalendar(date);
        int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
        if (Calendar.SUNDAY == weekDay) {
            return WEEK_DAY_MON_BASE_SUNDAY;
        } else {
            return weekDay - 1;
        }
    }

    public static int getStartDayOfYearForDayInt(int dayInt) {
        return dayInt / DAY_INT_YEAR_CLEAR_MARK * DAY_INT_YEAR_CLEAR_MARK + DAY_INT_YEAR_START_MD;
    }

    public static int getEndDayOfYearForDayInt(int dayInt) {
        return dayInt / DAY_INT_YEAR_CLEAR_MARK * DAY_INT_YEAR_CLEAR_MARK + DAY_INT_YEAR_END_MD;
    }

    public static int getStartDayOfNextYearForDayInt(int dayInt) {
        return (dayInt / DAY_INT_YEAR_CLEAR_MARK + 1) * DAY_INT_YEAR_CLEAR_MARK + DAY_INT_YEAR_START_MD;
    }

    public static int getEndDayOfBeforeYearForDayInt(int dayInt) {
        return (dayInt / DAY_INT_YEAR_CLEAR_MARK - 1) * DAY_INT_YEAR_CLEAR_MARK + DAY_INT_YEAR_END_MD;
    }

    /**
     * 以无分隔格式（{@value #FORMAT_YYYYMMDD_NO_BREAK}）获取日历的日期部分整数格式表示，如果Calendar对象为null，则返回-1
     * @return [day, time]
     */
    public static int toIntOrNullWithNoBreakDayPart(Calendar calendar) {
        if (null == calendar) {
            return -1;
        }
        return calendar.get(Calendar.YEAR) * DAY_INT_YEAR_CLEAR_MARK
                + (calendar.get(Calendar.MONTH) + 1) * DAY_INT_MONTH_CLEAR_MARK
                + calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 以无分隔格式（{@value #FORMAT_HHMMSS_NO_BREAK}）获取日历的时间部分整数格式表示，如果Calendar对象为null，则返回-1
     * @return [day, time]
     */
    public static int toIntOrNullWithNoBreakTimePart(Calendar calendar) {
        if (null == calendar) {
            return -1;
        }
        return calendar.get(Calendar.HOUR_OF_DAY) * TIME_INT_HOUR_CLEAR_MARK
                + calendar.get(Calendar.MINUTE) * TIME_INT_MINUTE_CLEAR_MARK + calendar.get(Calendar.SECOND);
    }

}

