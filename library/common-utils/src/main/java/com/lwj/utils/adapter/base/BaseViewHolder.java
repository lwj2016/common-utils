package com.lwj.utils.adapter.base;

import android.view.View;

import com.lwj.utils.ViewUtil;

/**
 * Created by lwj on 16/8/30.
 * liuwenjie@goumin.com
 *
 * @param <T> T is a javabean, you can get this item_data of AdapterView
 */
public abstract class BaseViewHolder<T> {
    public View rootView;

    public BaseViewHolder(View rootView, int rootViewID) {
        if (rootView == null) {

        }
        this.rootView = rootView;
        this.rootView.setTag(this);
    }

    public abstract void initView();

    /**
     * find View
     *
     * @param root   root View
     * @param viewId id of view
     * @param <V>    must be subClass of View.class
     * @return view
     */
    public <V extends View> V findViewById(View root, int viewId) {
        return ViewUtil.find(root, viewId);
    }

    public abstract void setItemData(T itemData);
}
