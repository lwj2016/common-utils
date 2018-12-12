package com.common.lib.base.adapter.recycle_view.delegate;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by lwj on 2017/1/18.
 * lwjfork@gmail.com
 */

public interface IAdapterDelegateManager<T> {

    /**
     * 添加 IAdapterDelegate
     *
     * @param delegate itemDelegate
     */
    IAdapterDelegateManager<T> addDelegate(@NonNull IAdapterDelegate<T> delegate);



    /**
     * 获取 itemView 的类型type
     *
     * @param items    data source
     * @param position index of item
     * @return itemType
     */
    int getItemViewType(@NonNull ArrayList<T> items, int position);


    /**
     * 获取 ViewHolder
     *
     * @param parent   RecycleView
     * @param viewType viewType
     * @return ViewHolder
     */
    RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 绑定 ViewHolder 并填充数据
     *
     * @param items
     * @param position
     * @param holder
     */
    void onBindViewHolder(@NonNull ArrayList<T> items, int position, @NonNull RecyclerView.ViewHolder holder);


}
