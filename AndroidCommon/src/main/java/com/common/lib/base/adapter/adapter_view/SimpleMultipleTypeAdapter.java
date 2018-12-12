package com.common.lib.base.adapter.adapter_view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.common.lib.base.adapter.adapter_view.delegate.SimpleAdapterDelegate;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by lwj on 2017/10/27.
 * lwjfork@gmail.com
 */

public class SimpleMultipleTypeAdapter<T> extends ArrayListAdapter<T> {


    ArrayList<SimpleAdapterDelegate<T>> delegates = new ArrayList<>();

    public SimpleMultipleTypeAdapter(Context context) {
        super(context);
    }

    public SimpleMultipleTypeAdapter(Context context, ArrayList<T> _list) {
        super(context, _list);
    }


    @Override
    public int getViewTypeCount() {
        return delegates.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        int count = delegates.size();
        for (int i = 0; i < count; i++) {
            SimpleAdapterDelegate<T> delegate = delegates.get(i);
            if (delegate.isForViewType(mList, position, getCount())) {
                return i;
            }
        }
        return count;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        for (SimpleAdapterDelegate<T> delegate : delegates) {
            if (delegate.isForViewType(mList, position, getCount())) {
                return delegate.getView(position, convertView, mList);
            }
        }
        return new View(mContext);
    }

    @SafeVarargs
    public final SimpleMultipleTypeAdapter<T> addTypeDelegates(SimpleAdapterDelegate<T>... delegate) {
        Collections.addAll(delegates, delegate);
        return this;
    }

}
