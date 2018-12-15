package com.common.lib.json;


import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lwj on 2018/5/6.
 * lwjfork@gmail.com
 */

public class JSONUtil {

    private static BaseJsonConverter iJsonConverter;

    public static void setJsonConverter(BaseJsonConverter iJsonConverter) {
        iJsonConverter = iJsonConverter;
    }


    public static <T> T jsonStr2Obj(String jsonStr, Class<T> tClass) {
        return iJsonConverter.jsonStr2Obj(jsonStr, tClass);
    }


    public static <T> String obj2JsonStr(T model) {
        return iJsonConverter.obj2JsonStr(model);
    }


    public static <T> ArrayList<T> jsonStr2ObjList(String jsonStr, Class<T> tClass) {
        return iJsonConverter.jsonStr2ObjList(jsonStr, tClass);
    }


    public static <T> T[] jsonStr2ObjArray(String jsonStr, Class<T> tClass) {
        return iJsonConverter.jsonStr2ObjArray(jsonStr, tClass);
    }


    public static <T> String objList2JsonStr(ArrayList<T> list) {
        return iJsonConverter.objList2JsonStr(list);
    }


    @SuppressWarnings("unchecked")
    public static <T> String objArray2JsonStr(T... modelArray) {
        return iJsonConverter.objArray2JsonStr(modelArray);
    }


    public static void setPrettyFormat(boolean isPretty) {
        iJsonConverter.setPrettyFormat(isPretty);
    }


    public static String formatJson(String json) {
        return iJsonConverter.formatJson(json);
    }


    public static <K, V> HashMap<K, V> jsonStr2Map(String jsonStr, Class<K> kClass, Class<V> vClass) {
        return iJsonConverter.jsonStr2Map(jsonStr, kClass, vClass);
    }


    public static <K, V> String map2Json(HashMap<K, V> map) {
        return iJsonConverter.map2Json(map);
    }

    public static boolean isHave(JSONObject jsonObject, String key) {
        return jsonObject.has(key);
    }


    public static <T> T fromJson(String jsonStr, Type type) {
        return iJsonConverter.fromJson(jsonStr, type);
    }

}
