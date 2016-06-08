package com.lwj.utils;

/**
 * Created by lwj on 16/6/7.
 * Des:
 */
public class Preconditions {

    public static <T> T checkNotNUll(T value, String message) {
        if (value == null) {
            throw new NullPointerException(message);
        }
       return value;
    }
}
