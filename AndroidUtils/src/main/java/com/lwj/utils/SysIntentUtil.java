package com.lwj.utils;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Settings;

import com.lwj.utils.log.LogUtil;

import java.io.File;

/**
 * Created by lwj on 16/3/23.
 * Des: open the system app or  service
 */
public class SysIntentUtil {
    /**
     * 打开网络设置界面
     */
    public static void openNetSetting(Context context) {
        Intent intent = null;
        if (OSUtils.hasHoneycomb()) {
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
        ActivityUtil.startActivity(context, Intent.ACTION_DIAL, "tel:" + telNum);
    }

    /**
     * 打开电话界面  直接播出了
     *
     * @param context
     * @param telNum
     */
    @SuppressWarnings("MissingPermission")
    public static void callTel(Context context, String telNum) {
        ActivityUtil.startActivity(context, Intent.ACTION_CALL, "tel:" + telNum);
    }

    /**
     * 打开设置界面
     *
     * @param context
     */
    public static void openSetting(Context context) {
        ActivityUtil.startActivity(context,android.provider.Settings.ACTION_SETTINGS);
    }

    /**
     * 打开相机
     *
     * @param context
     */

    public static void openCamera(Context context) {
        ActivityUtil.startActivity(context,"android.media.action.STILL_IMAGE_CAMERA");
    }

    /**
     * 打开联系人界面
     *
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

    public static void playMP3(Context context, String filePath) {
        browserFile(context, filePath, "audio/mp3");
    }


    public static void browserFile(Context context, String filePath, String type) {
        ActivityUtil.startActivity(context,
                IntentBuilder.getBuilder().
                        setAction(android.content.Intent.ACTION_VIEW).
                        setData(filePath).
                        setType(type).
                        builder());
    }


    public static void openGPS(Context context) {
        ActivityUtil.startActivity(context, Settings.ACTION_LOCATION_SOURCE_SETTINGS);
    }


    public static void openGPS(Activity activity, int reqCode) {
        ActivityUtil.startActivityForResult(activity, Settings.ACTION_LOCATION_SOURCE_SETTINGS, reqCode);
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
        File photofile = new File(Environment.getExternalStorageDirectory().getPath() + cameraPicName);
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


    /**
     * 打开应用市场的app
     *
     * @param context
     * @param packageName 所要打开的app简介
     * @throws Exception
     */
    public static void gotoMarket(Context context, String packageName) throws Exception {
        String strUrl = "market://details?id=" + packageName;
        Uri uri = Uri.parse(strUrl);
        Intent it = new Intent("android.intent.action.VIEW", uri);
        context.startActivity(it);
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

}
