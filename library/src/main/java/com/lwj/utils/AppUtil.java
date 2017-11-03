package com.lwj.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.lwj.utils.context.GlobalContext;
import com.lwj.utils.log.LogUtil;

import java.io.File;
import java.util.List;

/**
 * Created by lwj on 2016/3/8.
 * lwjfork@gmail.com
 */


public class AppUtil {

    public static String getVersionName(Context context) {
        try {
            PackageInfo e = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return e.versionName;
        } catch (PackageManager.NameNotFoundException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static String getVersionName() {
        return getVersionName(GlobalContext.getContext());
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

    public static int getVersionCode() {
        return getVersionCode(GlobalContext.getContext());
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

    public static String getPackageName() {
        return getPackageName(GlobalContext.getContext());
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

    public static String getAppName() {
        return getAppName(GlobalContext.getContext());
    }

    public static boolean isInstall(Context context, String _packageName) {
        PackageInfo packageInfo;

        try {
            packageInfo = context.getApplicationContext().getPackageManager().getPackageInfo(_packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
        return packageInfo != null;
    }

    public static boolean isInstall(String _packageName) {
        return isInstall(GlobalContext.getContext(), _packageName);
    }

    public static List<ApplicationInfo> getAllApps(Context context) {
        PackageManager pm = context.getPackageManager();
        List<ApplicationInfo> infos = pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        return infos;
    }

    public static List<ApplicationInfo> getAllApps() {
        return getAllApps(GlobalContext.getContext());
    }



    public static void openApp(Context context, String packageName) throws Exception {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        context.startActivity(intent);
    }


    public boolean isRunning(Context context, String packageName) {

        boolean isRunning = false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> runningTasks = am.getRunningTasks(100);
        for (ActivityManager.RunningTaskInfo runningTaskInfo : runningTasks) {

            if (runningTaskInfo.topActivity.getPackageName().equals(packageName) || runningTaskInfo.baseActivity.getPackageName().equals(packageName)) {
                isRunning = true;
                break;
            }
        }
        return isRunning;
    }

    /**
     * 更新  手机相册 显示最新图片
     *
     * @param context context
     * @param file    this file update
     */
    public static void updateGallery(Context context, File file) {
        Intent intent = new Intent();
        Uri uri = Uri.fromFile(file);
        intent.setAction("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(uri);
        context.sendBroadcast(intent);
    }

    public static void updateGallery(Context context, String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            updateGallery(context, file);
            return;
        }
        LogUtil.w("%s isn't exist");
    }
}
