package com.lwj.utils;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.lwj.utils.log.LogUtil;

public class NetUtil {
    /**
     * 检查网络连接状态
     *
     * @param context
     * @return
     */
    public static boolean checkNetwork(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断wifi是否打开
     *
     * @param context
     * @return
     */
    public static boolean chckWifiState(Context context) {
        if (context == null) {
            LogUtil.e("%s", "context is null");
            return false;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getTypeName().equalsIgnoreCase("WIFI") && info[i].isConnected()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}