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

    public WeakHandler(T target) {
        setTarget(target);
    }

    private void setTarget(T target) {
        weakReference = new WeakReference<>(target);
    }

    @Override
    public void handleMessage(Message msg) {
        T weakObj = isNeedHandle();
        if (weakObj != null) {
            handleMsg(msg, weakObj);
        }
    }


    protected T isNeedHandle() {

        if (weakReference == null) {
            destroyHandler(this);
            return null;
        }
        T weakObj = weakReference.get();
        if (weakObj != null) {
            return weakObj;
        } else {
            destroyHandler(this);
            return null;
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
