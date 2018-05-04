package com.utils.test.json.gson.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by lwj on 2018/1/5.
 * lwjfork@gmail.com
 */

public class DoubleAdapter extends LogAdapter<Double> {

    @Override
    public Double deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return json.getAsDouble();
        } catch (Exception e) { // 转 int 类型错误  尝试转 double 类型
            try {
                Number number = json.getAsNumber();
                return number.doubleValue();
            } catch (Exception formatE) {
                errorLog(json);
                return 0.0d;
            }
        }
    }

}