package com.utils.test.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lwj.utils.json.IJsonConverter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by lwj on 2017/10/30.
 * lwjfork@gmail.com
 */

public class GsonConverter implements IJsonConverter {

    private Gson gson;

    private boolean isPretty = false;

    @Override
    public void setPrettyFormat(boolean isPretty) {
        if (isPretty != this.isPretty) {
            GsonBuilder builder = builder();
            if (isPretty) {
                builder.setPrettyPrinting();
            }
            gson = builder.create();
        }
        this.isPretty = isPretty;
    }

    private GsonBuilder builder() {
        return new GsonBuilder()
                .registerTypeAdapter(Integer.class, new IntegerAdapter()) // Integer
                .registerTypeAdapter(Integer.TYPE, new IntegerAdapter())  // int
                .registerTypeAdapter(Float.class, new FloatAdapter())   //  Float
                .registerTypeAdapter(Float.TYPE, new FloatAdapter()) // float
                .registerTypeAdapter(Double.class, new DoubleAdapter()) // Double
                .registerTypeAdapter(Double.TYPE, new DoubleAdapter()) // float
                .registerTypeAdapter(Long.class, new LongAdapter())// Long
                .registerTypeAdapter(Long.TYPE, new LongAdapter());// long
    }

    private void initGson() {
        if (gson == null) {
            GsonBuilder builder = builder();
            if (isPretty) {
                builder.setPrettyPrinting();
            }
            gson = builder.create();
        }
    }

    @Override
    public <T> T jsonStr2Obj(String jsonStr, Class<T> tClass) {
        initGson();

        return gson.fromJson(jsonStr, tClass);
    }

    @Override
    public <T> String obj2JsonStr(T model) {
        initGson();
        return gson.toJson(model);
    }

    @Override
    public <T> ArrayList<T> jsonStr2ObjList(String jsonStr, Class<T> tClass) {
        initGson();
        Type type = TypeToken.getParameterized(ArrayList.class, tClass).getType();
        return gson.fromJson(jsonStr, type);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] jsonStr2ObjArray(String jsonStr, Class<T> tClass) {
        initGson();
        Type type = TypeToken.getArray(tClass).getType();
        return gson.fromJson(jsonStr, type);
    }

    @Override
    public <T> String objList2JsonStr(ArrayList<T> list) {
        initGson();
        return gson.toJson(list);
    }

    @Override
    public <T> String objArray2JsonStr(T[] modelArray) {
        initGson();
        return gson.toJson(modelArray);
    }

    @Override
    public String formatJson(String json) {
        initGson();

        if (json == null) {
            return "null";
        }
        try {
            if (json.startsWith("[") && json.endsWith("]")) { // 数组
                JSONArray jsonArray = new JSONArray(json);
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

        return jsonObject.toString(4);
    }

    private String formatArrayJson(JSONArray jsonArray) throws JSONException {
        return jsonArray.toString(4);
    }


}
