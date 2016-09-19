package com.lwj.utils.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by lwj on 16/9/19.
 * liuwenjie@goumin.com
 */
public abstract class BaseAdapterDelegate<T> implements AdapterDelegate<T> {
    protected Context context;
    protected int type;

    public BaseAdapterDelegate(Context context, int type) {
        this.context = context;
        this.type = type;
    }

    @Override
    public int getItemViewType() {
        return this.type;
    }

    @Override
    public abstract boolean isForViewType(@NonNull ArrayList<T> items, int position);

    @Override
    public abstract View getView(int position, View convertView, @NonNull ArrayList<T> items);
}
