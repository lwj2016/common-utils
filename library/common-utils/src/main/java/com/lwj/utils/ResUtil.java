package com.lwj.utils;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.widget.RelativeLayout;

import com.lwj.utils.context.GlobalContext;

/**
 * Created by lwj on 16/3/9.
 * lwjfork@gmail.com
 */
public class ResUtil {
    /**
     * 从strings.xml中读取String
     *
     * @param resId
     * @return
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 从colors.xml中读取color
     *
     * @param resId
     * @return
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 从arrays.xml中读取array[]
     *
     * @param resId
     * @return
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 从dimens.xml里读取dimen
     *
     * @param resId
     * @return
     */
    public static int getDimen(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获得全局的resources
     *
     * @return
     */
    public static Resources getResources() {
        return GlobalContext.getContext().getResources();
    }

    /**
     * 获取 drawable
     *
     * @param drawableResId drawable id
     * @return drawable
     */
    public static Drawable getDrawable(int drawableResId) {
        return getResources().getDrawable(drawableResId);
    }

    /**
     * 获取 int 数组
     *
     * @param resId
     * @return
     */
    public static int[] getIntegerArray(int resId) {

        return getResources().getIntArray(resId);
    }

    /**
     * 获取  AssetManager
     *
     * @return
     */
    public static AssetManager getAssetManager() {
        return getResources().getAssets();
    }

    /**
     *
     * @param res
     * @param args
     * @return
     */
    public static String getFormatString(String res, Object... args){
       return String.format(res,args);
    }
    /**
     *
     * @param resId
     * @param args
     * @return
     */
    public static String getFormatString(int resId,Object... args){
        return getFormatString(ResUtil.getString(resId),args);
    }
}
