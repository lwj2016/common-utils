package com.common.lib.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Created by lwj on 2018/8/9.
 * lwjfork@gmail.com
 */

public final class ActivityManager {


    private static Stack<Activity> stack = new Stack<>();// Activity栈

    /**
     * 移除所有activity
     *
     */
    public static void popAll() {
        while (!stack.isEmpty()) {
            pop();
        }
    }

    public static int stackSize() {
        return stack.size();
    }

    /**
     * 移除位于栈顶的activity
     *
     */
    public static void pop() {
        Activity activity = stack.pop();
        finishActivity(activity);
    }

    /**
     * 移除指定activity
     *
     * @param activity
     */
    public static void pop(Activity activity) {
        finishActivity(activity);
        stack.remove(activity);
    }

    /**
     * 移除并关闭指定某一类的activity
     *
     * @param cls
     */
    public static void popClass(Class<? extends Activity> cls) {
        Stack<Activity> newStack = new Stack<>();
        for (Activity a : stack) {
            if (a.getClass().equals(cls)) {
                finishActivity(a);
            } else {
                newStack.push(a);
            }
        }
        stack = newStack;
    }

    /**
     * 获取在栈顶的activity
     *
     * @return
     *
     */
    public static Activity current() {
        if (stack.isEmpty()) {
            return null;
        }
        return stack.peek();
    }

    /**
     * 添加activity到栈中
     *
     * @param activity
     */
    public static void push(Activity activity) {
        stack.push(activity);
    }

    /**
     * 保留某一类的activity，其它的都关闭并移出栈
     *
     * @param cls
     */
    public static void retain(Class<? extends Activity> cls) {
        Stack<Activity> newStack = new Stack<>();
        for (Activity a : stack) {
            if (a.getClass().equals(cls)) {
                newStack.push(a);
            } else {
                finishActivity(a);
            }
        }
        stack = newStack;
    }

    public static void finishActivity(Activity activity) {
        if (activity == null) {
            return;
        }
        if (OSUtils.hasJELLY_BEAN_MR1_17() && activity.isDestroyed()) {
            return;
        }
        if (activity.isFinishing()) {
            return;
        }
        activity.finish();
    }


}
