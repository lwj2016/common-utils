package com.utils.test;

import android.app.Application;

import com.lwj.utils.NetUtil;
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

        if (NetUtil.isNetConnected()) {
            LogUtil.d("isNetConnected---> %s", true);

            if (NetUtil.is2GConnected()) {
                LogUtil.d("is2GConnected---> %s", true);
            }

            if (NetUtil.is3GConnected()) {
                LogUtil.d("is3GConnected---> %s", true);
            }

            if (NetUtil.is4GConnected()) {
                LogUtil.d("is4GConnected---> %s", true);
            }

            if (NetUtil.isWifiConnected()) {
                LogUtil.d("isWifiConnected---> %s", true);
            }
        }

    }
}
