package com.utils.test.json;

import com.lwj.utils.json.BaseJsonConverter;
import com.utils.test.json.fastJson.FastJsonConverter;
import com.utils.test.json.gson.GsonConverter;

/**
 * Created by lwj on 2017/10/30.
 * lwjfork@gmail.com
 */

public class JsonConverter extends BaseJsonConverter {

    private JsonConverter() {
        setJsonConverter(new FastJsonConverter());
    }

    public static JsonConverter getInstance() {

        return JsonConverterHolder.instance;
    }


    private static class JsonConverterHolder {
        private static final JsonConverter instance = new JsonConverter();
    }

}
