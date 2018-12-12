package com.common.lib.base.adapter.adapter_view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by lwj on 16/7/21.
 * liuwenjie@goumin.com
 */
public class ExceptionAdapter<T extends View> extends BaseAdapter {
    private T exceptionLayout;
    private Context mContext;

    public ExceptionAdapter(Context context, T exceptionLayout) {
        this.mContext = context;
        this.exceptionLayout = exceptionLayout;
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return exceptionLayout;
    }

    public T getExceptionLayout() {
        return exceptionLayout;
    }

}
