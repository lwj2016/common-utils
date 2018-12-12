package com.common.lib.utils;

import android.content.Context;
import android.location.LocationManager;

/**
 * Created:2018/6/21
 * User：lwjfork
 * Email:lwjfork@gmail.com
 * Des:
 * ====================
 */

public final  class GPSUtil {



    public static LocationManager getManager() {
        return OSUtils.getSystemService(Context.LOCATION_SERVICE);
    }

    /**
     * 判断GPS是否打开
     * ACCESS_FINE_LOCATION权限
     *
     * @return
     */
    public static boolean isGPSEnabled() {
        //获取手机所有连接LOCATION_SERVICE对象
        LocationManager locationManager = getManager();
        return locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
