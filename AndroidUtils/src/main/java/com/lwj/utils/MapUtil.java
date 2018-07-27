package com.lwj.utils;

import java.util.Map;

/**
 * Created by lwj on 2018/7/24.
 * lwjfork@gmail.com
 */

public class MapUtil {


    /**
     * Gets safe value.
     *
     * @param <K>          the type parameter
     * @param <V>          the type parameter
     * @param map          the map
     * @param key          the key
     * @param defaultValue the default value
     * @return the safe value
     * @author Created by lwjfork on 2018/07/24 16:11
     */
    public static <K, V> V getSafeValue(Map<K, V> map, K key, V defaultValue) {
        V realValue = map.get(key);
        if (realValue == null) {
            return defaultValue;
        }
        return realValue;
    }


}
