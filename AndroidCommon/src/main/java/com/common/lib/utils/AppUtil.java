package com.common.lib.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.support.annotation.Nullable;

import com.common.lib.GlobalContext;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by lwj on 2016/3/8.
 * lwjfork@gmail.com
 */


public final class AppUtil extends GlobalContext {

    public final static String SHA1 = "SHA1";


    public static PackageInfo getPackageInfo() {
        return getPackageInfo(getPackageName());
    }

    public static PackageInfo getPackageInfo(String _packageName) {
        try {
            return getPackageManager().getPackageInfo(_packageName, 0);
        } catch (PackageManager.NameNotFoundException var2) {
            var2.printStackTrace();
            return null;
        }
    }


    public static String getVersionName() {
        return getPackageInfo().versionName;
    }

    public static int getVersionCode() {

        return getPackageInfo().versionCode;
    }

    public static String getPackageName() {
        return getContext().getPackageName();
    }

    public static ApplicationInfo getApplicationInfo() {
        return getContext().getApplicationInfo();
    }


    public static String getAppName() {
        return getApplicationInfo().name;
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
    @Nullable
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

    @Nullable
    public static Signature[] getSignatures() {
        return getSignatures(getPackageName());
    }


    /**
     * 返回一个签名的对应类型的字符串
     *
     * @param packageName
     * @param type
     * @return
     */
    @Nullable
    public static String getSingInfo(String packageName, String type) {
        String tmp = null;
        Signature[] signs = getSignatures(packageName);
        if (signs == null) {
            return null;
        }
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
    @Nullable
    public static String getSingInfo(String type) {
        return getSingInfo(getPackageName(), type);
    }

    /**
     * 返回一个签名的对应类型的字符串
     *
     * @return
     */
    @Nullable
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

    /**
     * 多进程环境下，防止多次初始化
     *
     * @return true 初始化， false 不初始化
     */
    public static boolean isInitAble() {

        String packageName = getPackageName();
        if (packageName == null) { // impossible
            return true;
        }
        return packageName.equalsIgnoreCase(OSUtils.getProcessName());
    }


}

