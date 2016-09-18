package com.lwj.utils.adapter;

import android.support.annotation.NonNull;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by lwj on 16/9/18.
 * liuwenjie@goumin.com
 */
public class AdapterDelegateManager<T> {

    ArrayList<AdapterDelegate<T>> delegates;

    public AdapterDelegateManager() {
        this.delegates = new ArrayList<>();
    }

    public void addDelegate(@NonNull AdapterDelegate<T> delegate) {
        this.delegates.add(delegate);
    }

    @SuppressWarnings("unchecked")
    public int getItemViewType(@NonNull ArrayList<T> items, int position) {
        for (AdapterDelegate delegate : delegates) {
            if (delegate.isForViewType(items, position)) {
                return delegate.getItemViewType();
            }
        }
        throw new IllegalArgumentException("No delegate found. This type of adapter is not support");
    }
    public int getViewTypeCount() {
        return delegates.size();
    }
    @SuppressWarnings("unchecked")
    public View getView(int position, View convertView, @NonNull ArrayList<T> items) {
        for (AdapterDelegate delegate : delegates) {
            if (delegate.isForViewType(items, position)) {
                return delegate.getView(position, convertView, items);
            }
        }
        throw new IllegalArgumentException("No delegate found. This type of adapter is not support");
    }
}
