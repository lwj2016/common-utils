package com.lwj.utils;

import com.lwj.utils.log.LogUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by lwj on 16/5/16.
 * Des:
 */
public class FormatUtil {


    public static int str2Int(String str) {
        int l = 0;
        try {
            l = Integer.valueOf(str);
        } catch (NumberFormatException e) {
            LogUtil.e("str2Integer %s", "error");
        }
        return l;
    }

    public static float str2Float(String str) {
        float l = 0f;
        try {
            l = Float.valueOf(str);
        } catch (NumberFormatException e) {
            LogUtil.e("str2Float %s", "error");
        }
        return l;
    }

    public static long str2Long(String str) {
        long l = 0L;
        try {
            l = Long.valueOf(str);
        } catch (NumberFormatException e) {
            LogUtil.e("str2Long %s", "error");
        }
        return l;
    }


    public static double str2Double(String str) {
        double l = 0d;
        try {
            l = Double.valueOf(str);
        } catch (NumberFormatException e) {
            LogUtil.e("str2Double %s", "error");
        }
        return l;
    }


    public static String getFormat(double money, String formatStr) {
        NumberFormat nf = new DecimalFormat(formatStr);
        return nf.format(money);
    }

    public static String getFormat(String money, String formatStr) {
        NumberFormat nf = new DecimalFormat(formatStr);
        return nf.format(str2Float(money));
    }
}
