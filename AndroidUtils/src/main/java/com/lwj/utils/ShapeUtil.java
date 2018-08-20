package com.lwj.utils;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.support.annotation.ColorInt;

/**
 * Created by lwj on 16/7/12.
 * lwjfork@gmail.com
 * shape util
 */
public class ShapeUtil {


    /**
     * 创建圆角矩形 shape 资源
     *
     * @param radius int - px
     * @param color  int
     * @return shape
     */
    public static GradientDrawable getGradientDrawable(@ColorInt int color, int radius) {

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        if (radius > 0) {
            shape.setCornerRadius(radius);
        }
        shape.setColor(color);
        return shape;
    }


    /**
     * 创建矩形 shape 资源
     *
     * @param color
     * @return
     */
    public static GradientDrawable getGradientDrawable(@ColorInt int color) {

        return getGradientDrawable(color, 0);

    }

    /**
     * 创建 选择器
     *
     * @param normalDrawable
     * @param pressDrawable
     * @return
     */
    public static StateListDrawable createStateListDrawable(Drawable normalDrawable, Drawable pressDrawable) {
        return DrawableUtil.getStateDrawableBuilder()
                .addDrawableState(pressDrawable, android.R.attr.state_pressed)
                .addDrawableState(pressDrawable, android.R.attr.state_checked)
                .addDrawableState(pressDrawable, android.R.attr.state_selected)
                .addDrawableState(normalDrawable)
                .buildDrawable();
    }

    public static StateListDrawable addState(StateListDrawable stateListDrawable, int[] stateSet, Drawable drawable) {

        stateListDrawable.addState(stateSet, drawable);
        return stateListDrawable;
    }

}
