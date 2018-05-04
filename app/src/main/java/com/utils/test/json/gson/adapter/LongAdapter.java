package com.utils.test.json.gson.adapter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by lwj on 2017/10/11.
 * lwjfork@gmail.com
 */

public class LongAdapter extends LogAdapter<Long> {

    @Override
    public Long deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return json.getAsLong();
        } catch (Exception e) { // 转 int 类型错误  尝试转 double 类型
            try {
                Number number = json.getAsNumber();
                return number.longValue();
            } catch (Exception formatE) {
                errorLog(json);
                return 0L;
            }
        }
    }

}
