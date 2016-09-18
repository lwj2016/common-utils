package com.lwj.utils.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by lwj on 16/9/18.
 * liuwenjie@goumin.com
 */
public abstract class MultipeTypeListAdapter<T> extends ArrayListAdapter<T> {
    AdapterDelegateManager<T> delegateManager = new AdapterDelegateManager<>();

    public MultipeTypeListAdapter(Context context) {
        super(context);
    }

    public MultipeTypeListAdapter(Context context, ArrayList<T> _list) {
        super(context, _list);
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
