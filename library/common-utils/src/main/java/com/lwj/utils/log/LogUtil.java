package com.lwj.utils.log;

import android.util.Log;

/**
 * Created by lwj on 2016/3/8.
 * lwjfork@gmail.com
 */
public class LogUtil extends Timber {
    public static void setLog(boolean open) {
        if (open) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new LogUtil.CrashReportingTree());
        }

    }

    private static class CrashReportingTree extends Timber.Tree {
        private CrashReportingTree() {
        }

        protected void log(int priority, String tag, String message, Throwable t) {
            if (priority != Log.VERBOSE && priority != Log.DEBUG) {
                if (t != null && priority != Log.ERROR && priority == Log.WARN) {

                }

            }
        }
    }
}
