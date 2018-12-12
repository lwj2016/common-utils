package com.common.lib.utils;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Created by lwj on 2016/3/8.
 * lwjfork@gmail.com
 */
public final class StrUtil {


    public static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static String safeStr(String string) {
        return emptyIfNull(string);
    }

    public static String emptyIfNull(String string) {
        return string == null ? "" : string;
    }

    public static String nullIfEmpty(@Nullable String str) {
        return isEmpty(str) ? null : str;
    }

    public static boolean isValid(String string) {
        return !isEmpty(string);
    }


    public static String trim(String string) {
        return string.trim();
    }

    /**
     * @param res
     * @param args
     * @return
     */
    public static String getFormatStr(String res, Object... args) {
        return String.format(res, args);
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


    public static String replaceFirst(String source, String regex, String replacement) {
        return source.replaceFirst(regex, replacement);
    }

    public static String replaceAll(String source, String regex, String replacement) {
        return source.replaceAll(regex, replacement);
    }

    public static CharSequence concat(CharSequence... array) {
        return TextUtils.concat(array);
    }

    /**
     * 拼接字符串
     *
     * @param delimiter 分割符
     * @param tokens    数组数据
     * @return String
     */
    public static String join(CharSequence delimiter, Object... tokens) {
        if (OSUtils.hasN_24()) {
            StringJoiner joiner = new StringJoiner(delimiter);
            for (Object token : tokens) {
                joiner.add(token.toString());
            }
            return joiner.toString();
        }
        return TextUtils.join(delimiter, tokens);
    }


    /**
     * 拼接字符串
     *
     * @param delimiter 分割符
     * @param tokens    数组数据
     * @return String
     */
    public static String join(CharSequence delimiter, Iterable tokens) {
        return TextUtils.join(delimiter, tokens);
    }


    public static String randomStr(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(randomChar());
        }
        return stringBuilder.toString();
    }


    public static char randomChar(String src) {
        int length = src.length();
        int index = (int) (Math.random() * length);
        return src.charAt(index);
    }


    /**
     * 随机一个 0-9A-Za-z 的字符
     *
     * @return
     */
    public static char randomChar() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            stringBuilder.append(i);
        }
        for (char i = 'a'; i <= 'z'; i++) {
            stringBuilder.append(i);
        }

        for (char i = 'A'; i <= 'Z'; i++) {
            stringBuilder.append(i);
        }
        return randomChar(stringBuilder.toString());
    }


    public static void main(String[] args) {
        System.out.println(StrUtil.replaceAll("com.google.test", "\\.", "_"));
        System.out.println(StrUtil.replaceFirst("com.google.test", "\\.", "_"));

        System.out.println(randomStr(10));
        System.out.println(randomStr(20));
    }

}
