package com.common.lib.base.adapter.adapter_view.delegate;

import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by lwj on 16/9/18.
 * liuwenjie@goumin.com
 */
public interface IAdapterDelegate<T> {


    boolean isForViewType(@NonNull ArrayList<T> items, int position, int count);

    View getView(int position, View convertView, @NonNull ArrayList<T> items);
}
