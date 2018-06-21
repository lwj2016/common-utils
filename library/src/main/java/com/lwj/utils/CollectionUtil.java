package com.lwj.utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by lwj on 16/3/23.
 * Des: 集合工具类
 */
public class CollectionUtil {


    public static boolean isListMoreOne(Collection mArrayList) {
        return mArrayList != null && mArrayList.size() > 0;
    }

    public static <T> List<T> array2List(T... objects) {
        return Arrays.asList(objects);
    }

    public static <T> ArrayList<T> array2ArrayList(T... objects) {
        ArrayList<T> result = new ArrayList<>();
        result.addAll(Arrays.asList(objects));
        return result;
    }

    public static String[] strlist2Array(ArrayList<String> list) {
        return list2Array(list, String.class);
    }


    @SuppressWarnings("unchecked")
    public static <T> T[] list2Array(List<T> list, Class<T> clazz) {
        return list.toArray((T[]) Array.newInstance(clazz, list.size()));
    }

}
