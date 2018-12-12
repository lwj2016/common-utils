package com.common.lib.base.adapter.adapter_view.delegate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;

import com.common.lib.base.adapter.adapter_view.single.SimpleHolder;

import java.util.ArrayList;

/**
 * Created by lwj on 2017/10/27.
 * lwjfork@gmail.com
 */

public abstract class SimpleAdapterDelegate<T> implements IAdapterDelegate<T> {


    private Context context;
    private int layoutID;

    public SimpleAdapterDelegate(Context context, int layoutID) {
        this.context = context;
        this.layoutID = layoutID;
    }

    @Override
    public abstract boolean isForViewType(@NonNull ArrayList<T> items, int position, int count);

    @Override
    public View getView(int position, View convertView, @NonNull ArrayList<T> items) {

        SimpleHolder holder = SimpleHolder.getViewHolder(context, layoutID, convertView);
        T item = null;
        if (position < items.size()) {
            item = items.get(position);
        }
        fillData(holder, item, position, items.size());
        return holder.getRootView();
    }

    public abstract void fillData(SimpleHolder viewHolder, T item, int position, int count);

}
