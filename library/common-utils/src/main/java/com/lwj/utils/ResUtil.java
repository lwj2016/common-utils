package com.lwj.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.lwj.utils.context.GlobalContext;

/**
 * Created by lwj on 16/3/9.
 * lwjfork@gmail.com
 */
public class ResUtil {

    /**
     * 获得全局的resources
     *
     * @return resources
     */
    public static Resources getResources() {
        return GlobalContext.getContext().getResources();
    }
    /**
     * 获得全局的resources
     *
     * @return resources
     */
    public static Resources getResources(Context context) {
        return context.getResources();
    }

    /**
     * 从strings.xml中读取String
     *
     * @param resId String res id
     * @return string
     */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /**
     * 从strings.xml中读取String
     *
     * @param context context
     * @param resId   String res id
     * @return string
     */
    public static String getString(Context context, int resId) {
        return getResources(context).getString(resId);
    }

    /**
     * 从colors.xml中读取color
     *
     * @param resId color id
     * @return color
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 从colors.xml中读取color
     *
     * @param resId color id
     * @return color
     */
    public static int getColor(Context context, int resId) {
        return getResources(context).getColor(resId);
    }

    /**
     * 从arrays.xml中读取array[]
     *
     * @param resId String array id
     * @return String[]
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }
    /**
     * 从arrays.xml中读取array[]
     *
     * @param resId String array id
     * @return String[]
     */
    public static String[] getStringArray(Context context,int resId) {
        return getResources(context).getStringArray(resId);
    }

    /**
     * 从dimens.xml里读取dimen
     *
     * @param resId dimensid
     * @return dimen
     */
    public static int getDimen(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * 从dimens.xml里读取dimen
     *
     * @param resId dimensid
     * @return dimen
     */
    public static int getDimen(Context context,int resId) {
        return getResources(context).getDimensionPixelSize(resId);
    }


    /**
     * 获取 drawable
     *
     * @param drawableResId drawable id
     * @return drawable
     */
    public static Drawable getDrawable(int drawableResId) {
        if (VersionCompatUtils.hasJellyBean()) {
            return getResources().getDrawable(drawableResId, null);
        } else {
            return getResources().getDrawable(drawableResId);
        }
    }
    /**
     * 获取 drawable
     *
     * @param drawableResId drawable id
     * @return drawable
     */
    public static Drawable getDrawable(Context context,int drawableResId) {
        if (VersionCompatUtils.hasJellyBean()) {
            return getResources(context).getDrawable(drawableResId, null);
        } else {
            return getResources(context).getDrawable(drawableResId);
        }
    }

    /**
     * 获取 int 数组
     *
     * @param resId IntegerArray id
     * @return IntegerArray
     */
    public static int[] getIntegerArray(int resId) {

        return getResources().getIntArray(resId);
    }
    /**
     * 获取 int 数组
     *
     * @param resId IntegerArray id
     * @return IntegerArray
     */
    public static int[] getIntegerArray(Context context,int resId) {

        return getResources(context).getIntArray(resId);
    }
    /**
     * 获取  AssetManager
     *
     * @return AssetManager
     */
    public static AssetManager getAssetManager() {
        return getResources().getAssets();
    }
    /**
     * 获取  AssetManager
     *
     * @return AssetManager
     */
    public static AssetManager getAssetManager(Context context) {
        return getResources(context).getAssets();
    }

    /**
     * @param res
     * @param args
     * @return
     */
    public static String getFormatString(String res, Object... args) {
        return String.format(res, args);
    }

    /**
     * @param resId
     * @param args
     * @return
     */
    public static String getFormatString(int resId, Object... args) {
        return getFormatString(ResUtil.getString(resId), args);
    }
    /**
     * @param resId
     * @param args
     * @return
     */
    public static String getFormatString(Context context,int resId, Object... args) {
        return getFormatString(ResUtil.getString(context,resId), args);
    }

    /**
     * 获取动画
     *
     * @param animationID
     * @return
     */
    public static Animation getAnimation(int animationID) {
        return AnimationUtils.loadAnimation(GlobalContext.getContext(), animationID);
    }
    /**
     * 获取动画
     *
     * @param animationID
     * @return
     */
    public static Animation getAnimation(Context context,int animationID) {
        return AnimationUtils.loadAnimation(context, animationID);
    }
}
