package com.lwj.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;

import com.lwj.utils.context.GlobalContext;

/**
 * Created by lwj on 16/3/15.
 * Des: DisplayUtil
 */
public class DisplayUtil {

    private static DisplayMetrics dm = null;


    public static int getScreenWidth() {
        return getScreenWidth(GlobalContext.getContext());
    }

    public static int getScreenHeight() {
        return getScreenHeight(GlobalContext.getContext());
    }

    public static int getScreenWidth(Context context) {
        return getScreenWidthInPx(context);
    }

    public static int getScreenHeight(Context context) {
        return getScreenHeightInPx(context);
    }


    public static DisplayMetrics getDisplayMetrics(Context context) {
        if (dm == null) {
            dm = context.getResources().getDisplayMetrics();
        }
        return dm;
    }

    public static int px2dp(Context context, int px) {
        if (context == null) {
            return px;
        }
        getDisplayMetrics(context);
        final float density = dm.density;
        return (int) (px / density + 0.5f);
    }

    public static int dp2px(Context context, float dp) {
        if (context == null) {
            return (int) dp;
        }
        getDisplayMetrics(context);
        final float density = dm.density;
        return (int) (dp * density + 0.5f);
    }

    public static int px2sp(Context context, float px) {
        if (context == null) {
            return (int) px;
        }
        getDisplayMetrics(context);
        return (int) (px / dm.scaledDensity + 0.5f);
    }

    public static int sp2px(Context context, float sp) {
        if (context == null) {
            return (int) sp;
        }
        getDisplayMetrics(context);
        return (int) (sp * dm.scaledDensity + 0.5f);
    }

    public static int getScreenWidthInPx(Context context) {
        getDisplayMetrics(context);
        return dm.widthPixels;
    }

    public static int getScreenHeightInPx(Context context) {
        getDisplayMetrics(context);
        return dm.heightPixels;
    }

    public static int getScreenWidthInDp(Context context) {
        getDisplayMetrics(context);
        return (int) ((float) dm.widthPixels * (160 / dm.xdpi));
    }

    public static int getScreenHeightInDp(Context context) {
        getDisplayMetrics(context);
        int screenHeight = dm.heightPixels;
        return (int) ((float) screenHeight / dm.density);
    }

    public static float getDensity(Context context) {
        getDisplayMetrics(context);
        return dm.density;
    }

    public static int getScreenTitleBarHeight(Context context) {
        Rect rect = new Rect();
        ((Activity) context).getWindow().getDecorView()
                .getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

}
