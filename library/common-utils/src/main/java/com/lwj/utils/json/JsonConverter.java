package com.lwj.utils.json;

import java.util.ArrayList;

/**
 * Created by lwj on 2017/10/11.
 * lwjfork@gmail.com
 */

public abstract class JsonConverter implements IJsonConverter {

    IJsonConverter converter;

    public void setJsonConverter(IJsonConverter iJsonConverter) {
        this.converter = iJsonConverter;
    }


    public void checkNotNull() {
        if (converter == null) {
            throw new RuntimeException("IJsonConverter must be init");
        }
    }

    @Override
    public <T> T jsonStr2Model(String jsonStr, Class<T> tClass) {
        checkNotNull();
        return converter.jsonStr2Model(jsonStr, tClass);
    }

    @Override
    public <T> String model2JsonStr(T model) {
        checkNotNull();
        return converter.model2JsonStr(model);
    }

    @Override
    public <T> ArrayList<T> jsonStr2ModelList(String jsonStr, Class<T> tClass) {
        checkNotNull();
        return converter.jsonStr2ModelList(jsonStr, tClass);
    }

    @Override
    public <T> T[] jsonStr2ModelArray(String jsonStr, Class<T> tClass) {
        checkNotNull();
        return converter.jsonStr2ModelArray(jsonStr, tClass);
    }

    @Override
    public <T> String models2JsonStr(ArrayList<T> list) {
        checkNotNull();
        return converter.model2JsonStr(list);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> String models2JsonStr(T... modelArray) {
        checkNotNull();
        return converter.models2JsonStr(modelArray);
    }
}
