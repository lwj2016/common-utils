package com.utils.test;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.lwj.utils.NetUtil;
import com.lwj.utils.context.GlobalContext;
import com.lwj.utils.log.LogUtil;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by lwj on 2017/10/27.
 * lwjfork@gmail.com
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Stetho.initializeWithDefaults(this);

        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .build());

        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);

        LogUtil.setLog(BuildConfig.DEBUG);

        new Thread() {
            @Override
            public void run() {
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
        }.start();
    }
}
