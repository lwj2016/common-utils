package com.lwj.utils;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

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
    public static GradientDrawable createRectangleShape(int radius, int color) {

        if (radius <= 0) {
            return createRectangleShape(color);
        }

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setCornerRadius(radius);
        shape.setColor(color);
        return shape;
    }

    /**
     * 创建矩形 shape 资源
     *
     * @param color
     * @return
     */
    public static GradientDrawable createRectangleShape(int color) {

        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.RECTANGLE);
        shape.setColor(color);
        return shape;

    }

    /**
     * 创建 选择器
     *
     * @param normalDrawable
     * @param pressDrawable
     * @return
     */
    public static StateListDrawable createStateListDrawable(Drawable normalDrawable, Drawable pressDrawable) {
        StateListDrawable stateListDrawable = new StateListDrawable();

        addState(stateListDrawable, new int[]{android.R.attr.state_pressed}, pressDrawable);
        addState(stateListDrawable, new int[]{android.R.attr.state_selected}, pressDrawable);
        addState(stateListDrawable, new int[]{android.R.attr.state_checked}, pressDrawable);
        addState(stateListDrawable, new int[]{}, normalDrawable);
        return stateListDrawable;
    }

    public static StateListDrawable addState(StateListDrawable stateListDrawable, int[] stateSet, Drawable drawable) {

        stateListDrawable.addState(stateSet, drawable);
        return stateListDrawable;
    }

    public static int praseColor(String color) {
        return Color.parseColor(color);
    }
}
