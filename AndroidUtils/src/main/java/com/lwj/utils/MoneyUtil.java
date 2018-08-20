package com.lwj.utils;

/**
 * Created:2018/6/28
 * User：lwjfork
 * Email:lwjfork@gmail.com
 * Des:
 * ====================
 */

public final class MoneyUtil {

    /**
     * 保留两位小数
     *
     * @param money
     * @return
     */
    public static String getFormatMoney(double money) {
        return FormatUtil.getNumberFormat(money, "#.##");
    }


    /**
     * 保留两位小数
     *
     * @param money
     * @return
     */
    public static String getFormatDotMoney(double money) {
        return FormatUtil.getNumberFormat(money + "", "#,###.##");
    }



    /**
     * 保留两位小数
     *
     * @param money
     * @return
     */
    public static String getFormatMoney(String money) {
        return FormatUtil.getNumberFormat(money, "#.##");
    }


    /**
     * 保留两位小数
     *
     * @param money
     * @return
     */
    public static String getFormatDotMoney(String money) {
        return FormatUtil.getNumberFormat(money + "", "#,###.##");
    }



    public static void main(String[] args) {
        double d = 554545.4545454;
        System.out.println(getFormatMoney(d));
        System.out.println(getFormatDotMoney(d));
        System.out.println(FormatUtil.getNumberFormat(d, "#,###.####"));

        int x = 110330114;
        System.out.println(getFormatMoney(x));
        System.out.println(getFormatDotMoney(x));
        System.out.println(FormatUtil.getNumberFormat(x, "#,###.####"));

    }

}
