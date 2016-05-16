package com.lwj.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * soft key board help
 *
 * @author CX
 */
public class SoftKeyboardUtil {


    public static void showInputMethod(Context ctx, View v) {
        // InputMethodManager imm = InputMethodManager.peekInstance();
        InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    }

    public static boolean hideInputMethod(Context ctx, View v) {
        // InputMethodManager imm = InputMethodManager.peekInstance();
        InputMethodManager imm = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            return imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
        return false;
    }


}
