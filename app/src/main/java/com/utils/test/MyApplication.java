package com.utils.test;

import android.app.Application;

import com.lwj.utils.context.GlobalContext;
import com.lwj.utils.log.LogUtil;

/**
 * Created by lwj on 2017/10/27.
 * lwjfork@gmail.com
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();


        LogUtil.setLog(BuildConfig.DEBUG);

        GlobalContext.setApplication(this);

    }
}
