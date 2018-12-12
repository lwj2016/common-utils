package com.common.lib.utils;

/**
 * Created by lwj on 2018/8/22.
 * lwjfork@gmail.com
 */
public final class ScreenUtil {

    public static int getScreenWidth() {

        return DisplayUtil.getScreenWidth();
    }

    public static int getScreenHeight() {

        return DisplayUtil.getScreenHeight();
    }

    public static float getScreenWidth(float x) {
        return getScreenWidth() * x;
    }


    public static float getScreenHeight(float y) {
        return getScreenHeight() * y;
    }


    public static float getScreenWidth2() {
        return getScreenWidth(0.5f);
    }

    public static float getScreenHeight2() {
        return getScreenHeight(0.5f);
    }


    public static float getScreenWidth3() {
        return getScreenWidth(1.0f/3f);
    }

    public static float getScreenHeight3() {
        return getScreenHeight(1.0f/3f);
    }



    public static float getScreenWidth4() {
        return getScreenWidth(1.0f/4f);
    }

    public static float getScreenHeight4() {
        return getScreenHeight(1.0f/4f);
    }



    public static float getScreenWidth5() {
        return getScreenWidth(1.0f/5f);
    }

    public static float getScreenHeight5() {
        return getScreenHeight(1.0f/5f);
    }


    public static float getScreenWidth6() {
        return getScreenWidth(1.0f/6f);
    }

    public static float getScreenHeight6() {
        return getScreenHeight(1.0f/6f);
    }




}
