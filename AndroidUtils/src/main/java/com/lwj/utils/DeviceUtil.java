package com.lwj.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;

import com.lwj.utils.context.GlobalContext;
import com.lwj.utils.log.LogUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by lwj on 2016/3/8.
 * lwjfork@gmail.com
 */
public class DeviceUtil {



    public static String getPhoneModel() {
        return Build.MODEL;
    }

    public static String getPhoneBrand() {
        return Build.BRAND;
    }

    /**
     * SD 卡是否准备好
     *
     * @return
     */
    public static boolean isSDCardReady() {
        String STATE = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(STATE);
    }

    @SuppressWarnings("MissingPermission")
    public static String getDeviceId() {
        TelephonyManager tm = OSUtils.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm == null) {
            LogUtil.e("TelephonyManager %s", "null");
            return "";
        }

        String device_id = tm.getDeviceId();
        if (StrUtil.isEmpty(device_id)) {
            LogUtil.e("device_id %s", "null");
        }
        return device_id;
    }


    // 获得可用的内存
    public static long getUseAbleMemory() {
        long MEM_UNUSED;
        // 得到ActivityManager
        ActivityManager am = OSUtils.getSystemService(Context.ACTIVITY_SERVICE);
        // 创建ActivityManager.MemoryInfo对象

        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);

        // 取得剩余的内存空间

        MEM_UNUSED = mi.availMem / 1024;
        return MEM_UNUSED;
    }


    public static long getTotalMemory() {
        long mTotal;
        // /proc/meminfo读出的内核信息进行解释
        String path = "/proc/meminfo";
        String content = null;
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path), 8);
            String line;
            if ((line = br.readLine()) != null) {
                content = line;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        // beginIndex
        int begin = content.indexOf(':');
        // endIndex
        int end = content.indexOf('k');
        // 截取字符串信息
        content = content.substring(begin + 1, end).trim();
        mTotal = Integer.parseInt(content);
        return mTotal;
    }

    /**
     * 判断GPS是否打开
     * ACCESS_FINE_LOCATION权限
     *
     * @return
     */
    public static boolean isGPSEnabled() {
        return GPSUtil.isGPSEnabled();
    }
}
