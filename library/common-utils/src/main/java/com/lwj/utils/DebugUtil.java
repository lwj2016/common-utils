package com.lwj.utils;

import java.io.File;

/**
 * Created by lwj on 16/6/13.
 * Des:
 */
public class DebugUtil {

    public static void main(String[] args){
        File file = new File("holy shit");

        System.out.println(getTag(file));
        System.out.println(getTag(File.class));
    }

    public static <T> String getTag(Class<T> tClass){
        if(tClass == null){
            return "null";
        }
        return tClass.getSimpleName();
    }

    public static <T> String getTag(Object object){
        if(object == null){
            return "null";
        }
        return object.getClass().getSimpleName();
    }
}
