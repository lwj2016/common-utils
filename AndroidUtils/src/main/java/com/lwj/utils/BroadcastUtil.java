package com.lwj.utils;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import com.lwj.utils.context.GlobalContext;
import com.lwj.utils.log.LogUtil;

import java.io.File;

/**
 * Created by lwj on 2018/3/20.
 * lwjfork@gmail.com
 */

public class BroadcastUtil {


    /**
     * 注册广播
     *
     * @param context
     * @param receiver
     * @param action
     */
    public static void register(Context context, BroadcastReceiver receiver, String... action) {
        IntentFilter filter = new IntentFilter();
        for (String s : action) {
            filter.addAction(s);
        }
        context.registerReceiver(receiver, filter);
    }

    /**
     * 反注册广播接受者
     *
     * @param context
     * @param receiver
     */
    public static void unregister(Context context, BroadcastReceiver receiver) {
        context.unregisterReceiver(receiver);
    }


    /**
     * 发送广播
     */
    public static void sendBroadcast(String action) {
        Intent intent = new Intent();
        intent.setAction(action);
        sendBroadcast(intent);
    }

    /**
     * 发送广播
     */
    public static void sendBroadcast(String action, String receiverPermission) {
        Intent intent = new Intent();
        intent.setAction(action);
        sendBroadcast(intent, receiverPermission);
    }

    /**
     * 发送广播
     */
    public static void sendBroadcast(Intent intent) {
        sendBroadcast(intent, null);
    }

    /**
     * 发送广播
     */
    public static void sendBroadcast(Intent intent, String receiverPermission) {
        GlobalContext.getContext().sendBroadcast(intent, receiverPermission);
    }


    public static void refreshGallery(File file) {
        Uri uri = Uri.fromFile(file);
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(uri);
        sendBroadcast(intent);
    }

    public static void refreshGallery(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            refreshGallery(file);
        } else {
            LogUtil.e("refreshGallery ->> %s not found", filePath);
        }
    }


    public static void refreshInsertImage(ContentResolver cr, Bitmap source, String title, String description) {
        MediaStore.Images.Media.insertImage(cr, source, title, description);
    }

}
