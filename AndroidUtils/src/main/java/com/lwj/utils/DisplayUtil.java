package com.lwj.utils;

import android.app.Activity;
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
        return getScreenWidthInPx();
    }

    public static int getScreenHeight() {
        return getScreenHeightInPx();
    }


    public static DisplayMetrics getDisplayMetrics() {
        if (dm == null) {
            dm = GlobalContext.getContext().getResources().getDisplayMetrics();
        }
        return dm;
    }

    public static int px2dp(int px) {

        getDisplayMetrics();
        final float density = dm.density;
        return (int) (px / density + 0.5f);
    }

    public static int dp2px(float dp) {

        getDisplayMetrics();
        final float density = dm.density;
        return (int) (dp * density + 0.5f);
    }

    public static int px2sp(float px) {

        getDisplayMetrics();
        return (int) (px / dm.scaledDensity + 0.5f);
    }

    public static int sp2px(float sp) {
        getDisplayMetrics();
        return (int) (sp * dm.scaledDensity + 0.5f);
    }

    public static int getScreenWidthInPx() {
        getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeightInPx() {
        getDisplayMetrics();
        return dm.heightPixels;
    }

    public static int getScreenWidthInDp() {
        getDisplayMetrics();
        return (int) ((float) dm.widthPixels * (160 / dm.xdpi));
    }

    public static int getScreenHeightInDp() {
        getDisplayMetrics();
        int screenHeight = dm.heightPixels;
        return (int) ((float) screenHeight / dm.density);
    }

    public static float getDensity() {
        getDisplayMetrics();
        return dm.density;
    }

    public static int getScreenTitleBarHeight(Activity activity) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView()
                .getWindowVisibleDisplayFrame(rect);
        return rect.top;
    }

}
