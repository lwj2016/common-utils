package com.lwj.utils;

import com.lwj.utils.log.LogUtil;

/**
 * Created by lwj on 16/5/16.
 * Des:
 */
public class FormatUtil {
    /**
     *  求 地板运算
     * @param f    float
     * @return  long
     */
    public static long floor(double f) {
        return (long) Math.floor(f);
    }

    /**
     *  求 天棚 运算
     * @param f  float
     * @return  long
     */
    public static long ceil(double f) {
        return (long) Math.ceil(f);
    }

    public static int strToInteger(String str) {
        int l = 0;
        try {
            l = Integer.valueOf(str);
        } catch (NumberFormatException e) {
            LogUtil.e("strToInteger %s", "error");
        }
        return l;
    }

    public static float strToFloat(String str) {
        float l = 0f;
        try {
            l = Float.valueOf(str);
        } catch (NumberFormatException e) {
            LogUtil.e("strToFloat %s", "error");
        }
        return l;
    }

    public static long strToLong(String str) {
        long l = 0L;
        try {
            l = Long.valueOf(str);
        } catch (NumberFormatException e) {
            LogUtil.e("strToLong %s", "error");
        }
        return l;
    }


    public static double strToDouble(String str) {
        double l = 0d;
        try {
            l = Double.valueOf(str);
        } catch (NumberFormatException e) {
            LogUtil.e("strToDouble %s", "error");
        }
        return l;
    }
}
