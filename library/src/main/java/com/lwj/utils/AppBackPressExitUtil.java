package com.lwj.utils;

import android.view.KeyEvent;

/**
 * Created by lwj on 2017/3/21.
 * lwjfork@gmail.com
 */

public abstract class AppBackPressExitUtil {


    private long exitTime = 0;

    private long interval = 2000L;

    public AppBackPressExitUtil(long interval) {
        this.interval = interval;
    }

    public AppBackPressExitUtil() {
        this(2000L);
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > interval) {
                onInterval();
                exitTime = System.currentTimeMillis();
            } else {
                onFinish();
            }
            return true;
        }
        return false;
    }

    public abstract void onFinish();

    public abstract void onInterval();
}
