package com.lwj.utils;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;

import com.lwj.utils.context.GlobalContext;
import com.lwj.utils.log.LogUtil;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by lwj on 2016/3/8.
 * lwjfork@gmail.com
 */


public class AppUtil {


    private static Application getContext() {
        return GlobalContext.getContext();
    }


    public static PackageInfo getPackageInfo() {
        try {
            return getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static PackageInfo getPackageInfo(String _packageName) {
        try {
            return getPackageManager().getPackageInfo(_packageName, 0);
        } catch (PackageManager.NameNotFoundException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static PackageManager getPackageManager() {
        return getContext().getPackageManager();
    }

    public static String getVersionName() {

        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo != null) {
            return packageInfo.versionName;
        }
        return null;
    }

    public static int getVersionCode() {
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo != null) {
            return packageInfo.versionCode;
        }
        return -1;
    }

    public static String getPackageName() {
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo != null) {
            return packageInfo.packageName;
        }
        return null;
    }

    public static ApplicationInfo getApplicationInfo() {
        PackageInfo packageInfo = getPackageInfo();
        if (packageInfo != null) {
            return packageInfo.applicationInfo;
        }
        return null;
    }


    public static String getAppName() {
        ApplicationInfo applicationInfo = getApplicationInfo();
        if (applicationInfo != null) {
            return applicationInfo.name;
        }
        return null;
    }

    public static boolean isInstall(String _packageName) {
        return getPackageInfo(_packageName) != null;
    }

    public static List<ApplicationInfo> getAllApps() {
        PackageManager pm = getPackageManager();
        if (pm == null) {
            return null;
        } else {
            return pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
        }
    }

    public static void openApp(Context context, String packageName) throws Exception {
        Intent intent = getPackageManager().getLaunchIntentForPackage(packageName);
        context.startActivity(intent);
    }


    public boolean isRunning(String packageName) {

        boolean isRunning = false;
        ActivityManager am = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        if (am == null) {
            return false;
        }
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
     * 返回对应包的签名信息
     *
     * @param packageName
     * @return
     */
    public static Signature[] getSignatures(String packageName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = getContext().getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            return packageInfo.signatures;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Signature[] getSignatures() {
        return getSignatures(getPackageName());
    }

    public final static String SHA1 = "SHA1";

    /**
     * 返回一个签名的对应类型的字符串
     *
     * @param packageName
     * @param type
     * @return
     */
    public static String getSingInfo(String packageName, String type) {
        String tmp = null;
        Signature[] signs = getSignatures(packageName);
        for (Signature sig : signs) {
            if (SHA1.equals(type)) {
                tmp = getSignatureString(sig, SHA1);
                break;
            }
        }
        return tmp;
    }


    /**
     * 返回一个签名的对应类型的字符串
     *
     * @param type
     * @return
     */
    public static String getSingInfo(String type) {
        return getSingInfo(getPackageName(), type);
    }

    /**
     * 返回一个签名的对应类型的字符串
     *
     * @return
     */
    public static String getSingInfo() {
        return getSingInfo(getPackageName(), SHA1);
    }


    /**
     * 获取相应的类型的字符串（把签名的byte[]信息转换成16进制）
     *
     * @param sig
     * @param type
     * @return
     */
    public static String getSignatureString(Signature sig, String type) {
        byte[] hexBytes = sig.toByteArray();
        String fingerprint = "error!";
        try {
            MessageDigest digest = MessageDigest.getInstance(type);
            if (digest != null) {
                byte[] digestBytes = digest.digest(hexBytes);
                StringBuilder sb = new StringBuilder();
                for (byte digestByte : digestBytes) {
                    sb.append((Integer.toHexString((digestByte & 0xFF) | 0x100)).substring(1, 3));
                }
                fingerprint = sb.toString();
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return fingerprint;
    }
}

