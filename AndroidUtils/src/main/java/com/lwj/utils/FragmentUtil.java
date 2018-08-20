package com.lwj.utils;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by lwj on 16/3/9.
 * lwjfork@gmail.com
 */
public final  class FragmentUtil {

    /**
     * @param activity   android.support.v4.app.FragmentActivity
     * @param fragment   android.support.v4.app.Fragment
     * @param fragmentId 布局 id
     */
    public static void addFragmentIntoActivity(FragmentActivity activity, Fragment fragment, int fragmentId) {
        FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
        ft.replace(fragmentId, fragment);
        ft.commitAllowingStateLoss();
    }

    /**
     * @param baseFragment android.support.v4.app.Fragment
     * @param fragment     android.support.v4.app.Fragment
     * @param fragmentId   布局 id
     */
    public static void addFragmentIntoFragment(Fragment baseFragment, Fragment fragment, int fragmentId) {
        FragmentTransaction ft = baseFragment.getChildFragmentManager().beginTransaction();
        ft.replace(fragmentId, fragment);
        ft.commitAllowingStateLoss();
    }
}
