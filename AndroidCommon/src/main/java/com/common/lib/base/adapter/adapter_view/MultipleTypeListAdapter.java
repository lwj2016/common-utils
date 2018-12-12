package com.common.lib.base.adapter.adapter_view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.common.lib.base.adapter.adapter_view.delegate.DefaultAdapterDelegateManager;
import com.common.lib.base.adapter.adapter_view.delegate.IAdapterDelegateManager;

import java.util.ArrayList;



/**
 * Created by lwj on 16/9/18.
 * liuwenjie@goumin.com
 */
public class MultipleTypeListAdapter<T> extends ArrayListAdapter<T> {
    protected IAdapterDelegateManager<T> delegateManager;

    public IAdapterDelegateManager<T> createDelegateManager() {
        return new DefaultAdapterDelegateManager<>();
    }

    public MultipleTypeListAdapter(Context context) {
        this(context, new ArrayList<T>());
    }

    public MultipleTypeListAdapter(Context context, ArrayList<T> _list) {
        super(context, _list);
        this.delegateManager = createDelegateManager();
    }

    @Override
    public int getItemViewType(int position) {
        return delegateManager.getItemViewType(getList(), position, getCount());
    }

    @Override
    public int getViewTypeCount() {
        return delegateManager.getViewTypeCount() + 1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return delegateManager.getView(position, convertView, getList(), getCount());
    }
}
