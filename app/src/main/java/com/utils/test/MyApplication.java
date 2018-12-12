package com.utils.test;

import android.app.Application;

import com.common.lib.utils.log.LogUtil;
import com.facebook.stetho.Stetho;
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

        LogUtil.d("isNetConnected---> %s", "TEST");
        LogUtil.dT("tag", "isNetConnected---> %s", "TEST");
    }
}
