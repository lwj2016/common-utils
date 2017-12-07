package com.lwj.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.lwj.utils.log.DebugUtil;
import com.lwj.utils.log.LogUtil;

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
    private static boolean checkValid() {

        long nowTime = System.currentTimeMillis();
        if (nowTime - innerTime > INNER_INTERVAL) {
            return true;
        }
        innerTime = nowTime;
        LogUtil.i("open fast and the interval is %s", String.valueOf(INNER_INTERVAL));
        return false;
    }


    /**
     * 调用某一个Action
     *
     * @param context
     * @param intent
     */
    public static void startActivity(Context context, Intent intent, boolean isFinish) {
        if (!checkValid()) {
            return;
        }
        context.startActivity(intent);
        if (isFinish) {
            if (context instanceof Activity) {
                ((Activity) context).finish();
            } else {
                LogUtil.w("this %s can't convert to activity", DebugUtil.getTag(context));
            }
        }
    }

    public static void startActivityForResult(Activity act, Intent intent, int code) {
        if (!checkValid()) {
            return;
        }
        act.startActivityForResult(intent, code);
    }

    /**
     * 调用某一个Action
     *
     * @param context
     * @param intent
     */
    public static void startActivity(Context context, Intent intent) {
        startActivity(context, intent, false);
    }

    /**
     * 一个Activity 开启 另一个 Activity
     *
     * @param context
     * @param cls
     */
    public static void startActivity(Context context, Class cls) {
        Intent intent = new Intent(context, cls);
        startActivity(context, intent, false);
    }


    /**
     * 一个Activity 开启 另一个 Activity
     *
     * @param context
     * @param cls
     */
    public static void startActivity(Context context, Class cls, boolean isFinish) {
        Intent intent = new Intent(context, cls);
        startActivity(context, intent, isFinish);
    }


    /**
     * 一个Activity 开启 另一个 Activity，带Bundle
     *
     * @param context
     * @param cls
     * @param bundle
     */
    public static void startActivity(Context context, Class cls, Bundle bundle, boolean isFinish) {
        Intent intent = new Intent(context, cls);
        intent.setClass(context, cls);
        intent.putExtras(bundle);
        startActivity(context, intent, isFinish);
    }

    /**
     * 一个Activity 开启 另一个 Activity，带Bundle
     *
     * @param context
     * @param cls
     * @param bundle
     */
    public static void startActivity(Context context, Class cls, Bundle bundle) {
        Intent intent = new Intent(context, cls);
        intent.setClass(context, cls);
        intent.putExtras(bundle);
        startActivity(context, intent, false);
    }


    /**
     * 一个Activity 开启 另一个 Activity，并且可以返回处理的数据
     *
     * @param act
     * @param cls
     * @param Code
     */
    public static void startActivityForResult(Context act, Class cls, int Code) {
        Intent intent = new Intent(act, cls);
        startActivityForResult(act, intent, Code);
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
        Intent intent = new Intent(act, cls);
        intent.putExtras(bundle);
        startActivityForResult(act, intent, Code);
    }


    /**
     * 一个Activity 开启 另一个 Activity，并且可以返回处理的数据
     *
     * @param act
     * @param cls
     * @param Code
     */
    public static void startActivityForResult(Activity act, Class cls, int Code) {
        Intent intent = new Intent(act, cls);
        startActivityForResult(act, intent, Code);
    }


    /**
     * 一个Activity 开启 另一个 Activity，带Bundle，并且可以返回处理的数据
     *
     * @param act
     * @param cls
     * @param bundle
     * @param Code
     */
    public static void startActivityForResult(Activity act, Class cls, Bundle bundle, int Code) {
        Intent intent = new Intent(act, cls);
        intent.putExtras(bundle);
        startActivityForResult(act, intent, Code);
    }


    /**
     * 一个Activity 开启 另一个 Activity，带Bundle，并且可以返回处理的数据
     *
     * @param act
     * @param intent
     * @param Code
     */
    public static void startActivityForResult(Context act, Intent intent, int Code) {
        if (act instanceof Activity) {
            Activity activity = (Activity) act;
            startActivityForResult(activity, intent, Code);
        }
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
