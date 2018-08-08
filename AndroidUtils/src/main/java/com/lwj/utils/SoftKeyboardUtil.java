package com.lwj.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.lwj.utils.context.GlobalContext;

/**
 * Created by lwj on 16/3/9.
 * lwjfork@gmail.com
 */
public class SoftKeyboardUtil {


    public static void showInputMethod(View v) {
        InputMethodManager imm = OSUtils.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);
    }

    public static boolean hideInputMethod(View v) {
        InputMethodManager imm = OSUtils.getSystemService(Context.INPUT_METHOD_SERVICE);
        return imm != null && imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }


}
