package com.lwj.utils.log;

/**
 * Created by lwj on 16/6/13.
 * Des:
 */
public class DebugUtil {


    public static <T> String getTag(Class<T> tClass) {
        if (tClass == null) {
            return "null";
        }
        return tClass.getSimpleName();
    }

    public static <T> String getTag(Object object) {
        if (object == null) {
            return "null";
        }
        return object.getClass().getSimpleName();
    }
}
