package com.common.lib.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Created:2018/6/28
 * User：lwjfork
 * Email:lwjfork@gmail.com
 * Des:
 * ====================
 */

public final  class MathUtil {
    /**
     * 求 地板运算
     *
     * @param f float
     * @return long
     */
    public static long floor(double f) {
        return (long) Math.floor(f);
    }

    /**
     * 求 天棚 运算
     *
     * @param f float
     * @return long
     */
    public static long ceil(double f) {
        return (long) Math.ceil(f);
    }


    /**
     * 求 四舍五入
     *
     * @param f float
     * @return long
     */
    public static long round(double f) {
        return Math.round(f);
    }

    /**
     * 求 四舍五入
     *
     * @param f float
     * @return long
     */
    public static int round(float f) {
        return Math.round(f);
    }


    /**
     * @param num          待转换数字
     * @param newScale     保留小数点位数
     * @param roundingMode 舍入规则
     * @return 转换后数字
     */
    public static double getBigDecimal(double num, int newScale, RoundingMode roundingMode) {
        BigDecimal decimal = new BigDecimal(num);
        BigDecimal newDecimal = decimal.setScale(newScale, roundingMode);
        return newDecimal.doubleValue();
    }

    /**
     * 默认四舍五入
     *
     * @param num      待转换数字
     * @param newScale 保留小数点位数
     * @return 生成结果
     */
    public static double getBigDecimal(double num, int newScale) {
        return getBigDecimal(num, newScale, RoundingMode.HALF_UP);
    }

    /**
     * 默认保留两位小数
     *
     * @param num          带转换数字
     * @param roundingMode 舍入规则
     * @return 结果
     */
    public static double getBigDecimal(double num, RoundingMode roundingMode) {
        return getBigDecimal(num, 2, roundingMode);
    }


    /**
     * 2335.4234  to 2335.42
     * 2335.4254  to 2335.43
     * 四舍五入
     *
     * @param num 待转换数字
     * @return 转换后数字
     */
    public static double getBigDecimal(double num) {
        return getBigDecimal(num, 2);
    }

    /**
     * 浮点数相加
     *
     * @param num1
     * @param num2
     * @return
     */
    public static double doubleAdd(double num1, double num2) {
        BigDecimal decimal1 = new BigDecimal(num1);
        BigDecimal decimal2 = new BigDecimal(num2);
        return decimal1.add(decimal2).doubleValue();
    }

    /**
     * 浮点数相减
     *
     * @param num1
     * @param num2
     * @return
     */
    public static double doubleSubtract(double num1, double num2) {
        BigDecimal decimal1 = new BigDecimal(num1);
        BigDecimal decimal2 = new BigDecimal(num2);
        return decimal1.subtract(decimal2).doubleValue();
    }

    /**
     * 浮点数相乘
     *
     * @param num1
     * @param num2
     * @return
     */
    public static double doubleMultiply(double num1, double num2) {
        BigDecimal decimal1 = new BigDecimal(num1);
        BigDecimal decimal2 = new BigDecimal(num2);
        return decimal1.multiply(decimal2).doubleValue();
    }

    /**
     * 浮点数相除
     *
     * @param num1
     * @param num2
     * @return
     */
    public static double doubleDivide(double num1, double num2) {
        return doubleDivide(num1, num2, RoundingMode.HALF_UP);
    }

    /**
     * 浮点数相除
     *
     * @param num1
     * @param num2
     * @param roundingMode
     * @return
     */
    public static double doubleDivide(double num1, double num2, RoundingMode roundingMode) {
        BigDecimal decimal1 = new BigDecimal(num1);
        BigDecimal decimal2 = new BigDecimal(num2);
        return decimal1.divide(decimal2, roundingMode).doubleValue();
    }


    public static void main(String[] args) {
        double sourceD = 1223.45632d;
        System.out.println(getBigDecimal(sourceD));
        System.out.println(getBigDecimal(sourceD, 2));
        System.out.println(getBigDecimal(sourceD, 3));
        System.out.println(getBigDecimal(sourceD, 2, RoundingMode.DOWN));

        double x = 0.03;
        double y = 0.02;
        System.out.println(x - y);
        System.out.println(doubleSubtract(x, y));
    }
}
