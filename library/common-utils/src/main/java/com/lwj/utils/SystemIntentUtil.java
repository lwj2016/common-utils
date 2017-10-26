package com.lwj.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;

import java.io.File;

/**
 * Created by lwj on 16/3/23.
 * Des: open the system app or  service
 */
public class SystemIntentUtil {
    /**
     * 打开网络设置界面
     */
    public static void openNetSetting(Context context) {
        Intent intent = null;
        if (VersionCompatUtils.hasHoneycomb()) {
            // 3.0 以上
            intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
        } else {
            intent = new Intent();
            ComponentName componentName = new ComponentName("com.android.settings", "com.android.settings.WirelessSettings");
            intent.setComponent(componentName);
            intent.setAction(Intent.ACTION_VIEW);
        }
        context.startActivity(intent);
    }

    /**
     * 打开默认浏览器
     */
    public static void openDefBrowser(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        context.startActivity(intent);
    }

    /**
     * 打开安卓自带浏览器
     */
    public static void openSysBrowser(Context context, String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");

        context.startActivity(intent);
    }

    /**
     * 打开电话界面  但是 不播出
     *
     * @param context
     * @param telNum
     */
    public static void openTel(Context context, String telNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + telNum);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 打开电话界面  直接播出了
     *
     * @param context
     * @param telNum
     */
    public static void callTel(Context context, String telNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + telNum);
        intent.setData(data);
        context.startActivity(intent);
    }

    /**
     * 打开设置界面
     *
     * @param context
     */
    public static void openSetting(Context context) {
        Intent intent = new Intent(android.provider.Settings.ACTION_SETTINGS); //系统设置

        context.startActivity(intent);
    }

    /**
     * 打开相机
     *
     * @param context
     */

    public static void openCamera(Context context) {

        Intent intent = new Intent("android.media.action.STILL_IMAGE_CAMERA");
        context.startActivity(intent);
    }

    /**
     * 打开联系人界面
     * @param context
     */
    public static void openContacts(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
        context.startActivity(intent);
    }

    /**
     * 打开mp3播放器
     *
     * @param context
     */

    public static void openMP3(Context context) {
        Intent intent = new Intent("android.intent.action.MUSIC_PLAYER");

        context.startActivity(intent);
    }

    /**
     * 选择本地视频
     *
     * @param activity
     * @param requestCode
     */
    public static void selectVideo(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);//视频
        intent.setType("video/*");
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 选择本地图片
     *
     * @param activity
     * @param requestCode
     */
    public static void selectImage(Activity activity, int requestCode) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, null);//视频
        intent.setType("image/*");
        activity.startActivityForResult(intent, requestCode);
    }


    /**
     * 拍摄视频
     *
     * @param activity
     * @param requestCode
     */
    public static void shotVideo(Activity activity, int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);//拍摄视频
        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 0);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 拍摄照片
     *
     * @param activity
     * @param requestCode
     */
    public static void shotImage(Activity activity, int requestCode) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//拍摄照片
        String cameraPicName = System.currentTimeMillis() + ".jpg";
        File photofile = new File("/sdcard/farmer/" + cameraPicName);
        Uri uri1 = Uri.fromFile(photofile);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri1);
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * home  键
     *
     * @param context
     */
    public static void goHome(Context context) {
        Intent homeIntent = new Intent();
        homeIntent.setAction(Intent.ACTION_MAIN);
        homeIntent.addCategory(Intent.CATEGORY_HOME);
        context.startActivity(homeIntent);
    }
}
