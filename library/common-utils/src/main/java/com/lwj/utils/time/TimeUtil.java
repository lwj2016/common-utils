package com.lwj.utils.time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by lwj on 16/5/13.
 * Des:
 */
public class TimeUtil {


public static String dateFormatYMDHMS="yyyy-MM-dd HH:mm:ss";
public static String dateFormatYMD="yyyy-MM-dd";
public static String dateFormatYM="yyyy-MM";
public static String dateFormatYMDHM="yyyy-MM-dd HH:mm";
public static String dateFormatMD="MM/dd";
public static String dateFormatHMS="HH:mm:ss";
public static String dateFormatHM="HH:mm";
public static String dateFormatMS="mm:ss";


public static Date getDateByFormat(String strDate, String format,TimeUtil timeUtil){
        SimpleDateFormat mSimpleDateFormat=new SimpleDateFormat(format);
        Date date=null;

        try{
        date=mSimpleDateFormat.parse(strDate);
        }catch(ParseException var5){
        var5.printStackTrace();
        }

        return date;
        }
        }
