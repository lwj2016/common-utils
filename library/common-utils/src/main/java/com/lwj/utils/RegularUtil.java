package com.lwj.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lwj on 16/3/23.
 * Des: 正则工具类
 */
public class RegularUtil {
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
        boolean tag = true;
        Pattern pattern = Pattern.compile(regex);
        Matcher mat = pattern.matcher(email);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }

    /**
     * 判断是否是数字
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 验证手机号
     *
     * @param str
     * @return
     */
    public static boolean isPhone(String str) {
        String p = "(^(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7})$";

        return isPhone(str, p);
    }

    /**
     * 验证手机号
     *
     * @param str
     * @return
     */
    public static boolean isPhone(String str, String regex) {
        Pattern p = Pattern
                .compile(regex);
        Matcher m = p.matcher(str);
        return m.matches();
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
}
