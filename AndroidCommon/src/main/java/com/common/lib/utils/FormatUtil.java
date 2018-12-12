package com.common.lib.utils;



import com.common.lib.utils.log.LogUtil;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Created by lwj on 16/5/16.
 * Des:
 */
public final class FormatUtil {


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


    public static String getNumberFormat(double money, String formatStr) {
        NumberFormat nf = new DecimalFormat(formatStr);
        return nf.format(money);
    }

    public static String getNumberFormat(String money, String formatStr) {
        NumberFormat nf = new DecimalFormat(formatStr);
        return nf.format(str2Float(money));
    }

    public static String format(String str, Object... args) {
        return StrUtil.getFormatStr(str, args);
    }

    public static <T> T defaultIfNull(T str, T defaultValue) {
        return str == null ? defaultValue : str;
    }


}
