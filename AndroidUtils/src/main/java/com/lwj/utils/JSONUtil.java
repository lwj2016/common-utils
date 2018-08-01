package com.lwj.utils;

import org.json.JSONObject;

/**
 * Created by lwj on 2018/7/31.
 * lwjfork@gmail.com
 */

public class JSONUtil {


    public static boolean isHave(JSONObject jsonObject, String key) {
        return jsonObject.has(key);
    }

}
