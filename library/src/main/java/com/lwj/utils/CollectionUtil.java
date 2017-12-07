package com.lwj.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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

        return listToArray(list, String.class);
    }


    @SuppressWarnings("unchecked")
    public static <T> T[] listToArray(List<T> list, Class<T> clazz) {
        return list.toArray((T[]) Array.newInstance(clazz, list.size()));
    }

}
