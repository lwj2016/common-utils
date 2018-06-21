package com.lwj.utils;

import android.content.Context;
import android.location.LocationManager;

import com.lwj.utils.context.GlobalContext;

/**
 * Created:2018/6/21
 * User：liuwenjie
 * Email:liuwnejie180423@credithc.com
 * Des:
 * ====================
 */

public class GPSUtil {


    /**
     * 判断GPS是否打开
     * ACCESS_FINE_LOCATION权限
     * @return
     */
    public static boolean isGPSEnabled() {
        //获取手机所有连接LOCATION_SERVICE对象
        LocationManager locationManager = ((LocationManager) GlobalContext.getContext().getSystemService(Context.LOCATION_SERVICE));
        return locationManager != null && locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}
