package com.common.lib.utils;

import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.LocalBroadcastManager;


import com.common.lib.GlobalContext;
import com.common.lib.utils.log.LogUtil;

import java.io.File;

/**
 * Created by lwj on 2018/3/20.
 * lwjfork@gmail.com
 */

public class BroadcastUtil extends GlobalContext {


    /**
     * 注册App 内 广播
     *
     * @param context
     * @param receiver
     * @param action
     */
    public static BroadcastReceiver registerLocalReceiver(Context context, BroadcastReceiver receiver, String... action) {
        return register(context, receiver, false, action);
    }


    /**
     * 反注册App内广播接受者
     *
     * @param context
     * @param receiver
     */
    public static void unregisterLocalReceiver(Context context, BroadcastReceiver receiver) {
        unregister(context, receiver, false);
    }


    public static BroadcastReceiver registerGlobalReceiver(Context context, BroadcastReceiver receiver, String... action) {
        return register(context, receiver, true, action);
    }

    /**
     * 反注册广播接受者
     *
     * @param context
     * @param receiver
     */
    public static void unregisterGlobalReceiver(Context context, BroadcastReceiver receiver) {
        unregister(context, receiver, true);
    }


    /**
     * 注册广播
     *
     * @param context
     * @param receiver
     * @param action
     * @param isGlobal 是否是全局广播
     */
    public static BroadcastReceiver register(Context context, BroadcastReceiver receiver, boolean isGlobal, String... action) {
        IntentFilter filter = new IntentFilter();
        for (String s : action) {
            filter.addAction(s);
        }
        if (isGlobal) {
            context.registerReceiver(receiver, filter);
            return receiver;
        }
        LocalBroadcastManager.getInstance(context).registerReceiver(receiver, filter);
        return receiver;
    }

    /**
     * 反注册广播接受者
     *
     * @param context
     * @param receiver
     */
    public static void unregister(Context context, BroadcastReceiver receiver, boolean isGlobal) {
        if(receiver == null){
            return;
        }
        if (isGlobal) {
            LocalBroadcastManager.getInstance(context).unregisterReceiver(receiver);
            return;
        }
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
        getContext().sendBroadcast(intent, receiverPermission);
    }


    /**
     * 发送有序广播
     */
    public static void sendOrderBroadcast(Intent intent) {
        sendBroadcast(intent, null);
    }

    /**
     * 发送有序广播
     */
    public static void sendOrderBroadcast(Intent intent, String receiverPermission) {
        getContext().sendBroadcast(intent, receiverPermission);
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
