package com.lwj.utils;

import java.nio.charset.Charset;
import java.util.Arrays;

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


    /**
     * 数组转 字符串
     *
     * @param array
     * @return
     */
    public static String toString(Object[] array) {
        return Arrays.toString(array);
    }

    public static String toString(Object[] array, String seg) {
        if (array == null || array.length == 0) {
            return "null";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(array[0].toString());
        int length = array.length;
        for (int i = 1; i < length; i++) {
            sb.append(seg);
            sb.append(array[i]);
        }
        return sb.toString();
    }

    public static byte[] str2Bytes(String str) {

        if (isEmpty(str)) {
            return new byte[]{};
        }
        return str.getBytes(Charset.defaultCharset());
    }

    public static byte[] str2Bytes(String str, String charSetName) {
        return str2Bytes(str, Charset.forName(charSetName));
    }

    public static byte[] str2Bytes(String str, Charset charset) {
        byte[] result;
        if (isEmpty(str)) {
            result = new byte[]{};
        } else {
            result = str.getBytes(charset);
        }
        return result;
    }


    public static int getByteLength(String str) {
        return getByteLength(str, Charset.defaultCharset());
    }

    public static int getByteLength(String str, String charSetName) {

        return getByteLength(str, Charset.forName(charSetName));
    }


    public static int getByteLength(String str, Charset charset) {

        return str2Bytes(str, charset).length;
    }
}
