package com.utils.test.json.fastJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.lwj.utils.json.IJsonConverter;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by lwj on 2017/10/30.
 * lwjfork@gmail.com
 */

public class FastJsonConvert implements IJsonConverter {


    @Override
    public <T> T jsonStr2Obj(String jsonStr, Class<T> tClass) {
        if (jsonStr == null) {
            return null;
        }
        return JSON.parseObject(jsonStr, tClass);
    }

    @Override
    public <T> String obj2JsonStr(T model) {
        if (model == null) {
            return null;
        }
        if (isPretty) {
            return JSON.toJSONString(model, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.PrettyFormat);
        } else {
            return JSON.toJSONString(model, SerializerFeature.DisableCircularReferenceDetect);
        }
    }

    @Override
    public <T> ArrayList<T> jsonStr2ObjList(String jsonStr, Class<T> tClass) {
        if (jsonStr == null) {
            return new ArrayList<T>();
        }
        if ("[]".equals(jsonStr)) {
            return new ArrayList<T>();
        }

        return (ArrayList<T>) JSON.parseArray(jsonStr, tClass);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] jsonStr2ObjArray(String jsonStr, Class<T> tClass) {
        T[] arrayT = (T[]) Array.newInstance(tClass, JSONArray.parseArray(jsonStr).size());
        return (T[]) JSON.parseObject(jsonStr, arrayT.getClass());
    }

    @Override
    public <T> String objArray2JsonStr(T[] modelArray) {
        if (modelArray == null) {
            return "[]";
        }
        if (modelArray.length == 0) {
            return "[]";
        }

        if (isPretty) {
            return JSON.toJSONString(modelArray, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.PrettyFormat);
        } else {
            return JSON.toJSONString(modelArray, SerializerFeature.DisableCircularReferenceDetect);
        }
    }

    @Override
    public <T> String objList2JsonStr(ArrayList<T> list) {
        if (list == null) {
            return "[]";
        }
        if (list.size() == 0) {
            return "[]";
        }
        if (isPretty) {
            return JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.PrettyFormat);
        } else {
            return JSON.toJSONString(list, SerializerFeature.DisableCircularReferenceDetect);
        }
    }

    private boolean isPretty = false;

    @Override
    public void setPrettyFormat(boolean isPretty) {
        this.isPretty = isPretty;
    }

    @Override
    public String formatJson(String json) {
        if (json == null) {
            return "null";
        }
        try {
            if (json.startsWith("[") && json.endsWith("]")) { // 数组
                org.json.JSONArray jsonArray = new org.json.JSONArray(json);
                return formatArrayJson(jsonArray);
            } else if (json.startsWith("{") && json.endsWith("}")) {
                JSONObject jsonObj = new JSONObject(json);
                return formatObjJson(jsonObj);
            }
            return json;
        } catch (Exception e) {
            return json;
        }
    }

    private String formatObjJson(JSONObject jsonObject) throws JSONException {

        String str = jsonObject.toString(4);
        return str;
    }

    private String formatArrayJson(org.json.JSONArray jsonArray) throws JSONException {
        return jsonArray.toString(4);
    }
}
