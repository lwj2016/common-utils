package com.common.lib.image.utils;

import android.content.Context;

/**
 * Created by lwj on 2017/9/18.
 * lwjfork@gmail.com
 */

public class ImageSizeUtil {


    /**
     * 计算 大小
     *
     * @param widthRatio 宽度占屏幕的几分之几
     * @param whRatio    宽高比是多少
     * @return ReSize
     */
    public static ReSize getReSize(Context context, float widthRatio, float whRatio) {

        int displayW = context.getResources().getDisplayMetrics().widthPixels;

        float viewW = displayW * widthRatio;
        float viewH = viewW / whRatio;

        return new ReSize((int) viewW, (int) viewH);
    }

    /**
     * 获取方形size
     *
     * @param widthRatio 宽度占屏幕的几分之几
     * @return ReSize
     */
    public static ReSize getSquareReSize(Context context, float widthRatio) {
        return getReSize(context, widthRatio, 1f);
    }

    /**
     * 方形图，宽度充满屏幕
     *
     * @param context context
     * @return ReSize
     */
    public static ReSize getSquareReSize1(Context context) {
        return getSquareReSize(context, 1f);
    }

    /**
     * 方形图，宽度 占屏幕的 1／2
     *
     * @param context context
     * @return ReSize
     */
    public static ReSize getSquareReSize2(Context context) {
        return getSquareReSize(context, 1f / 2f);
    }

    /**
     * 方形图，宽度 占屏幕的 1／3
     *
     * @param context context
     * @return ReSize
     */
    public static ReSize getSquareReSize3(Context context) {
        return getSquareReSize(context, 1f / 3f);
    }

    /**
     * 方形图，宽度 占屏幕的 1／4
     *
     * @param context context
     * @return ReSize
     */
    public static ReSize getSquareReSize4(Context context) {
        return getSquareReSize(context, 1f / 4f);
    }

    /**
     * 方形图，宽度 占屏幕的 1／5
     *
     * @param context context
     * @return ReSize
     */
    public static ReSize getSquareReSize5(Context context) {
        return getSquareReSize(context, 1f / 5f);
    }

    /**
     * 方形图，宽度 占屏幕的 1／6
     *
     * @param context context
     * @return ReSize
     */
    public static ReSize getSquareReSize6(Context context) {
        return getSquareReSize(context, 1f / 6f);
    }

    /**
     * 宽度固定
     * 高度 = 宽度 * 1／2
     *
     * @param context
     * @return ReSize
     */
    public static ReSize getHByWSize2(Context context) {
        int displayW = context.getResources().getDisplayMetrics().widthPixels;
        return getHByWSize2(context, displayW);
    }

    /**
     * 宽度固定
     * 高度 = 宽度 * 1／2
     *
     * @param context
     * @return ReSize
     */
    public static ReSize getHByWSize3(Context context) {
        int displayW = context.getResources().getDisplayMetrics().widthPixels;
        return getHByWSize3(context, displayW);
    }

    /**
     * 宽度固定
     * 高度 = 宽度 * 1／2
     *
     * @param context
     * @return ReSize
     */
    public static ReSize getHByWSize4(Context context) {
        int displayW = context.getResources().getDisplayMetrics().widthPixels;
        return getHByWSize4(context, displayW);
    }

    /**
     * 宽度固定
     * 高度 = 宽度 * 1／2
     *
     * @param context
     * @return ReSize
     */
    public static ReSize c(Context context) {
        int displayW = context.getResources().getDisplayMetrics().widthPixels;
        return getHByWSize4(context, displayW);
    }

    /**
     * 宽度固定
     * 高度 = 宽度 * 1／2
     *
     * @param context
     * @return ReSize
     */
    public static ReSize getHByWSize6(Context context) {
        int displayW = context.getResources().getDisplayMetrics().widthPixels;
        return getHByWSize6(context, displayW);
    }


    /**
     * 宽度固定
     * 高度 = 宽度 * 1／2f
     *
     * @param context
     * @return ReSize
     */
    public static ReSize getHByWSize2(Context context, int width) {
        return getReSize(context, width, 2f);
    }

    /**
     * 宽度固定
     * 高度 = 宽度 * 1／3f
     *
     * @param context
     * @return ReSize
     */
    public static ReSize getHByWSize3(Context context, int width) {
        return getReSize(context, width, 3f);
    }

    /**
     * 宽度固定
     * 高度 = 宽度 * 1／4f
     *
     * @param context
     * @return ReSize
     */
    public static ReSize getHByWSize4(Context context, int width) {
        return getReSize(context, width, 4f);
    }

    /**
     * 宽度固定
     * 高度 = 宽度 * 1／5f
     *
     * @param context
     * @return ReSize
     */
    public static ReSize getHByWSize5(Context context, int width) {
        return getReSize(context, width, 5f);
    }

    /**
     * 宽度固定
     * 高度 = 宽度 * 1／6f
     *
     * @param context
     * @return ReSize
     */
    public static ReSize getHByWSize6(Context context, int width) {
        return getReSize(context, width, 6f);
    }
}
