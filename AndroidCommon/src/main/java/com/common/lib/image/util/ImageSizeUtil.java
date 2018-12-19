package com.common.lib.image.util;


import com.common.lib.utils.DisplayUtil;

/**
 * Created by liuwenjie on 2017/9/18.
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
    public static ReSize getReSize(float widthRatio, float whRatio) {

        float viewW = DisplayUtil.getScreenWidthInPx() * widthRatio;
        float viewH = viewW / whRatio;

        return new ReSize((int) viewW, (int) viewH);
    }

    /**
     * 获取方形size
     *
     * @param widthRatio 宽度占屏幕的几分之几
     * @return ReSize
     */
    public static ReSize getSquareReSize(float widthRatio) {
        return getReSize(widthRatio, 1f);
    }

    /**
     * 方形图，宽度充满屏幕
     *
     * @return ReSize
     */
    public static ReSize getSquareReSize1() {
        return getSquareReSize(1f);
    }

    /**
     * 方形图，宽度 占屏幕的 1／2
     *
     * @return ReSize
     */
    public static ReSize getSquareReSize2() {
        return getSquareReSize(1f / 2f);
    }

    /**
     * 方形图，宽度 占屏幕的 1／3
     *
     * @return ReSize
     */
    public static ReSize getSquareReSize3() {
        return getSquareReSize(1f / 3f);
    }

    /**
     * 方形图，宽度 占屏幕的 1／4
     *
     * @return ReSize
     */
    public static ReSize getSquareReSize4() {
        return getSquareReSize(1f / 4f);
    }

    /**
     * 方形图，宽度 占屏幕的 1／5
     *
     * @return ReSize
     */
    public static ReSize getSquareReSize5() {
        return getSquareReSize(1f / 5f);
    }

    /**
     * 方形图，宽度 占屏幕的 1／6
     *
     * @return ReSize
     */
    public static ReSize getSquareReSize6() {
        return getSquareReSize(1f / 6f);
    }

    /**
     * 宽度固定
     * 高度 = 宽度 * 1／2
     *
     * @return ReSize
     */
    public static ReSize getHByWSize2() {
        int displayW = DisplayUtil.getScreenWidthInPx();
        return getHByWSize2(displayW);
    }

    /**
     * 宽度固定
     * 高度 = 宽度 * 1／2
     *
     * @return ReSize
     */
    public static ReSize getHByWSize3() {
        int displayW = DisplayUtil.getScreenWidthInPx();
        return getHByWSize3(displayW);
    }

    /**
     * 宽度固定
     * 高度 = 宽度 * 1／2
     *
     * @return ReSize
     */
    public static ReSize getHByWSize4() {
        int displayW = DisplayUtil.getScreenWidthInPx();
        return getHByWSize4(displayW);
    }

    /**
     * 宽度固定
     * 高度 = 宽度 * 1／2
     *
     * @return ReSize
     */
    public static ReSize c() {
        int displayW = DisplayUtil.getScreenWidthInPx();
        return getHByWSize4(displayW);
    }

    /**
     * 宽度固定
     * 高度 = 宽度 * 1／2
     *
     * @return ReSize
     */
    public static ReSize getHByWSize6() {
        int displayW = DisplayUtil.getScreenWidthInPx();
        return getHByWSize6(displayW);
    }


    /**
     * 宽度固定
     * 高度 = 宽度 * 1／2f
     *
     * @return ReSize
     */
    public static ReSize getHByWSize2(int width) {
        return getReSize(width, 2f);
    }

    /**
     * 宽度固定
     * 高度 = 宽度 * 1／3f
     *
     * @return ReSize
     */
    public static ReSize getHByWSize3(int width) {
        return getReSize(width, 3f);
    }

    /**
     * 宽度固定
     * 高度 = 宽度 * 1／4f
     *
     * @return ReSize
     */
    public static ReSize getHByWSize4(int width) {
        return getReSize(width, 4f);
    }

    /**
     * 宽度固定
     * 高度 = 宽度 * 1／5f
     *
     * @return ReSize
     */
    public static ReSize getHByWSize5(int width) {
        return getReSize(width, 5f);
    }

    /**
     * 宽度固定
     * 高度 = 宽度 * 1／6f
     *
     * @return ReSize
     */
    public static ReSize getHByWSize6(int width) {
        return getReSize(width, 6f);
    }
}
