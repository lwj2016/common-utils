package com.lwj.utils;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created:2018/6/19
 * User：lwjfork
 * Email:lwjfork@gmail.com
 * Des:
 * ====================
 */

public class DrawableUtil {


    public static Drawable getBoundDrawable(@DrawableRes int id) {
        return getBoundDrawable(ResUtil.getDrawable(id));
    }

    public static Drawable getBoundDrawable(Drawable sourceDrawable) {
        if (sourceDrawable == null) {
            return null;
        }
        sourceDrawable.setBounds(0, 0, sourceDrawable.getMinimumWidth(), sourceDrawable.getMinimumHeight());
        return sourceDrawable;
    }


    public static <T extends TextView> void setCompoundDrawables(T view, @Nullable Drawable leftRes, @Nullable Drawable topRes, @Nullable Drawable rightRes, @Nullable Drawable bottomRes) {
        view.setCompoundDrawables(getBoundDrawable(leftRes),
                getBoundDrawable(topRes),
                getBoundDrawable(rightRes),
                getBoundDrawable(bottomRes));
    }


    public static <T extends TextView> void setCompoundDrawables(T view, int leftRes, int topRes, int rightRes, int bottomRes) {
        view.setCompoundDrawables(getBoundDrawable(leftRes),
                getBoundDrawable(topRes),
                getBoundDrawable(rightRes),
                getBoundDrawable(bottomRes));
    }


    public static <T extends TextView> void setLeftDrawable(T view, @DrawableRes int id) {
        view.setCompoundDrawables(DrawableUtil.getBoundDrawable(id), null, null, null);
    }

    public static <T extends TextView> void setTopDrawable(T view, @DrawableRes int id) {
        view.setCompoundDrawables(null, DrawableUtil.getBoundDrawable(id), null, null);
    }

    public static <T extends TextView> void setRightDrawable(T view, @DrawableRes int id) {
        view.setCompoundDrawables(null, null, DrawableUtil.getBoundDrawable(id), null);
    }

    public static <T extends TextView> void setBottomDrawable(T view, @DrawableRes int id) {
        view.setCompoundDrawables(null, null, null, DrawableUtil.getBoundDrawable(id));
    }

    public static <T extends TextView> void setLeftDrawable(T view, Drawable drawable) {
        view.setCompoundDrawables(DrawableUtil.getBoundDrawable(drawable), null, null, null);
    }

    public static <T extends TextView> void setTopDrawable(T view, Drawable drawable) {
        view.setCompoundDrawables(null, DrawableUtil.getBoundDrawable(drawable), null, null);
    }

    public static <T extends TextView> void setRightDrawable(T view, Drawable drawable) {
        view.setCompoundDrawables(null, null, DrawableUtil.getBoundDrawable(drawable), null);
    }

    public static <T extends TextView> void setBottomDrawable(T view, Drawable drawable) {
        view.setCompoundDrawables(null, null, null, DrawableUtil.getBoundDrawable(drawable));
    }


}
