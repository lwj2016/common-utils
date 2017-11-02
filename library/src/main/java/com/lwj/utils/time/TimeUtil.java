package com.lwj.utils.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by lwj on 16/5/13.
 * Des:
 */
public class TimeUtil {


    public static String dateFormatYMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static String dateFormatYMD = "yyyy-MM-dd";
    public static String dateFormatYM = "yyyy-MM";
    public static String dateFormatYMDHM = "yyyy-MM-dd HH:mm";
    public static String dateFormatMD = "MM/dd";
    public static String dateFormatHMS = "HH:mm:ss";
    public static String dateFormatHM = "HH:mm";
    public static String dateFormatMS = "mm:ss";


    public static String dateFormatCYMDHMS = "yyyy年MM月dd日 HH时mm分ss秒";
    public static String dateFormatCYMD = "yyyy年MM月dd日";
    public static String dateFormatCYM = "yyyy年MM月";
    public static String dateFormatCYMDHM = "yyyy年MM月dd日 HH时mm分";
    public static String dateFormatCMD = "MM/dd";
    public static String dateFormatCHMS = "HH时mm分秒";
    public static String dateFormatCHM = "HH时mm分";
    public static String dateFormatCMS = "mm分ss秒";


    public static long getNanos(long time) {
        return getNanos(time, TimeUnit.SECONDS);
    }

    public static long getMicros(long time) {
        return getMicros(time, TimeUnit.SECONDS);
    }


    public static long getMills(long time) {
        return getMills(time, TimeUnit.SECONDS);
    }

    public static long getSeconds(long time) {
        return getSeconds(time, TimeUnit.SECONDS);
    }

    public static long getMinutes(long time) {
        return getMinutes(time, TimeUnit.SECONDS);
    }

    public static long getHours(long time) {
        return getHours(time, TimeUnit.SECONDS);
    }

    public static long getDays(long time) {
        return getDays(time, TimeUnit.SECONDS);
    }


    public static long getNanos(long time, TimeUnit timeUnit) {
        return timeUnit.toNanos(time);
    }

    public static long getMicros(long time, TimeUnit timeUnit) {
        return timeUnit.toMicros(time);
    }


    public static long getMills(long time, TimeUnit timeUnit) {
        return timeUnit.toMillis(time);
    }

    public static long getSeconds(long time, TimeUnit timeUnit) {
        return timeUnit.toSeconds(time);
    }

    public static long getMinutes(long time, TimeUnit timeUnit) {
        return timeUnit.toMinutes(time);
    }

    public static long getHours(long time, TimeUnit timeUnit) {
        return timeUnit.toHours(time);
    }

    public static long getDays(long time, TimeUnit timeUnit) {
        return timeUnit.toDays(time);
    }


    /**
     * @param strDate 时间字符串
     * @param format  格式
     * @return 日期 date
     */
    public static Date getDateByFormat(String strDate, String format) {
        ;
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format, Locale.getDefault());
        Date date = null;

