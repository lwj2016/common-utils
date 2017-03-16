package com.lwj.utils.adapter.base;

import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by lwj on 2017/3/16.
 * lwjfork@gmail.com
 */

public class ViewHolder {

    private ViewHolder viewHolder;
    private SparseArray<View> views;
    private View rootView;

    private ViewHolder(View rootView) {
        this.rootView = rootView;
        views = new SparseArray<>();
        rootView.setTag(viewHolder);
    }

    public static ViewHolder getViewHolder(View view) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder(view);
        }
        return viewHolder;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T get(int id) {
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

    public TextView getTextView(int id) {

        return get(id);
    }

    public Button getButton(int id) {

        return get(id);
    }

    public ImageView getImageView(int id) {
        return get(id);
    }




}
