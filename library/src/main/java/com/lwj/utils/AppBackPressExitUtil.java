package com.lwj.utils;

import android.app.Activity;
import android.view.KeyEvent;

/**
 * Created by lwj on 2017/3/21.
 * lwjfork@gmail.com
 */

public abstract class AppBackPressExitUtil {


    private long exitTime = 0;

    private long interval = 2000L;
    private Activity activity;

    public AppBackPressExitUtil(Activity activity, long interval) {
        this.interval = interval;
        this.activity = activity;
    }

    public AppBackPressExitUtil(Activity activity) {
        this(activity, 2000L);
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
        } else {
            return activity.onKeyDown(keyCode, event);
        }
    }


    public void onFinish() {
        activity.finish();
    }

    public abstract void onInterval();
}
