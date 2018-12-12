package com.common.lib.base.adapter.recycle_view.delegate;


import android.support.annotation.NonNull;

import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;

/**
 * Created by lwj on 2017/1/16.
 * lwjfork@gmail.com
 */

public class DefaultAdapterDelegateManager<T> implements IAdapterDelegateManager<T> {


    ArrayList<IAdapterDelegate<T>> delegates;

    public DefaultAdapterDelegateManager() {
        this.delegates = new ArrayList<>();
    }

    @Override
    public IAdapterDelegateManager<T> addDelegate(@NonNull IAdapterDelegate<T> delegate) {
        this.delegates.add(delegate);
        return this;
    }

    @Override
    public int getItemViewType(@NonNull ArrayList<T> items, int position) {
        T itemData = items.get(position);
        for (IAdapterDelegate<T> item : delegates) {
            if (item.isForViewType(itemData, position)) {
                return item.getViewType();
            }
        }
        return 0;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        for (IAdapterDelegate<T> item : delegates) {
            if (item.getViewType() == viewType) {
                return item.onCreateViewHolder(parent);
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ArrayList<T> items, int position, @NonNull RecyclerView.ViewHolder holder) {
        T itemData = items.get(position);
        for (IAdapterDelegate<T> item : delegates) {
            if (item.isForViewType(itemData, position)) {
                item.onBindViewHolder(itemData, position, holder);
                break;
            }
        }
    }
}
