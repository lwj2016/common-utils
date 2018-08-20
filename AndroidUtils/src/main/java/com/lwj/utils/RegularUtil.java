package com.lwj.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lwj on 16/3/23.
 * Des: 正则工具类
 */
public final  class RegularUtil {
    /**
     * 验证输入的邮箱格式是否符合
     *
     * @param email
     * @return 是否合法
     */
    public static boolean isEmail(String email) {
        String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        return isEmail(email, pattern1);
    }

    /**
     * 验证是否是邮箱
     *
     * @param email
     * @param regex
     * @return
     */
    public static boolean isEmail(String email, String regex) {
        return isMatch(email, regex);
    }

    /**
     * 判断是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        String regularStr = "[0-9]+";
        return isMatch(str, regularStr);
    }

    /**
     * 判断是否是字母
     *
     * @param str
     * @return
     */
    public static boolean isLetter(String str) {
        String regularStr = "[a-zA-Z]+";
        return isMatch(str, regularStr);
    }

    /**
     * 验证手机号,考虑+86
     *
     * @param str
     * @return
     */
    public static boolean isPhone(String str) {
        String p = "(^(\\+86)?(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7})$";

        return isPhone(str, p);
    }

    /**
     * 验证手机号
     *
     * @param str
     * @return
     */
    public static boolean isPhone(String str, String regularStr) {
        return isMatch(str, regularStr);
    }

    /**
     * @param c
     * @return 是否为中文
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * @param str
     * @return 是否仅仅包含数字和字母
     */
    public static boolean isNumericAndLetter(String str) {
        String regularStr = "([0-9]+[a-zA-Z]+)|([a-zA-Z]+[0-9]+)[0-9a-zA-Z]*";
        return isMatch(str, regularStr);
    }

    /**
     * Is match boolean.
     *
     * @param str        the str
     * @param regularStr the regular str
     * @return the boolean
     * @author Created by lwjfork on 2018/07/23 16:03
     */
    public static boolean isMatch(String str, String regularStr) {
        Pattern pattern = Pattern.compile(regularStr);
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }


    public static void main(String[] args) {

        boolean isTrue = isPhone("+8618715218142");


        System.out.println(isTrue);


    }

}
