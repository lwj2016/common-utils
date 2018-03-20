package com.lwj.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;

/**
 * Created by lwj on 2018/3/20.
 * lwjfork@gmail.com
 */

public class BroadcasUtil {


    /**
     * 注册广播
     *
     * @param context
     * @param receiver
     * @param action
     */
    public static void register(Context context, BroadcastReceiver receiver, String... action) {
        IntentFilter filter = new IntentFilter();
        for (String s : action) {
            filter.addAction(s);
        }
        context.registerReceiver(receiver, filter);
    }


}
