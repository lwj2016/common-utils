package com.lwj.utils.adapter.delegate;

import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by lwj on 16/9/21.
 * liuwenjie@goumin.com
 *
 * @param <T> Data  source
 */
public interface IAdapterDelegateManager<T> {

    void addDelegate(@NonNull IAdapterDelegate<T> delegate);

    /**
     * 获取 itemview 的类型type
     *
     * @param items
     * @param position
     * @return
     */
    int getItemViewType(@NonNull ArrayList<T> items, int position);

    /**
     * 获取 item 的 typeCount
     *
     * @return
     */
    int getViewTypeCount();

    /**
     * 获取 item View
     *
     * @param position    index of item
     * @param convertView
     * @param items
     * @return
     */
    View getView(int position, View convertView, @NonNull ArrayList<T> items);
}
