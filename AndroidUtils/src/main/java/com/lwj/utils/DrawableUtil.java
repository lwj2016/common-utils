package com.lwj.utils;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;

/**
 * Created:2018/6/19
 * User：liuwenjie
 * Email:liuwnejie180423@credithc.com
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








}
