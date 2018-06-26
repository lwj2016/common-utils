package com.lwj.utils;

import android.support.annotation.StringRes;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lwj.utils.context.GlobalContext;

/**
 * Created:2018/6/26
 * User：lwjfork
 * Email:lwjfork@gmail.com
 * Des:
 * ====================
 */

public class ToastBuilder {
    public static final int DURATION = Toast.LENGTH_SHORT;
    public static int TOAST_LAYOUT_ID = -1;
    public static int TOAST_TEXT_ID = -1;
    public static final int DEFAULT_GRAVITY = -1;
    public static final int X_OFFSET_DP = -1;
    public static final int Y_OFFSET_DP = -1;

    private static boolean checkDefault() {
        return TOAST_LAYOUT_ID != -1 && TOAST_TEXT_ID != -1;
    }

    public static void showToast(String msg) {

    }

    public static void showToast(@StringRes int strRes) {

    }

    public static void showToast(String msg, int duration) {
        Toast toast = new Toast(GlobalContext.getContext());
        if (checkDefault()) {
            View view = ViewUtil.inflate(TOAST_LAYOUT_ID);
            TextView tv_toast = ViewUtil.findViewById(view, TOAST_TEXT_ID);
            tv_toast.setText(msg);
        }
        toast.setGravity(gravity, xOffset, yOffset);
        toast.setDuration(duration);
        Toast.makeText(GlobalContext.getContext(), msg, duration).show();
    }

    public static void showToast(@StringRes int strRes, int duration) {
        Toast.makeText(GlobalContext.getContext(), strRes, duration).show();
    }
}
