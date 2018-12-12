package com.common.lib.base.adapter.recycle_view.delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by lwj on 2017/1/17.
 * lwjfork@gmail.com
 */

public interface IAdapterDelegate<T> {

    boolean isForViewType(@NonNull T item, int position);

    int getViewType();

    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent);

    void onBindViewHolder(@NonNull T item, int position, @NonNull RecyclerView.ViewHolder holder);
}
