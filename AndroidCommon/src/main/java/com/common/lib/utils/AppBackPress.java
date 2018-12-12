package com.common.lib.utils;

import android.view.KeyEvent;

/**
 * Created by lwj on 2017/3/21.
 * lwjfork@gmail.com
 * 双击退出程序
 */

public final  class AppBackPress {


    private long exitTime = 0;

    private long interval = 2000L;

    public AppBackPress(long interval) {
        this.interval = interval;
    }

    public AppBackPress() {
        this(2000L);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > interval) {
                if (onBackPressListener != null) {
                    onBackPressListener.onBackPressedInterval();
                }
                exitTime = System.currentTimeMillis();
            } else {
                if (onBackPressListener != null) {
                    onBackPressListener.onBackPressedFinish();
                }
            }
            return true;
        }
        return false;
    }


    private OnBackPressListener onBackPressListener;


    public void setOnBackPressListener(OnBackPressListener onBackPressListener) {
        this.onBackPressListener = onBackPressListener;
    }

    public interface OnBackPressListener {

        void onBackPressedInterval();

        void onBackPressedFinish();

    }
}
