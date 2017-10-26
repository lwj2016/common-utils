package com.lwj.utils.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.lwj.utils.adapter.base.ArrayListAdapter;
import com.lwj.utils.adapter.delegate.IAdapterDelegateManager;

import java.util.ArrayList;

/**
 * Created by lwj on 16/9/18.
 * liuwenjie@goumin.com
 */
public abstract class MultipleTypeListAdapter<T> extends ArrayListAdapter<T> {
    protected IAdapterDelegateManager<T> delegateManager;

    public abstract IAdapterDelegateManager<T> createDelegateManager();

    public MultipleTypeListAdapter(Context context) {
        this(context, new ArrayList<T>());
    }

    public MultipleTypeListAdapter(Context context, ArrayList<T> _list) {
        super(context, _list);
        this.delegateManager = createDelegateManager();
    }

    @Override
    public int getItemViewType(int position) {
        return delegateManager.getItemViewType(getList(), position);
    }

    @Override
    public int getViewTypeCount() {
        return delegateManager.getViewTypeCount() + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return delegateManager.getView(position, convertView, getList());
    }
}
