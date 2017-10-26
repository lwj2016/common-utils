package com.lwj.utils.adapter.base;

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

import com.lwj.utils.ViewUtil;

/**
 * Created by lwj on 2017/3/16.
 * lwjfork@gmail.com
 */

public class SimpleViewHolder {

    private SparseArray<View> views;
    private View rootView;

    private SimpleViewHolder(View rootView) {
        this.rootView = rootView;
        views = new SparseArray<>();
        rootView.setTag(this);
    }

    public static SimpleViewHolder getViewHolder(Context context, int layoutId, View convertView) {
        SimpleViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, layoutId, null);
            viewHolder = new SimpleViewHolder(convertView);
        } else {
            viewHolder = (SimpleViewHolder) convertView.getTag();
        }
        return viewHolder;
    }


    @SuppressWarnings("unchecked")
    public <T extends View> T findViewById(int id) {
        View childView = views.get(id);
        if (childView == null) {
            childView = ViewUtil.find(rootView, id);
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


}
