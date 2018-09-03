package com.lwj.utils.log;

import android.util.Log;

/**
 * Created by lwj on 2018/4/10.
 * lwjfork@gmail.com
 * <p>
 * Copy from https://github.com/JakeWharton/timber
 */
public abstract class Tree {
    public final ThreadLocal<String> explicitTag = new ThreadLocal<>();

    public Tree() {
    }

    protected String getTag() {
        String tag = (String) this.explicitTag.get();
        if (tag != null) {
            this.explicitTag.remove();
        }

        return tag;
    }

    public void v(String message, Object... args) {
        this.prepareLog(Log.VERBOSE, null, message, args);
    }

    public void v(Throwable t, String message, Object... args) {
        this.prepareLog(Log.VERBOSE, t, message, args);
    }

    public void d(String message, Object... args) {
        this.prepareLog(Log.DEBUG, null, message, args);
    }

    public void d(Throwable t, String message, Object... args) {
        this.prepareLog(Log.DEBUG, t, message, args);
    }

    public void i(String message, Object... args) {
        this.prepareLog(Log.INFO, null, message, args);
    }

    public void i(Throwable t, String message, Object... args) {
        this.prepareLog(Log.INFO, t, message, args);
    }

    public void w(String message, Object... args) {
        this.prepareLog(Log.WARN, null, message, args);
    }

    public void w(Throwable t, String message, Object... args) {
        this.prepareLog(Log.WARN, t, message, args);
    }

    public void e(String message, Object... args) {
        this.prepareLog(Log.ERROR, null, message, args);
    }

    public void e(Throwable t, String message, Object... args) {
        this.prepareLog(Log.ERROR, t, message, args);
    }

    public void wtf(String message, Object... args) {
        this.prepareLog(Log.ASSERT, null, message, args);
    }

    public void wtf(Throwable t, String message, Object... args) {
        this.prepareLog(Log.ASSERT, t, message, args);
    }

    private void prepareLog(int priority, Throwable t, String message, Object... args) {
        if (message != null && message.length() == 0) {
            message = null;
        }

        if (message == null) {
            if (t == null) {
                return;
            }

            message = Log.getStackTraceString(t);
        } else {
            if (args.length > 0) {
                message = String.format(message, args);
            }

            if (t != null) {
                message = message + "\n" + Log.getStackTraceString(t);
            }
        }

        this.log(priority, this.getTag(), message, t);
    }

    protected abstract void log(int var1, String var2, String var3, Throwable var4);

    public void vT(String tag, String message, Object... args) {
        this.prepareLog(tag, Log.VERBOSE, null, message, args);
    }

    public void vT(String tag, Throwable t, String message, Object... args) {
        this.prepareLog(tag, Log.VERBOSE, t, message, args);
    }

    public void dT(String tag, String message, Object... args) {
        this.prepareLog(tag, Log.DEBUG, null, message, args);
    }

    public void dT(String tag, Throwable t, String message, Object... args) {
        this.prepareLog(tag, Log.DEBUG, t, message, args);
    }

    public void iT(String tag, String message, Object... args) {
        this.prepareLog(tag, Log.INFO, null, message, args);
    }

    public void iT(String tag, Throwable t, String message, Object... args) {
        this.prepareLog(tag, Log.INFO, t, message, args);
    }

    public void wT(String tag, String message, Object... args) {
        this.prepareLog(tag, Log.WARN, null, message, args);
    }

    public void wT(String tag, Throwable t, String message, Object... args) {
        this.prepareLog(tag, Log.WARN, t, message, args);
    }

    public void eT(String tag, String message, Object... args) {
        this.prepareLog(tag, Log.ERROR, null, message, args);
    }

    public void eT(String tag, Throwable t, String message, Object... args) {
        this.prepareLog(tag, Log.ERROR, t, message, args);
    }

    public void wtfT(String tag, String message, Object... args) {
        this.prepareLog(tag, Log.ASSERT, null, message, args);
    }

    public void wtfT(String tag, Throwable t, String message, Object... args) {
        this.prepareLog(tag, Log.ASSERT, t, message, args);
    }

    private void prepareLog(String tag, int priority, Throwable t, String message, Object... args) {
        if (message != null && message.length() == 0) {
            message = null;
        }

        if (message == null) {
            if (t == null) {
                return;
            }

            message = Log.getStackTraceString(t);
        } else {
            if (args.length > 0) {
                message = String.format(message, args);
            }

            if (t != null) {
                message = message + "\n" + Log.getStackTraceString(t);
            }
        }

        this.log(priority, tag, message, t);
    }

}
