package com.lwj.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

import com.lwj.utils.log.LogUtil;

/**
 * Created by lwj on 2016/3/8.
 * lwjfork@gmail.com
 */
public class DeviceUtil {


    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String device_id = tm.getDeviceId();
        if (StringUtil.isEmpty(device_id)) {
            LogUtil.e("device_id %s", "null");
        }
        return device_id;
    }

}
