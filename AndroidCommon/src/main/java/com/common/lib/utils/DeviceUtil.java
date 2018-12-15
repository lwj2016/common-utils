package com.common.lib.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Environment;
import android.telephony.TelephonyManager;

import com.common.lib.GlobalContext;
import com.common.lib.utils.log.LogUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by lwj on 2016/3/8.
 * lwjfork@gmail.com
 */
public final class DeviceUtil extends GlobalContext {


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

    public static String getDeviceUniqueID(int length) {
        String deviceTag = Build.CPU_ABI
                + Build.VERSION.SDK_INT
                + Build.DEVICE
                + Build.DISPLAY
                + Build.HOST
                + Build.ID
                + Build.MANUFACTURER
                + Build.MODEL
                + Build.PRODUCT
                + Build.TAGS
                + Build.TYPE
                + Build.USER;

        String serial = "serial"; // 随便一个初始化
        try {
            serial = android.os.Build.class.getField("SERIAL").get(null).toString();
            //API>=9 使用serial号

        } catch (Exception exception) {
            //serial需要一个初始化

        }
        return MD5Util.str2Md5Str(new UUID(deviceTag.hashCode(), serial.hashCode()).toString(), length);
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

    /**
     * 判断是否是平板电脑
     *
     * @return
     */
    public static boolean isTablet() {
        return (getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * 获得设备型号
     *
     * @return
     */
    public static String getDeviceModel() {
        return StrUtil.trim(Build.MODEL);
    }


    /**
     * 检测是否魅族手机
     */
    public static boolean isMeizu() {
        return getDeviceModel().toLowerCase().contains("meizu");
    }

    /**
     * 检测是否HTC手机
     */
    public static boolean isHTC() {
        return getDeviceModel().toLowerCase().contains("htc");
    }

    /**
     * 是否是小米手机
     *
     * @return
     */
    public static boolean isXiaomi() {
        return getDeviceModel().toLowerCase().contains("xiaomi");

    }

}
