package com.lwj.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.lwj.utils.log.LogUtil;

import java.security.InvalidParameterException;

/**
 * Created by lwj on 16/3/9.
 * lwjfork@gmail.com
 */
public class ActivityUtil {

    private static long innerTime = 0L;

    /**
     * 每次打开同一个界面的时间间隔
     */
    private static long INNER_INTERVAL = 1000L;

    public static void setActivityInterval(long intervalTime) {
        INNER_INTERVAL = intervalTime;
    }

    /**
     * 检测是否可以打开界面
     *
     * @return
     */
    public static boolean checkValid() {

        long nowTime = System.currentTimeMillis();
        if (nowTime - innerTime > INNER_INTERVAL) {
            return true;
        }
        innerTime = nowTime;
        LogUtil.i("open fast and the interval is %s", String.valueOf(INNER_INTERVAL));
        return false;
    }


    /**
     * 一个Activity 开启 另一个 Activity
     *
     * @param context
     * @param cls
     */
    public static void startActivity(Context context, Class cls, int flags) {
        startActivity(context, cls, null, flags);
    }

    /**
     * 一个Activity 开启 另一个 Activity
     *
     * @param context
     * @param cls
     */
    public static void startActivity(Context context, Class cls) {
        startActivity(context, cls, 0);
    }


    /**
     * 一个Activity 开启 另一个 Activity，带Bundle
     *
     * @param context
     * @param cls
     * @param bundle
     */
    public static void startActivity(Context context, Class cls, Bundle bundle) {
        startActivity(context, cls, bundle, 0);
    }


    /**
     * 一个Activity 开启 另一个 Activity，带Bundle
     *
     * @param context
     * @param cls
     * @param bundle
     */
    public static void startActivity(Context context, Class cls, Bundle bundle, int flags) {
        Intent intent = new Intent();
        if (cls == null) {
            throw new InvalidParameterException("cls can't be null");
        }
        intent.setClass(context, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (flags > 0) {
            intent.setFlags(flags);
        }
        startActivity(context, intent);
    }


    /**
     * 调用某一个Action
     *
     * @param context
     * @param intent
     */
    public static void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }


    /**
     * 一个Activity 开启 另一个 Activity，并且可以返回处理的数据
     *
     * @param act
     * @param cls
     * @param Code
     */
    public static void startActivityForResult(Context act, Class cls, int Code) {
        if (act instanceof Activity) {
            startActivityForResult(act, cls, null, Code);

        } else {
            throw new InvalidParameterException("act must be Activity");
        }
    }


    /**
     * 一个Activity 开启 另一个 Activity，并且可以返回处理的数据
     *
     * @param act
     * @param cls
     * @param code
     */
    public static void startActivityForResult(Activity act, Class cls, int code) {

        startActivityForResult(act, cls, null, code);
    }

    /**
     * 一个Activity 开启 另一个 Activity，带Bundle，并且可以返回处理的数据
     *
     * @param act
     * @param cls
     * @param bundle
     * @param Code
     */
    public static void startActivityForResult(Context act, Class cls, Bundle bundle, int Code) {
        if (act instanceof Activity) {
            startActivityForResult((Activity) act, cls, bundle, Code, 0);

        } else {
            throw new InvalidParameterException("act must be Activity");
        }

    }

    /**
     * 一个Activity 开启 另一个 Activity，带Bundle，并且可以返回处理的数据
     *
     * @param act
     * @param cls
     * @param bundle
     * @param Code
     */
    public static void startActivityForResult(Activity act, Class cls, Bundle bundle, int Code, int flags) {
        Intent intent = new Intent();
        if (cls == null) {
            throw new InvalidParameterException("cls can't be null");
        }
        intent.setClass(act, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        if (flags > 0) {
            intent.setFlags(flags);
        }
        startActivityForResult(act, intent, Code);
    }


    public static void startActivityForResult(Activity act, Intent intent, int code) {
        act.startActivityForResult(intent, code);
    }


    public static boolean isRunning(Context context) {
        if (context != null && context instanceof Activity) {

            Activity activity = (Activity) context;
            if (activity.isFinishing()) {
                return false;
            }
            if (Build.VERSION.SDK_INT >= 17 && activity.isDestroyed()) {
                return false;
            }
            return true;
        }

        return false;
    }
}
