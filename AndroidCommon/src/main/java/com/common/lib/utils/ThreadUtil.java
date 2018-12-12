package com.common.lib.utils;

import android.app.Activity;
import android.os.Looper;
import android.view.View;

/**
 * Created:2018/7/2
 * User：lwjfork
 * Email:lwjfork@gmail.com
 * Des: 线程检查类
 * ====================
 */

public final  class ThreadUtil {


    /**
     * Throws an {@link IllegalArgumentException} if called on a thread other than the main
     * thread.
     */
    public static void checkUIThread() {
        if (!isOnMainThread()) {
            throw new IllegalArgumentException("You must call this method on the main thread");
        }
    }

    /**
     * Throws an {@link IllegalArgumentException} if called on the main thread.
     */
    public static void checkBackgroundThread() {
        if (!isOnBackgroundThread()) {
            throw new IllegalArgumentException("You must call this method on a background thread");
        }
    }


    /**
     * Returns {@code true} if called on the main thread, {@code false} otherwise.
     */
    public static boolean isOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * Returns {@code true} if called on a background thread, {@code false} otherwise.
     */
    public static boolean isOnBackgroundThread() {
        return !isOnMainThread();
    }


    public static void runOnUIThread(View view, Runnable runnable) {
        view.post(runnable);
    }


    public static void runOnUIThread(Activity activity, Runnable runnable) {
        activity.runOnUiThread(runnable);
    }


    


}
