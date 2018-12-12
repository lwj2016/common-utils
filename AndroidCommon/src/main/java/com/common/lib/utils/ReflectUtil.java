package com.common.lib.utils;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by lwj on 2018/8/3.
 * lwjfork@gmail.com
 */

public final class ReflectUtil {


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

    /**
     * @param raw  实际类型
     * @param args 泛型参数类型
     * @return 带参数的实际泛型类型
     */
    public static ParameterizedType type(final Class raw, final Type... args) {
        return new ParameterizedType() {
            public Type getRawType() {
                return raw;
            }

            public Type[] getActualTypeArguments() {
                return args;
            }

            public Type getOwnerType() {
                return null;
            }
        };
    }
}
