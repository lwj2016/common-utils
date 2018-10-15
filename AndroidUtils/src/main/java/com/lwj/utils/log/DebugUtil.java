package com.lwj.utils.log;

/**
 * Created by lwj on 16/6/13.
 * Des:
 */
public class DebugUtil {


    public static String getSimpleTag(Class<?> tClass) {
        if (tClass == null) {
            return "null";
        }
        return tClass.getSimpleName();
    }

    public static String getSimpleTag(Object object) {
        if (object == null) {
            return "null";
        }
        return object.getClass().getSimpleName();
    }


    public static String getTag(Class<?> tClass) {
        if (tClass == null) {
            return "null";
        }
        return tClass.getName();
    }

    public static String getTag(Object object) {
        if (object == null) {
            return "null";
        }
        return object.getClass().getName();
    }


}
