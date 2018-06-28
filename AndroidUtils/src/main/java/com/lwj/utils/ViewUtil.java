package com.lwj.utils;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lwj.utils.context.GlobalContext;

/**
 * Created by lwj on 2016/3/8.
 * lwjfork@gmail.com
 */
public class ViewUtil {
    @SuppressWarnings("unchecked")
    public static <V extends View> V findViewById(Activity ac, @IdRes int id) {
        return (V) ac.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    public static <V extends View> V findViewById(View view, @IdRes int id) {
        return (V) view.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    public static <V extends View> V findViewById(Dialog view, @IdRes int id) {
        return (V) view.findViewById(id);
    }

    @SuppressWarnings("unchecked")
    public static <V extends View> V findViewById(Window window, @IdRes int id) {
        return (V) window.findViewById(id);
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




    public static LayoutInflater newInflate() {
        return LayoutInflater.from(GlobalContext.getContext());
    }

    public static View inflate(@LayoutRes int layoutId) {
        return inflate(layoutId, null, false);
    }

    public static View inflate(@LayoutRes int resource, @Nullable ViewGroup root, boolean attachToRoot) {
        return newInflate().inflate(resource, root, attachToRoot);
    }

    public static View inflate(@LayoutRes int resource, @Nullable ViewGroup root) {
        return newInflate().inflate(resource, root, root != null);
    }

    public static LinearLayout findLinearLayoutById(View view, @IdRes int id) {
        return findViewById(view, id);
    }

    public static RelativeLayout findRelativeLayoutById(View view, @IdRes int id) {
        return findViewById(view, id);
    }


    public static FrameLayout findFrameLayoutById(View view, @IdRes int id) {
        return findViewById(view, id);
    }

    public static TextView findTextViewById(View view, @IdRes int id) {

        return findViewById(view, id);
    }

    public static Button findButtonById(View view, @IdRes int id) {

        return findViewById(view, id);
    }

    public static ImageView findImageViewById(View view, @IdRes int id) {
        return findViewById(view, id);
    }

    public static CheckBox findCheckBoxById(View view, @IdRes int id) {
        return findViewById(view, id);
    }

    public static LinearLayout findLinearLayoutById(Activity activity, @IdRes int id) {
        return findViewById(activity, id);
    }

    public static RelativeLayout findRelativeLayoutById(Activity activity, @IdRes int id) {
        return findViewById(activity, id);
    }


    public static FrameLayout findFrameLayoutById(Activity activity, @IdRes int id) {
        return findViewById(activity, id);
    }

    public static TextView findTextViewById(Activity activity, @IdRes int id) {

        return findViewById(activity, id);
    }

    public static TextView setTvText(Activity activity, @IdRes int id, String text) {
        TextView textView = findTextViewById(activity, id);
        textView.setText(text);
        return textView;
    }


    public static TextView setTvText(View view, @IdRes int id, String text) {
        TextView textView = findTextViewById(view, id);
        textView.setText(text);
        return textView;
    }

    public static Button setBtnText(Activity activity, @IdRes int id, String text) {
        Button button = findButtonById(activity, id);
        button.setText(text);
        return button;
    }

    public static Button findButtonById(Activity activity, @IdRes int id) {

        return findViewById(activity, id);
    }

    public static ImageView findImageViewById(Activity activity, @IdRes int id) {
        return findViewById(activity, id);
    }

    public static CheckBox findCheckBoxById(Activity activity, @IdRes int id) {
        return findViewById(activity, id);
    }


}
