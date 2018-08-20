package com.lwj.utils;

import java.lang.reflect.Method;

/**
 * Created by lwj on 2018/8/3.
 * lwjfork@gmail.com
 */

public  final class ReflectUtil {


    public static Method[] getDeclaredMethods(Class clazz) {
        return clazz.getDeclaredMethods();
    }

    public static Method[] getPublicMethods(Class clazz) {
        return clazz.getMethods();
    }

    @SuppressWarnings("unchecked")
    public static Method getMethod(String methodName, Class clazz, Class<?>... parameterTypes) throws NoSuchMethodException {
        return clazz.getMethod(methodName, parameterTypes);
    }


}
