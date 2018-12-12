package com.common.lib.utils;

import org.json.JSONObject;

/**
 * Created by lwj on 2018/7/31.
 * lwjfork@gmail.com
 */

public final  class JSONUtil {


    public static boolean isHave(JSONObject jsonObject, String key) {
        return jsonObject.has(key);
    }

}
