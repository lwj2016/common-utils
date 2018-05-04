package com.utils.test.json.gson.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by lwj on 2017/10/11.
 * lwjfork@gmail.com
 */

public class IntegerAdapter extends LogAdapter<Integer> {

    @Override
    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return json.getAsInt();
        } catch (Exception e) { // 转 int 类型错误  尝试转 double 类型
            try {
                Number number = json.getAsNumber();
                return number.intValue();
            } catch (Exception formatE) {
                errorLog(json);
                return 0;
            }
        }
    }

}
