package com.lwj.utils;

import android.app.Service;
import android.content.Context;
import android.location.LocationManager;
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
     * @param context
     * @return
     */
    @SuppressWarnings("MissingPermission")
    public static boolean checkNetwork(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
            if (cm == null) {
                return false;
            }
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                return true;
            }
        }

        return false;
    }

    public static boolean isNetConnected() {
        return isNetConnected(GlobalContext.getContext());
    }

    public static boolean isNetConnected(Context context) {
        return getAPNType(context) != TYPE_UNKNOWN;
    }


    /**
     * 检查网络连接状态
     *
     * @return
     */
    public static boolean checkNetwork() {
        return checkNetwork(GlobalContext.getContext());
    }

    public static boolean isWifiConnected() {
        return isWifiConnected(GlobalContext.getContext());
    }

    /**
     * 判断wifi是否打开
     *
     * @param context
     * @return
     */
    @SuppressWarnings("MissingPermission")
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
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


    public static int getAPNType() {
        return getAPNType(GlobalContext.getContext());
    }

    @SuppressWarnings("MissingPermission")
    public static int getAPNType(Context context) {
        int type = TYPE_UNKNOWN;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (isConnect(networkInfo)) {
                int netType = networkInfo.getType();
                if (netType == ConnectivityManager.TYPE_WIFI) {
                    type = TYPE_WIFI;
                } else {
                    TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
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
                                ) {
                            type = TYPE_3G;
                        } else if (subType == TelephonyManager.NETWORK_TYPE_LTE) {
                            type = TYPE_4G;
                        } else if (subType > TelephonyManager.NETWORK_TYPE_LTE) { // 默认是4G
                            type = TYPE_4G;// 默认是 2G
                        } else {
                            type = TYPE_2G;
                        }
                    }
                }
            }
        }
        return type;
    }

    public static boolean is2GConnected() {
        return is2GConnected(GlobalContext.getContext());
    }

    public static boolean is3GConnected() {
        return is3GConnected(GlobalContext.getContext());
    }


    public static boolean is4GConnected() {
        return is4GConnected(GlobalContext.getContext());
    }

    public static boolean is2GConnected(Context context) {
        return getAPNType(context) == TYPE_2G;
    }

    public static boolean is3GConnected(Context context) {
        return getAPNType(context) == TYPE_3G;
    }


    public static boolean is4GConnected(Context context) {
        return getAPNType(context) == TYPE_4G;
    }


    /**
     * 判断GPS是否打开
     * ACCESS_FINE_LOCATION权限
     *
     * @param context
     * @return
     */
    public static boolean isGPSEnabled(Context context) {
        //获取手机所有连接LOCATION_SERVICE对象
        LocationManager locationManager = ((LocationManager) context.getSystemService(Context.LOCATION_SERVICE));
        return locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    /**
     * 判断GPS是否打开
     * ACCESS_FINE_LOCATION权限
     *
     * @return
     */
    public static boolean isGPSEnabled() {
        return isGPSEnabled(GlobalContext.getContext());
    }
}

