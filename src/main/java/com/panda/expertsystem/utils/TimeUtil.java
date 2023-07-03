package com.panda.expertsystem.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @Author: lhw
 * @Date: 2023-07-03-11:19
 * @Description: 时间工具类
 */
public class TimeUtil {
    /**
     * 北京时间
     */
    private static final ZoneOffset BEIJING_ZONE = ZoneOffset.of("+8");
    /**
     * 标准日期时间格式，精确到秒：yyyy-MM-dd HH:mm:ss
     */
    private static final String NORM_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /**
     * 标准日期格式：yyyy-MM-dd
     */
    private static final String NORM_DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 标准日期格式：yyyy-MM-dd
     */
    private static final String YEAR = "yyyy";
    /**
     * 标准日期格式：yyyy-MM-dd
     */
    private static final String MONTH = "MM";
    /**
     * 标准日期格式：yyyy-MM-dd
     */
    private static final String DAY = "MM";

    /**
     * 获取系统当前日期
     *
     * @return LocalDate
     */
    public static LocalDate getLocalDate() {
        return LocalDate.now();
    }

    /**
     * 获取系统当前日期+时间
     *
     * @return LocalDate
     */
    public static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * 转换成时间戳
     *
     * @param localDateTime 当前日期时间
     * @return 时间戳
     */
    public static Long toTimestamp(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.toEpochSecond(BEIJING_ZONE);
    }

    /**
     * 转换成时间戳
     *
     * @param localDate 当前日期
     * @return 时间戳
     */
    public static Long toTimestamp(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return localDate.atStartOfDay(BEIJING_ZONE).toEpochSecond();
    }

    /**
     * 标准日期字符串转Long的13位毫秒时间戳
     *
     * @param dateStr 标准日期字符串
     * @return 13位毫秒时间戳
     * @throws ParseException 异常
     */
    public static Long toTimestamp(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(NORM_DATETIME_PATTERN);
        Date date = sdf.parse(dateStr);
        if (date == null) {
            return null;
        }
        return date.getTime();
    }

    /**
     * 转换成Date
     *
     * @param localDateTime 当前日期时间
     * @return Date日期
     */
    public static Date toDate(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDateTime.atZone(BEIJING_ZONE);
        Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }

    /**
     * 转换成Date
     *
     * @param localDate 当前日期
     * @return Date日期
     */
    public static Date toDate(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(BEIJING_ZONE);
        Instant instant = zonedDateTime.toInstant();
        return Date.from(instant);
    }

    /**
     * 转换成时间字符串
     *
     * @param localDateTime 当前日期时间
     * @return 时间字符串
     */
    public static String toStringTime(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.format(DateTimeFormatter.ofPattern(NORM_DATETIME_PATTERN));
    }

    /**
     * 时间戳转年月日
     *
     * @param stamp 时间戳
     * @return 年月日
     */
    public static String toStringTime(Long stamp) {
        return toStringTime(toLocalDateTime(stamp));
    }

    /**
     * 转换成时间字符串
     *
     * @param localDate 当前日期
     * @return 时间字符串
     */
    public static String toStringTime(LocalDate localDate) {
        if (localDate == null) {
            return null;
        }
        return localDate.format(DateTimeFormatter.ofPattern(NORM_DATE_PATTERN));
    }

    /**
     * 转换成日期时间
     *
     * @param text 时间字符串
     * @return 日期时间
     */
    public static LocalDateTime parseLocalDateTime(CharSequence text) {
        if (text == null) {
            return null;
        }
        return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(NORM_DATETIME_PATTERN));
    }

    /**
     * 转换成日期
     *
     * @param text 时间字符串
     * @return 日期
     */
    public static LocalDate parseLocalDate(String text) {
        if (text == null) {
            return null;
        }
        return LocalDate.parse(text, DateTimeFormatter.ofPattern(NORM_DATE_PATTERN));
    }

    /**
     * 转换成日期时间
     *
     * @param timestamp 时间戳
     * @return 日期时间
     */
    public static LocalDateTime toLocalDateTime(Long timestamp) {
        if (timestamp == null || timestamp == 0) {
            return null;
        }
        return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
    }

    /**
     * 转换成日期
     *
     * @param timestamp 时间戳
     * @return 日期
     */
    public static LocalDate toLocalDate(Long timestamp) {
        if (timestamp == null || timestamp == 0) {
            return null;
        }
        return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();
    }

    /**
     * 13位的豪秒级别的时间戳转“yyyy-MM-dd HH:mm:ss”
     *
     * @param trackTime =  1546071846000
     */
    public static String formatTrackTime(Long trackTime) {
        return new SimpleDateFormat(NORM_DATETIME_PATTERN).format(trackTime);
    }

    /**
     * 获取某天的时间
     *
     * @param index 为正表示当前时间加天数，为负表示当前时间减天数
     * @return String
     */
    public static String getTimeDay(int index) {
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat(NORM_DATETIME_PATTERN);
        calendar.add(Calendar.DAY_OF_MONTH, index);
        return fmt.format(calendar.getTime());
    }

    /**
     * 获取某天的时间,支持自定义时间格式
     *
     * @param simpleDateFormat 时间格式,yyyy-MM-dd HH:mm:ss
     * @param index            为正表示当前时间加天数，为负表示当前时间减天数
     * @return String
     */
    public static String getTimeDay(String simpleDateFormat, int index) {
        TimeZone tz = TimeZone.getTimeZone("Asia/Shanghai");
        TimeZone.setDefault(tz);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat fmt = new SimpleDateFormat(simpleDateFormat);
        calendar.add(Calendar.DAY_OF_MONTH, index);
        return fmt.format(calendar.getTime());
    }

    /**
     * 毫秒时间戳转分钟
     *
     * @param timeMillis 毫秒时间戳
     * @return 返回分钟
     */
    public static String timeMillisToMin(long timeMillis) {
        return String.valueOf(timeMillis / 1000 / 60);
    }

    /**
     * 13位的豪秒级别的时间戳转“yyyy-MM-dd”
     *
     * @param trackTime =  1546071846000
     */
    public static String formatTrackTimeTwo(Long trackTime) {
        if (trackTime==null){
            return null;
        }
        return new SimpleDateFormat(NORM_DATE_PATTERN).format(trackTime);
    }

    /**
     * Date返回年月日
     * @param date
     * @return
     */
// 先注释
//    public static DateVo getYearMonthDay(Date date){
//        SimpleDateFormat sdf = new SimpleDateFormat(NORM_DATE_PATTERN);
//        Calendar c = Calendar.getInstance();
//        c.setTime(date);
//        return new DateVo(c.get(Calendar.YEAR),c.get(Calendar.MONTH) + 1,c.get(Calendar.DAY_OF_MONTH));
//    }

    /**
     * 将Date转化成字符串
     * @param date
     * @return
     */
    public static String dateToString(Date date){
        if(date==null){
            return null;
        }
        return new SimpleDateFormat(NORM_DATE_PATTERN).format(date);
    }
}
