package com.common.lib.base.adapter.adapter_view.single;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by lwj on 2017/3/16.
 * lwjfork@gmail.com
 */

public class SimpleHolder {

    private SparseArray<View> views;
    private View rootView;

    private SimpleHolder(View rootView) {
        this.rootView = rootView;
        views = new SparseArray<>();
        rootView.setTag(this);
    }

    public static SimpleHolder getViewHolder(Context context, int layoutId, View convertView) {
        SimpleHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, layoutId, null);
            viewHolder = new SimpleHolder(convertView);
        } else {
            viewHolder = (SimpleHolder) convertView.getTag();
        }
        return viewHolder;
    }


    @SuppressWarnings("unchecked")
    public <T extends View> T findViewById(int id) {
        View childView = views.get(id);
        if (childView == null) {
            childView = rootView.findViewById(id);
            views.put(id, childView);
        }
        return (T) childView;
    }

    public View getRootView() {
        return rootView;
    }

    public LinearLayout findLinearLayoutById(int id) {
        return findViewById(id);
    }

    public RelativeLayout findRelativeLayoutById(int id) {
        return findViewById(id);
    }


    public FrameLayout findFrameLayoutById(int id) {
        return findViewById(id);
    }

    public TextView findTextViewById(int id) {

        return findViewById(id);
    }

    public Button findButtonById(int id) {

        return findViewById(id);
    }

    public ImageView findImageViewById(int id) {
        return findViewById(id);
    }

    public CheckBox findCheckBoxById(int id) {
        return findViewById(id);
    }


    public TextView setTextView(int id, String text) {
        TextView textView = findTextViewById(id);
        textView.setText(text);
        return textView;
    }

    public TextView setTextView(int id, String text, View.OnClickListener onClickListener) {
        TextView textView = findTextViewById(id);
        textView.setText(text);
        textView.setOnClickListener(onClickListener);
        return textView;
    }

    public ImageView setImageViewResource(int id, int resId) {
        ImageView imageView = findImageViewById(id);
        findImageViewById(id).setImageResource(resId);
        return imageView;
    }

    public ImageView setImageViewResource(int id, int resId, View.OnClickListener onClickListener) {
        ImageView imageView = findImageViewById(id);
        imageView.setImageResource(resId);
        imageView.setOnClickListener(onClickListener);
        return imageView;
    }

    public Button setButton(int id, String text) {
        Button button = findButtonById(id);
        button.setText(text);
        return button;
    }

    public Button setButton(int id, String text, View.OnClickListener onClickListener) {
        Button button = findButtonById(id);
        button.setText(text);
        button.setOnClickListener(onClickListener);
        return button;
    }

}
