package com.lwj.utils;

import android.content.Context;
import android.widget.Toast;

import com.lwj.utils.context.GlobalContext;

/**
 * Created by lwj on 16/3/15.
 */
public class ToastUtil {

    public static void showToast(int messageID) {
        showToast(messageID, Toast.LENGTH_SHORT);
    }

    public static void showToast(int messageID, int duration) {
        showToast(GlobalContext.getContext(), ResUtil.getString(messageID), duration);
    }

    public static void showToast(String message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    public static void showToast(String message, int duration) {
        showToast(GlobalContext.getContext(), message, duration);
    }

    public static void showToast(Context context, int resId) {
        showToast(context, resId, Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context, int resId, int duration) {
        showToast(context, context.getString(resId), duration);
    }

    public static void showToast(Context context, String message) {
        showToast(context, message, Toast.LENGTH_SHORT);
    }

    public static void showToast(Context context, String message, int duration) {
        if (context != null) {
            try {
                Toast.makeText(context, message, duration).show();
            } catch (Exception var5) {

                var5.printStackTrace();
            }

        }
    }
}
