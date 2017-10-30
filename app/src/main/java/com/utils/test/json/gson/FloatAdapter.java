package com.utils.test.json.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.lwj.utils.log.LogUtil;

import java.lang.reflect.Type;

/**
 * Created by lwj on 2017/10/11.
 * lwjfork@gmail.com
 */

public class FloatAdapter implements JsonDeserializer<Float> {

    @Override
    public Float deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return json.getAsFloat();
        } catch (Exception e) { // 转 int 类型错误  尝试转 double 类型
            try {
                Number number = json.getAsNumber();
                return number.floatValue();
            } catch (Exception formatE) {
                LogUtil.e("Can't cast to be %s", "Float");
                return 0.0f;
            }
        }
    }

}