package com.utils.test.json;

import com.lwj.utils.json.JsonConverter;
import com.utils.test.json.fastJson.FastJsonConvert;

/**
 * Created by lwj on 2017/10/30.
 * lwjfork@gmail.com
 */

public class TestJsonConverter extends JsonConverter {

    private TestJsonConverter() {
        setJsonConverter(new FastJsonConvert());
    }

    private static TestJsonConverter instance;

    public static TestJsonConverter getInstance() {
        if (instance == null) {
            instance = new TestJsonConverter();
        }
        return instance;
    }

}
