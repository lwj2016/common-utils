package com.common.lib.utils;

import android.util.DisplayMetrics;

import com.common.lib.GlobalContext;


/**
 * Created by lwj on 16/3/15.
 * Des: DisplayUtil
 */
public final class DisplayUtil extends GlobalContext {

    private static DisplayMetrics dm = null;


    public static int getScreenWidth() {
        return getScreenWidthInPx();
    }

    public static int getScreenHeight() {
        return getScreenHeightInPx();
    }


    static {
        dm = getResources().getDisplayMetrics();
    }


    /**
     * 获得设备屏幕密度
     */
    public static float getScreenDensity() {
        DisplayMetrics metrics = getContext().getResources()
                .getDisplayMetrics();
        return metrics.density;
    }


    public static DisplayMetrics getDisplayMetrics() {
        return dm;
    }

    public static int px2dp(int px) {

        final float density = dm.density;
        return (int) (px / density + 0.5f);
    }

    public static int dp2px(float dp) {

        final float density = dm.density;
        return (int) (dp * density + 0.5f);
    }

    public static int px2sp(float px) {


        return (int) (px / dm.scaledDensity + 0.5f);
    }

    public static int sp2px(float sp) {

        return (int) (sp * dm.scaledDensity + 0.5f);
    }

    public static int getScreenWidthInPx() {

        return dm.widthPixels;
    }

    public static int getScreenHeightInPx() {

        return dm.heightPixels;
    }

    public static int getScreenWidthInDp() {

        return (int) ((float) dm.widthPixels * (160 / dm.xdpi));
    }

    public static int getScreenHeightInDp() {

        int screenHeight = dm.heightPixels;
        return (int) ((float) screenHeight / dm.density);
    }

    public static float getDensity() {

        return dm.density;
    }

    public static int getStatusBarHeight() {
        int result = 0;
        int resourceId = ResUtil.getDimenResId("status_bar_height", "android");
        if (resourceId > 0) {
            result = getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
