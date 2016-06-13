package com.lwj.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
     * 将输入流转换为字符串
     *
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append((line));
                // sb.append((line + "\n"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
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

}
