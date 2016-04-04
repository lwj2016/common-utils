package com.lwj.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by lwj on 16/3/9.
 * lwjfork@gmail.com
 */
public class ActivityUtil {

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
        startActivity(context, intent);
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
        startActivity(context, intent);
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
        act.startActivityForResult(intent, Code);
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
        act.startActivityForResult(intent, Code);
    }

    /**
     * 调用某一个Action
     *
     * @param context
     * @param intent
     */
    public static void startActivity(Context context, Intent intent, boolean isFinish) {
        context.startActivity(intent);
        if (isFinish) {
            ((Activity) context).finish();
        }
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
}
