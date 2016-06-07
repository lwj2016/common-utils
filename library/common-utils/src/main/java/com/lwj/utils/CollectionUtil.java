package com.lwj.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by lwj on 16/3/23.
 * Des: 集合工具类
 */
public class CollectionUtil {

    public static boolean isListMoreOne(List mArrayList) {
        return mArrayList != null && mArrayList.size() > 0;
    }

    public static <T> List<T> arrayToList(T... objects) {
        return Arrays.asList(objects);
    }

    public static <T> ArrayList<T> arrayToArrayList(T... objects) {
        return new ArrayList<>(Arrays.asList(objects));
    }

    public static String[] strlistToArray(ArrayList<String> list) {

        return listToArray(list);
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] listToArray(ArrayList<T> list) {

        Object[] objs = list.toArray();
        return (T[]) objs;
    }

}
