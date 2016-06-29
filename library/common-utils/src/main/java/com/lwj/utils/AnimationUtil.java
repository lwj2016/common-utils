package com.lwj.utils;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;

import java.util.ArrayList;

/**
 * Created by lwj on 16/6/29.
 * Des:
 */
public class AnimationUtil {


    public static AnimatorSet buildSyncAnim(AnimatorSet animatorSet, ArrayList<Animator> animators) {
        if (animatorSet == null) {
            animatorSet = new AnimatorSet();
        }

        if (CollectionUtil.isListMoreOne(animators)) {
            int count = animators.size();
            for (int i = 0; i < count - 1; i++) {

                animatorSet.play(animators.get(i)).with(animators.get(i+1));
            }
        }
        return animatorSet;
    }

    public static AnimatorSet buildSyncAnim( ArrayList<Animator> animators) {
       return buildSyncAnim(null,animators);
    }
    public static AnimatorSet buildSequentAnim(AnimatorSet animatorSet, ArrayList<Animator> animators){
        if (animatorSet == null) {
            animatorSet = new AnimatorSet();
        }

        animatorSet.playSequentially(animators);
        return animatorSet;
    }


    public static AnimatorSet buildSequentAnim( ArrayList<Animator> animators) {
        return buildSequentAnim(null,animators);
    }

   
}
