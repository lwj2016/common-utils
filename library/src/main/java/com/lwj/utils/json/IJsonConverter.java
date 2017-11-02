package com.lwj.utils.json;

import java.util.ArrayList;

/**
 * Created by lwj on 2017/10/11.
 * lwjfork@gmail.com
 */

public interface IJsonConverter {


    public <T> T jsonStr2Obj(String jsonStr, Class<T> tClass);


    public <T> String obj2JsonStr(T model);

    public <T> ArrayList<T> jsonStr2ObjList(String jsonStr, Class<T> tClass);

    public <T> T[] jsonStr2ObjArray(String jsonStr, Class<T> tClass);

    @SuppressWarnings("unchecked")
    public <T> String objArray2JsonStr(T... modelArray);

    public <T> String objList2JsonStr(ArrayList<T> list);

    public void setPrettyFormat(boolean isPretty);

    public String formatJson(String json);
}
