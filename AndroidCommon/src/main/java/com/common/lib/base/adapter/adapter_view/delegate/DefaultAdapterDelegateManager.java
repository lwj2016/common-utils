package com.common.lib.base.adapter.adapter_view.delegate;

import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;


/**
 * Created by lwj on 16/9/18.
 * liuwenjie@goumin.com
 */
public class DefaultAdapterDelegateManager<T> implements IAdapterDelegateManager<T> {

    ArrayList<IAdapterDelegate<T>> delegates;

    public DefaultAdapterDelegateManager() {
        this.delegates = new ArrayList<>();
    }

    @Override
    public void addDelegate(@NonNull IAdapterDelegate<T> delegate) {
        this.delegates.add(delegate);
    }

    @Override
    public int getItemViewType(@NonNull ArrayList<T> items, int position, int count) {
        int typeCount = delegates.size();
        for (int i = 0; i < typeCount; i++) {
            IAdapterDelegate<T> delegate = delegates.get(i);
            if (delegate.isForViewType(items, position, count)) {
                return i;
            }
        }

        throw new IllegalArgumentException("No delegate found. This type of adapter is not support");
    }

    @Override
    public int getViewTypeCount() {
        return delegates.size();
    }

    @Override
    public View getView(int position, View convertView, @NonNull ArrayList<T> items, int count) {
        for (IAdapterDelegate<T> delegate : delegates) {
            if (delegate.isForViewType(items, position, count)) {
                return delegate.getView(position, convertView, items);
            }
        }
        throw new IllegalArgumentException("No delegate found. This type of adapter is not support");
    }
}
