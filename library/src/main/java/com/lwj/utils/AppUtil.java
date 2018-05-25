package com.lwj.utils;

import android.app.ActivityManager;
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
        return pm.getInstalledApplications(PackageManager.GET_UNINSTALLED_PACKAGES);
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
        LogUtil.w("%s isn't exist", filePath);
    }


    /**
     * 返回对应包的签名信息
     *
     * @param context
     * @param packageName
     *
     * @return
     */
    public static Signature[] getSignatures(Context context, String packageName) {
        PackageInfo packageInfo = null;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_SIGNATURES);
            return packageInfo.signatures;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Signature[] getSignatures(Context context) {
        return getSignatures(context, getPackageName(context));
    }

    public static Signature[] getSignatures(String packageName) {
        return getSignatures(GlobalContext.getContext(), packageName);
    }


    public static Signature[] getSignatures() {
        return getSignatures(GlobalContext.getContext(), getPackageName(GlobalContext.getContext()));
    }

    public final static String SHA1 = "SHA1";

    /**
     * 返回一个签名的对应类型的字符串
     *
     * @param context
     * @param packageName
     * @param type
     *
     * @return
     */
    public static String getSingInfo(Context context, String packageName, String type) {
        String tmp = null;
        Signature[] signs = getSignatures(context, packageName);
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
     *
     * @return
     */
    public static String getSingInfo(String type) {
        return getSingInfo(GlobalContext.getContext(), getPackageName(), type);
    }

    /**
     * 返回一个签名的对应类型的字符串
     *
     * @return
     */
    public static String getSingInfo() {
        return getSingInfo(GlobalContext.getContext(), getPackageName(), SHA1);
    }

    /**
     * 返回一个签名的对应类型的字符串
     *
     * @return
     */
    public static String getSingInfo(Context context, String type) {
        return getSingInfo(context, getPackageName(context), type);
    }

    /**
     * 返回一个签名的对应类型的字符串
     *
     * @param type
     * @param packageName
     *
     * @return
     */
    public static String getSingInfo(String packageName, String type) {
        return getSingInfo(GlobalContext.getContext(), packageName, type);
    }


    /**
     * 获取相应的类型的字符串（把签名的byte[]信息转换成16进制）
     *
     * @param sig
     * @param type
     *
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

