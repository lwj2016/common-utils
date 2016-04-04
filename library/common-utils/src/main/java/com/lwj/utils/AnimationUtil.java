package com.lwj.utils;

import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.lwj.utils.context.GlobalContext;

/**
 * Created by lwj on 16/4/4.
 * Des:  动画操作类
 */
public class AnimationUtil {

    /**
     * 获取动画
     *
     * @param animationID
     * @return
     */
    public static Animation getAnimation(int animationID) {
        return AnimationUtils.loadAnimation(GlobalContext.getContext(), animationID);
    }

}
