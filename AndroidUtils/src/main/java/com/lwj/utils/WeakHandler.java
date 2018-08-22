package com.lwj.utils;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by lwj on 2018/8/21.
 * lwjfork@gmail.com
 */

public abstract class WeakHandler<T> extends Handler {


    private WeakReference<T> weakReference;

    public WeakHandler(T activity) {
        setTarget(activity);
    }

    private void setTarget(T activity) {
        weakReference = new WeakReference<>(activity);
    }

    @Override
    public void handleMessage(Message msg) {
        if (weakReference == null) {
            return;
        }
        T weakObj = weakReference.get();
        if (weakObj != null) {
            handleMsg(msg, weakObj);
        }
    }


    protected abstract void handleMsg(Message message, T weakObj);

    private void destroy() {
        if (weakReference != null) {
            weakReference.clear();
        }
        removeCallbacksAndMessages(null);
    }


    public static void destroyHandler(WeakHandler... handlers) {
        for (WeakHandler handler : handlers) {
            if (handler == null) {
                return;
            }
            handler.destroy();
        }

    }
}
