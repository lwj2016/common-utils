package com.lwj.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.lwj.utils.context.GlobalContext;

/**
 * Created by lwj on 16/3/15.
 */
public class ToastUtil {
    static Toast toast;
    static TextView contentView;

    public ToastUtil() {
    }

    public static void showToast(int messageID) {
        showToast(GlobalContext.getContext(), messageID);
    }

    public static void showToast(String message) {
        showToast(GlobalContext.getContext(), message);
    }

    public static void showToast(int messageID, int duration) {
        showToast(GlobalContext.getContext(), ResUtil.getString(messageID), duration);
    }

    public static void showToast(String message, int duration) {
        showToast(GlobalContext.getContext(), message, duration);
    }

    public static void showToast(Context context, int resId) {
        showToast(context, context.getString(resId), 0);
    }

    public static void showToast(Context context, String message) {
        showToast(context, message, 0);
    }

    public static void showToast(Activity context, int resId) {
        showToast(context, context.getString(resId), 0);
    }

    private static void showToast(Activity context, String message) {
        showToast(context, message, 0);
    }

    private static void showToast(Context context, int resId, int duration) {
        showToast(context, context.getString(resId), duration);
    }

    private static void showToast(Context context, String message, int duration) {
        if(context != null) {
            try {
                Toast.makeText(context, message, duration).show();
            } catch (Exception var5) {

                var5.printStackTrace();
            }

        }
    }
}
