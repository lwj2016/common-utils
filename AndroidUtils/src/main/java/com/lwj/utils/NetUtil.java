package com.lwj.utils;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.lwj.utils.context.GlobalContext;

public class NetUtil {

    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_WIFI = 1;
    public static final int TYPE_2G = 2;
    public static final int TYPE_3G = 3;
    public static final int TYPE_4G = 4;


    /**
     * 检查网络连接状态
     *
     * @return
     */
    @SuppressWarnings("MissingPermission")
    public static boolean checkNetwork() {
        ConnectivityManager cm = (ConnectivityManager) GlobalContext.getContext().getSystemService(Service.CONNECTIVITY_SERVICE);
        if (cm == null) {
            return false;
        }
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        }

        return false;
    }

    public static boolean isNetConnected() {
        return getAPNType() != TYPE_UNKNOWN;
    }

    /**
     * 判断wifi是否打开
     *
     * @return
     */
    @SuppressWarnings("MissingPermission")
    public static boolean isWifiConnected() {
        ConnectivityManager cm = (ConnectivityManager) GlobalContext.getContext().getSystemService(Service.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();

            return isConnect(networkInfo)
                    && networkInfo.getType() == ConnectivityManager.TYPE_WIFI;
        }
        return false;
    }

    public static boolean isConnect(NetworkInfo networkInfo) {
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }


    @SuppressWarnings("MissingPermission")
    public static int getAPNType() {
        int type = TYPE_UNKNOWN;
        ConnectivityManager cm = (ConnectivityManager) GlobalContext.getContext().getSystemService(Service.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (isConnect(networkInfo)) {
                int netType = networkInfo.getType();
                if (netType == ConnectivityManager.TYPE_WIFI) {
                    type = TYPE_WIFI;
                } else {
                    TelephonyManager telephonyManager = (TelephonyManager) GlobalContext.getContext().getSystemService(Context.TELEPHONY_SERVICE);
                    if (telephonyManager != null && !telephonyManager.isNetworkRoaming()) {
                        int subType = networkInfo.getSubtype();
                        if ((subType == TelephonyManager.NETWORK_TYPE_GPRS
                                || subType == TelephonyManager.NETWORK_TYPE_EDGE
                                || subType == TelephonyManager.NETWORK_TYPE_CDMA
                                || subType == TelephonyManager.NETWORK_TYPE_1xRTT
                                || subType == TelephonyManager.NETWORK_TYPE_IDEN)) {
                            type = TYPE_2G;
                        } else if (subType == TelephonyManager.NETWORK_TYPE_UMTS
                                || subType == TelephonyManager.NETWORK_TYPE_EVDO_0
                                || subType == TelephonyManager.NETWORK_TYPE_EVDO_A
                                || subType == TelephonyManager.NETWORK_TYPE_HSDPA
                                || subType == TelephonyManager.NETWORK_TYPE_HSUPA
                                || subType == TelephonyManager.NETWORK_TYPE_HSPA
                                || subType == TelephonyManager.NETWORK_TYPE_EVDO_B
                                || subType == TelephonyManager.NETWORK_TYPE_EHRPD
                                || subType == TelephonyManager.NETWORK_TYPE_HSPAP
                                || subType == TelephonyManager.NETWORK_TYPE_TD_SCDMA
                                ) {
                            type = TYPE_3G;
                        } else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {
                            type = TYPE_4G;
                        } else if (subType > TelephonyManager.NETWORK_TYPE_LTE) { // 默认是4G
                            type = TYPE_4G;
                        } else {
                            type = TYPE_2G; // 默认是 2G
                        }
                    }
                }
            }
        }
        return type;
    }

    public static boolean is2GConnected() {
        return getAPNType() == TYPE_2G;
    }

    public static boolean is3GConnected() {
        return getAPNType() == TYPE_3G;
    }


    public static boolean is4GConnected() {
        return getAPNType() == TYPE_4G;
    }


}

