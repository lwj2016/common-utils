package com.common.lib.base.adapter.recycle_view.delegate;

/**
 * Created by lwj on 2017/1/18.
 * lwjfork@gmail.com
 */

public abstract class BaseAdapterDelegate<T> implements IAdapterDelegate<T> {

    protected int viewType;

    public BaseAdapterDelegate(int viweType) {
        this.viewType = viweType;
    }

    @Override
    public int getViewType() {
        return viewType;
    }
}
