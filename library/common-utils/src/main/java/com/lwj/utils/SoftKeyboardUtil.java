package com.lwj.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * soft key board help
 *
 * @author CX
 */
public class SoftKeyboardUtil {
    private Context mContext;

    public SoftKeyboardUtil(Context context) {
        this.mContext = context;
    }

    /**
     * show soft keyboard
     */
    public void ShowSoftKeyboard(int index) {
        if (index == 0) {
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    InputMethodManager m = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                    m.showSoftInput(((Activity) mContext).getCurrentFocus(), 0);
                }
            }, 1000);
        } else {
            InputMethodManager m = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
            m.showSoftInput(((Activity) mContext).getCurrentFocus(), 0);

        }
    }

    /**
     * hide soft keyboard
     */
    public void HideSoftKeyboard() {
        InputMethodManager m = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (m != null && ((Activity) mContext).getCurrentFocus() != null) {
            m.hideSoftInputFromWindow(((Activity) mContext).getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void HideSoftKeyboard(View v) {
        InputMethodManager m = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (m != null && v != null) {
            m.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

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
