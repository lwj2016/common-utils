package com.lwj.utils.json;

import java.util.ArrayList;

/**
 * Created by lwj on 2017/10/11.
 * lwjfork@gmail.com
 */

public interface IJsonConverter {

    public <T> T jsonStr2Model(String jsonStr, Class<T> tClass);


    public <T> String model2JsonStr(T model);

    public <T> ArrayList<T> jsonStr2ModelList(String jsonStr, Class<T> tClass);

    public <T> T[] jsonStr2ModelArray(String jsonStr, Class<T> tClass);

    public <T> String models2JsonStr(ArrayList<T> list);

    public <T> String models2JsonStr(T... modelArray);

}
