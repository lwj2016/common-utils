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

public class IntegerAdapter implements JsonDeserializer<Integer> {

    @Override
    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            return json.getAsInt();
        } catch (Exception e) { // 转 int 类型错误  尝试转 double 类型
            try {
                Number number = json.getAsNumber();
                return number.intValue();
            } catch (Exception formatE) {
                LogUtil.e("Can't cast to be %s", "Integer");
                return 0;
            }
        }
    }

}
