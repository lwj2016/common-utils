package com.utils.test.json.gson;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.lwj.utils.log.LogUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by lwj on 2018/1/5.
 * lwjfork@gmail.com
 */

abstract class LogAdapter<T> implements JsonDeserializer<T> {

    @SuppressWarnings("unchecked")
    private Class<T> getType() {
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType parameterizedType = (ParameterizedType) type;
        return (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    void errorLog(JsonElement jsonElement) {
        LogUtil.e("We expect %s but jsonStrR is %s", getType().getSimpleName(), jsonElement.toString());
    }

}