        try {
            date = mSimpleDateFormat.parse(strDate);
        } catch (ParseException var5) {
            var5.printStackTrace();
        }
        return date;
    }

    public static long getNanosByFormat(String strDate, String format) {
        Date date = getDateByFormat(strDate, format);
        return getNanos(date.getTime(), TimeUnit.MILLISECONDS);
    }

    public static long getMicrosByFormat(String strDate, String format) {
        Date date = getDateByFormat(strDate, format);
        return getMicros(date.getTime(), TimeUnit.MILLISECONDS);
    }


    public static long getMillsByFormat(String strDate, String format) {
        Date date = getDateByFormat(strDate, format);
        return getMills(date.getTime(), TimeUnit.MILLISECONDS);
    }

    public static long getSecondsByFormat(String strDate, String format) {
        Date date = getDateByFormat(strDate, format);
        return getSeconds(date.getTime(), TimeUnit.MILLISECONDS);
    }

    public static long getMinutesByFormat(String strDate, String format) {
        Date date = getDateByFormat(strDate, format);
        return getMinutes(date.getTime(), TimeUnit.MILLISECONDS);
    }

    public static long getHoursByFormat(String strDate, String format) {
        Date date = getDateByFormat(strDate, format);
        return getHours(date.getTime(), TimeUnit.MILLISECONDS);
    }

    public static long getDaysByFormat(String strDate, String format) {
        Date date = getDateByFormat(strDate, format);
        return getDays(date.getTime(), TimeUnit.MILLISECONDS);
    }


    /**
     * @param time     时间
     * @param format   格式
     * @param timeUnit 时间单元
     * @return 日期字符串
     */
    public static String getFormatByTime(String format, long time, TimeUnit timeUnit) {
        Date date = new Date(timeUnit.toMillis(time));
        return getFormatByDate(date, format);
    }

    /**
     * @param time   时间  seconds
     * @param format 格式
     * @return 日期字符串
     */
    public static String getFormatByTime(String format, long time) {
        return getFormatByTime(format, time, TimeUnit.SECONDS);
    }


    /**
     * @param date   时间
     * @param format 格式
     * @return 日期字符串
     */
    public static String getFormatByDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format, Locale.getDefault());
        return dateFormat.format(date);
    }

    /**
     * @param milliSeconds 毫秒值
     * @param format       格式
     * @return 日期字符串
     */
    public static String getFormatByMills(String format, long milliSeconds) {
        return getFormatByTime(format, milliSeconds, TimeUnit.MILLISECONDS);
    }

    /**
     * @param seconds 时间
     * @param format  格式
     * @return 日期字符串
     */
    public static String getFormatBySeconds(String format, long seconds) {
        return getFormatByTime(format, seconds, TimeUnit.SECONDS);
    }

    /**
     * @param hours  时间小时
     * @param format 格式
     * @return 日期字符串
     */
    public static String getFormatByHours(String format, long hours) {
        return getFormatByTime(format, hours, TimeUnit.HOURS);
    }

    /**
     * @param minutes 时间分
     * @param format  格式
     * @return 日期字符串
     */
    public static String getFormatByMinutes(String format, long minutes) {
        return getFormatByTime(format, minutes, TimeUnit.MINUTES);
    }

    /**
     * @param days   时间天
     * @param format 格式
     * @return 日期字符串
     */
    public static String getFormatByDays(String format, long days) {
        return getFormatByTime(format, days, TimeUnit.DAYS);
    }


    /**
     * get the years offset
     *
     * @param firstDate
     * @param endDate
     * @return
     */
    public static int getOffsetYears(Date firstDate, Date endDate) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(firstDate);
        c2.setTime(endDate);
        if (c1.after(c2)) {
            c1 = c2;
            c2.setTime(firstDate);
        }
        int offsetYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        return offsetYears;
    }

    public static int getOffsetMonths(Date firstDate, Date endDate, boolean isIgnoreYear) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(firstDate);
        c2.setTime(endDate);
        if (c1.after(c2)) {
            c1 = c2;
            c2.setTime(firstDate);
        }
        int offsetYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        if (offsetYears == 0 || isIgnoreYear) {
            return c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        } else {
            return (c1.getMaximum(Calendar.MONTH) + 1) * (offsetYears - 1) + c1.getMaximum(Calendar.MONTH) - c1.get(Calendar.MONTH) + c2.get(Calendar.MONTH) + 1;
        }
    }

    /**
     * get the months offset
     *
     * @param firstDate
     * @param endDate
     * @return
     */
    public static int getOffsetMonths(Date firstDate, Date endDate) {
        return getOffsetMonths(firstDate, endDate, true);
    }

    public static int getOffsetDays(Date firstDate, Date endDate) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(firstDate);
        c2.setTime(endDate);
        if (c1.after(c2)) {
            c1 = c2;
            c2.setTime(firstDate);
        }
        int offsetYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        if (offsetYears == 0) {// 同一年
            int offsetMonth = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
            if (offsetMonth == 0) {
                return c2.get(Calendar.DAY_OF_MONTH) - c1.get(Calendar.DAY_OF_MONTH);
            } else {
                int days = 0;
                int year = c1.get(Calendar.YEAR);
                int mDay = c1.get(Calendar.DAY_OF_MONTH);
                int mDay1 = c2.get(Calendar.DAY_OF_MONTH);
                days = c1.getActualMaximum(Calendar.DAY_OF_MONTH) - mDay + mDay1;
                for (int i = c1.get(Calendar.MONTH) + 1; i < c2.get(Calendar.DAY_OF_MONTH); i++) {
                    c1.set(Calendar.MONTH, i);
                    days = days + c1.getActualMaximum(Calendar.DAY_OF_MONTH);
                }
                return days;
            }
        } else {  //  不是同一年
            int mDay = c1.get(Calendar.DAY_OF_YEAR);
            int days = c1.getActualMaximum(Calendar.DAY_OF_YEAR) - mDay;
            days = days + c2.get(Calendar.DAY_OF_YEAR);
            int year = c1.get(Calendar.YEAR);
            int year1 = c2.get(Calendar.YEAR);
            for (int i = year + 1; i < year1; i++) {
                c1.set(Calendar.YEAR, i);
                days = days + c1.getActualMaximum(Calendar.DAY_OF_YEAR);
            }
            return days;
        }
    }

    /**
     * 通过日期获取当月的含有的天数
     *
     * @param date
     * @return
     */
    public static int getTotalDaysInMonth(Date date) {

        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        return c1.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 通过日期获取当年的含有的天数
     *
     * @param date
     * @return
     */
    public static int getTotalDaysInYear(Date date) {

        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        return c1.getActualMaximum(Calendar.DAY_OF_YEAR);
    }


    /**
     * 获取 天数偏移  忽略 年 月
     *
     * @param firstDate
     * @param secondDate
     * @return
     */
    public static int getOffsetDayIngoreYM(Date firstDate, Date secondDate) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(firstDate);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(secondDate);
        return c2.get(Calendar.DAY_OF_MONTH) - c1.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取 日期 距离当前日期的 年 月  日
     * ps 通常用于计算生日
     *
     * @param date
     * @return
     */
    public static int[] getAge(Date date) {
        Date today = new Date();
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(today);
        int[] m = new int[3];
        m[0] = c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
        m[1] = c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
        m[2] = c1.get(Calendar.DAY_OF_MONTH) - c2.get(Calendar.DAY_OF_MONTH);
        return m;
    }

    /**
     * 是否是同一年
     *
     * @param firstDate
     * @param endDate
     * @return
     */
    public static boolean isSameYear(Date firstDate, Date endDate) {
        return getOffsetYears(firstDate, endDate) == 0;
    }

    /**
     * @param date
     * @return
     */
    public static boolean isSameYear(Date date) {
        return isSameYear(date, new Date());
    }

    private static boolean isSameMonth(Date date, Date date1, boolean isIgnoreY) {
        return getOffsetMonths(date, date1, isIgnoreY) == 0;
    }

    /**
     * 是否是同一个月
     *
     * @param date
     * @param date1
     * @return
     */
    public static boolean isSameMonth(Date date, Date date1) {

        return isSameMonth(date, date1, false);
    }


    /**
     * 是否是同一月
     *
     * @param date
     * @return
     */
    public static boolean isSameMonth(Date date) {

        return isSameMonth(date, false);
    }

    /**
     * 是否是同一月
     *
     * @param date
     * @return
     */
    public static boolean isSameMonth(Date date, boolean isIngoreYear) {
        return isSameMonth(date, new Date(), isIngoreYear);
    }
}