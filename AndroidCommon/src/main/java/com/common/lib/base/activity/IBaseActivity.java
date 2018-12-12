package com.common.lib.base.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.common.lib.utils.ResUtil;


/**
 * Created by lwj on 2018/5/7.
 * lwjfork@gmail.com
 */

public interface IBaseActivity {


    @SuppressWarnings("unchecked")
    public <T extends View> T findView(int id);


    default void initBundleData(Activity activity) {
        Bundle bundle = activity.getIntent().getExtras();
        if (bundle != null && !bundle.isEmpty()) {
            onBundleData(bundle);
        }
    }

    default void onBundleData(Bundle bundle) {

    }

    default void setUpViews() {

    }

    /**
     * 获取
     *
     * @return layout-id
     */
    int getRootViewId();

    default View getRootView(Context context) {
        View view = null;
        int layoutId = getRootViewId();
        if (layoutId < 0) {
            return null;
        } else {
            view = LayoutInflater.from(context).inflate(layoutId, null);
        }
        return view;
    }


    default void showToast(String message) {
        showToast(message, Toast.LENGTH_SHORT);
    }

    /**
     * 显示Toast
     *
     * @param message  msg String res
     * @param duration toast duration
     * @see Toast#LENGTH_LONG
     * @see Toast#LENGTH_SHORT
     */
    void showToast(String message, int duration);

    default void showToast(@StringRes int strRes) {
        showToast(strRes, Toast.LENGTH_SHORT);
    }

    /**
     * 显示Toast
     *
     * @param strRes   msg String res
     * @param duration toast duration
     * @see Toast#LENGTH_LONG
     * @see Toast#LENGTH_SHORT
     */
    default void showToast(@StringRes int strRes, int duration) {
        showToast(ResUtil.getString(strRes), duration);
    }
}
