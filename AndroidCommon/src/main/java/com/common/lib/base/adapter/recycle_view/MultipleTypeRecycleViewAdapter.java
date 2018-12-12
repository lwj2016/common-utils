package com.common.lib.base.adapter.recycle_view;


import android.view.ViewGroup;

import com.common.lib.base.adapter.recycle_view.delegate.DefaultAdapterDelegateManager;
import com.common.lib.base.adapter.recycle_view.delegate.IAdapterDelegateManager;
import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;




/**
 * Created by lwj on 2017/1/18.
 * lwjfork@gmail.com
 */

public class MultipleTypeRecycleViewAdapter<T> extends RecycleViewListAdapter<T> {


    protected IAdapterDelegateManager<T> delegateManager;

    public IAdapterDelegateManager<T> createDelegateManager() {
        return new DefaultAdapterDelegateManager<>();
    }


    public ArrayList<T> datas = new ArrayList<>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return delegateManager.onCreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        delegateManager.onBindViewHolder(datas, position, holder);
    }

    @Override
    public int getItemViewType(int position) {
        return delegateManager.getItemViewType(datas, position);
    }

}
