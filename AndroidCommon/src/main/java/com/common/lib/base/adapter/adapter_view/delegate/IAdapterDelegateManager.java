package com.common.lib.base.adapter.adapter_view.delegate;

import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by lwj on 16/9/21.
 * liuwenjie@goumin.com
 *
 * @param <T> Data  source
 */
public interface IAdapterDelegateManager<T>{


    /**
     * 添加 IAdapterDelegate
     *
     * @param delegate itemDelegate
     */
    void addDelegate(@NonNull IAdapterDelegate<T> delegate);

    /**
     * 获取 itemView 的类型type
     *
     * @param items    data source
     * @param position index of item
     * @return itemType
     */
    int getItemViewType(@NonNull ArrayList<T> items, int position, int count);

    /**
     * 获取 item 的 typeCount
     *
     * @return typeCount
     */
    int getViewTypeCount();

    /**
     * 获取 item View
     *
     * @param position    index of item
     * @param convertView cache view of item
     * @param items       data source
     * @return itemView
     */
    View getView(int position, View convertView, @NonNull ArrayList<T> items, int count);
}