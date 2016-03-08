package com.lwj.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.telephony.TelephonyManager;

import com.lwj.utils.log.LogUtil;

/**
 * Created by lwj on 2016/3/8.
 * liuwenjie@goumin.com
 */
public class AdviceUtil {
    public static String getVersionName(Context context) {
        try {
            PackageInfo e = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return e.versionName;
        } catch (PackageManager.NameNotFoundException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static int getVersionCode(Context context) {
        try {
            PackageInfo e = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return e.versionCode;
        } catch (PackageManager.NameNotFoundException var2) {
            var2.printStackTrace();
            return -1;
        }
    }

    public static String getPackageName(Context context) {
        try {
            PackageInfo e = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return e.packageName;
        } catch (PackageManager.NameNotFoundException var2) {
            LogUtil.e("getPackageName %s", new Object[]{var2.toString()});
            return "";
        }
    }

    public static String getAppName(Context context) {
        try {
            PackageInfo e = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return e.applicationInfo.name;
        } catch (PackageManager.NameNotFoundException var2) {
            LogUtil.e("getAppName %s", new Object[]{var2.toString()});
            return "";
        }
    }

    public static boolean isInstall(Context context, String _packageName) throws PackageManager.NameNotFoundException {
        boolean isInstall = false;
        PackageInfo packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(_packageName, 0);
        if(packageInfo == null) {
            isInstall = false;
        } else {
            isInstall = true;
        }

        return isInstall;
    }

    public static void gotoMarket(Context context, String packageName) throws Exception {
        String strUrl = "market://details?id=" + packageName;
        Uri uri = Uri.parse(strUrl);
        Intent it = new Intent("android.intent.action.VIEW", uri);
        context.startActivity(it);
    }

    public static void openApp(Context context, String packageName) throws Exception {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        context.startActivity(intent);
    }

    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        String device_id = tm.getDeviceId();
        if(StringUtil.isEmpty(device_id)) {
            LogUtil.e("device_id %s", "null");
        }
        return device_id;
    }
}
