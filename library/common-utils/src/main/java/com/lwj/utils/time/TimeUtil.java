package com.lwj.utils.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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


    /**
     * @param strDate 时间字符串
     * @param format  格式
     * @return 日期 date
     */
    public static Date getDateByFormat(String strDate, String format) {
        SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat(format);
        Date date = null;

        try {
            date = mSimpleDateFormat.parse(strDate);
        } catch (ParseException var5) {
            var5.printStackTrace();
        }
        return date;
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
     * @param strDate 时间字符串
     * @param format  格式
     * @return 日期的毫秒值
     */
    public static long getMilliSecondsByFormat(String strDate, String format) {
        long l = 0L;
        l = getDateByFormat(strDate, format).getTime();

        return TimeUnit.MILLISECONDS.toMillis(l);
    }

    /**
     * @param strDate 时间字符串
     * @param format  格式
     * @return 日期的秒值
     */
    public static long getSecondsByFormat(String strDate, String format) {

        return TimeUnit.MILLISECONDS.toSeconds(getMilliSecondsByFormat(strDate, format));
    }

    /**
     * @param strDate 时间字符串
     * @param format  格式
     * @return 日期的分值
     */
    public static long getMinutesByFormat(String strDate, String format) {
        return TimeUnit.MILLISECONDS.toMinutes(getMilliSecondsByFormat(strDate, format));
    }

    /**
     * @param strDate 时间字符串
     * @param format  格式
     * @return 日期的小时值
     */
    public static long getHoursByFormat(String strDate, String format) {
        return TimeUnit.MILLISECONDS.toHours(getMilliSecondsByFormat(strDate, format));
    }

    /**
     * @param strDate 时间字符串
     * @param format  格式
     * @return 日期的天数值
     */
    public static long getDaysByFormat(String strDate, String format) {
        return TimeUnit.MILLISECONDS.toDays(getMilliSecondsByFormat(strDate, format));
    }

    /**
     * @param date   时间
     * @param format 格式
     * @return 日期字符串
     */
    public static String getFormatByDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * @param milliSeconds 毫秒值
     * @param format       格式
     * @return 日期字符串
     */
    public static String getFormatByMilliSeconds(String format, long milliSeconds) {
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

    public static int getOffsetMonths(Date firstDate, Date endDate) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(firstDate);
        c2.setTime(endDate);
        if (c1.after(c2)) {
            c1 = c2;
            c2.setTime(firstDate);
        }
        int offsetYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
        if (offsetYears == 0) {
            return c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        } else {
            return (c1.getMaximum(Calendar.MONTH) + 1) * (offsetYears - 1) + c1.getMaximum(Calendar.MONTH) - c1.get(Calendar.MONTH) + c2.get(Calendar.MONTH) + 1;
        }
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
                c1.set(Calendar.YEAR,i);
                days = days + c1.getActualMaximum(Calendar.DAY_OF_YEAR);
            }
            return days;
        }
    }


    public static int getCountDaysInMonth(int month, int year) {
        int count = -1;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                count = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                count = 30;
                break;
            case 2:
                if (year % 4 == 0)
                    count = 29;
                else
                    count = 28;
                if ((year % 100 == 0) & (year % 400 != 0))
                    count = 28;
        }
        return count;
    }
}
