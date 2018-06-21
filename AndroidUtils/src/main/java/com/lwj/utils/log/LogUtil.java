package com.lwj.utils.log;

/**
 * Created by lwj on 2016/3/8.
 * lwjfork@gmail.com
 */
public class LogUtil extends Timber {
    public static void setLog(boolean open) {
        if (open) {
            Timber.plant(new DebugTree());
        } else {
            Timber.plant(new CrashReportingTree());
        }

    }
}
