package com.common.lib.base.life;

import android.app.Activity;

/**
 * Created by lwj on 2017/1/16.
 * lwjfork@gmail.com
 */

public interface IActivityLife {

    void onCreate(Activity activity);

    void onResume(Activity activity);

    void onStart(Activity activity);

    void onRestart(Activity activity);

    void onPause(Activity activity);

    void onStop(Activity activity);

    void onDestroy(Activity activity);

    void onNewIntent(Activity activity);

}
