package com.common.lib.utils.log;

import android.util.Log;

/**
 * Created by lwj on 2018/4/10.
 * lwjfork@gmail.com
 * Copy from https://github.com/JakeWharton/timber
 *
 */

public class CrashReportingTree extends Tree {
    public CrashReportingTree() {
    }

    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
        if (priority != Log.VERBOSE && priority != Log.DEBUG) {
            if (t != null && priority != Log.ERROR && priority == Log.WARN) {

            }

        }
    }

}