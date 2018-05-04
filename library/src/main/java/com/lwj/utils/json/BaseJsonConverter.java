package com.lwj.utils.json;

import java.util.ArrayList;

/**
 * Created by lwj on 2017/10/11.
 * lwjfork@gmail.com
 */

public abstract class BaseJsonConverter implements IJsonConverter {

    private IJsonConverter converter;

    protected void setJsonConverter(IJsonConverter iJsonConverter) {
        this.converter = iJsonConverter;
    }


    /**
     * @see #setJsonConverter(IJsonConverter)
     */
    private void checkNotNull() {
        if (converter == null) {
            throw new RuntimeException("IJsonConverter must be init,");
        }
    }

    @Override
    public <T> T jsonStr2Obj(String jsonStr, Class<T> tClass) {
        checkNotNull();
        return converter.jsonStr2Obj(jsonStr, tClass);
    }

    @Override
    public <T> String obj2JsonStr(T model) {
        checkNotNull();
        return converter.obj2JsonStr(model);
    }

    @Override
    public <T> ArrayList<T> jsonStr2ObjList(String jsonStr, Class<T> tClass) {
        checkNotNull();
        return converter.jsonStr2ObjList(jsonStr, tClass);
    }

    @Override
    public <T> T[] jsonStr2ObjArray(String jsonStr, Class<T> tClass) {
        checkNotNull();
        return converter.jsonStr2ObjArray(jsonStr, tClass);
    }

    @Override
    public <T> String objList2JsonStr(ArrayList<T> list) {
        checkNotNull();
        return converter.objList2JsonStr(list);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> String objArray2JsonStr(T... modelArray) {
        checkNotNull();
        return converter.objArray2JsonStr(modelArray);
    }

    @Override
    public void setPrettyFormat(boolean isPretty) {
        checkNotNull();
        converter.setPrettyFormat(isPretty);
    }

    @Override
    public String formatJson(String json) {
        checkNotNull();
        return converter.formatJson(json);
    }
}
