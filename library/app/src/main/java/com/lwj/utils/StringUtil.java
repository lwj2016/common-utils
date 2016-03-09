package com.lwj.utils;

/**
 * Created by lwj on 2016/3/8.
 * lwjfork@gmail.com
 */
public class StringUtil {


    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String formatNotNull(String string) {
        if (string == null) {
            return "";
        }
        return string;
    }

    public static boolean isValid(String string) {

        return !isEmpty(string);
    }
}
