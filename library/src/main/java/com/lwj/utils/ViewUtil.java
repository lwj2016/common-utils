package com.lwj.utils;

import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by lwj on 2016/3/8.
 * lwjfork@gmail.com
 */
public class ViewUtil {
    @SuppressWarnings("unchecked")
    public static <V extends View> V findViewById(Activity ac, int id) {
        return (V) ac.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    public static <V extends View> V findViewById(View view, int id) {
        return (V) view.findViewById(id);
    }


    public static boolean isVisible(View _view) {
        return _view.getVisibility() == View.VISIBLE;
    }

    public static boolean isGone(View view) {
        return view.getVisibility() == View.GONE;
    }

    public static void viewGone(View _view) {
        if (_view != null && _view.getVisibility() != View.GONE) {
            _view.setVisibility(View.GONE);
        }

    }

    public static void viewVisible(View _view) {
        if (_view != null && _view.getVisibility() != View.VISIBLE) {
            _view.setVisibility(View.VISIBLE);
        }

    }


    public static LinearLayout findLinearLayoutById(View view, int id) {
        return findViewById(view, id);
    }

    public static RelativeLayout findRelativeLayoutById(View view, int id) {
        return findViewById(view, id);
    }


    public static FrameLayout findFrameLayoutById(View view, int id) {
        return findViewById(view, id);
    }

    public static TextView findTextViewById(View view, int id) {

        return findViewById(view, id);
    }

    public static Button findButtonById(View view, int id) {

        return findViewById(view, id);
    }

    public static ImageView findImageViewById(View view, int id) {
        return findViewById(view, id);
    }

    public static CheckBox findCheckBoxById(View view, int id) {
        return findViewById(view, id);
    }

    public static LinearLayout findLinearLayoutById(Activity activity, int id) {
        return findViewById(activity, id);
    }

    public static RelativeLayout findRelativeLayoutById(Activity activity, int id) {
        return findViewById(activity, id);
    }


    public static FrameLayout findFrameLayoutById(Activity activity, int id) {
        return findViewById(activity, id);
    }

    public static TextView findTextViewById(Activity activity, int id) {

        return findViewById(activity, id);
    }

    public static TextView setTvText(Activity activity, int id, String text) {
        TextView textView = findTextViewById(activity, id);
        textView.setText(text);
        return textView;
    }

    public static Button setBtnText(Activity activity, int id, String text) {
        Button button = findButtonById(activity, id);
        button.setText(text);
        return button;
    }

    public static Button findButtonById(Activity activity, int id) {

        return findViewById(activity, id);
    }

    public static ImageView findImageViewById(Activity activity, int id) {
        return findViewById(activity, id);
    }

    public static CheckBox findCheckBoxById(Activity activity, int id) {
        return findViewById(activity, id);
    }


}
