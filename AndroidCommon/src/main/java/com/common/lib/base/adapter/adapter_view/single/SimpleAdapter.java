package com.common.lib.base.adapter.adapter_view.single;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.common.lib.base.adapter.adapter_view.ArrayListAdapter;

import java.util.ArrayList;



/**
 * Created by lwj on 2017/10/26.
 * lwjfork@gmail.com
 */

public abstract class SimpleAdapter<T> extends ArrayListAdapter<T> {
    private int layoutID;

    public SimpleAdapter(Context context, int layoutID) {
        super(context);
        this.layoutID = layoutID;
    }

    public SimpleAdapter(Context context, ArrayList _list, int layoutID) {
        super(context, _list);
        this.layoutID = layoutID;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        SimpleHolder viewHolder = getViewHolder(layoutID, convertView);
        T item = getItem(position);
        fillData(viewHolder, item, position, getCount());
        return viewHolder.getRootView();
    }


    public abstract void fillData(SimpleHolder viewHolder, T item, int position, int count);

}
