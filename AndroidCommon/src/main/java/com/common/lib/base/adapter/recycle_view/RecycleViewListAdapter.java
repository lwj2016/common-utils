package com.common.lib.base.adapter.recycle_view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * Created by lwj on 2017/1/20.
 * lwjfork@gmail.com
 */

public abstract class RecycleViewListAdapter<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public ArrayList<T> datas = new ArrayList<>();

    @Override
    public abstract RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        if (datas == null) {
            return 0;
        }
        return datas.size();
    }


    public void addItem(T item) {
        addLastItem(item);
    }

    public void addItem(T item, boolean isAnim) {
        addLastItem(item, isAnim);
    }

    public void addLastItem(T item) {
        addLastItem(item, false);
    }

    /**
     * 向最后位置添加条目
     *
     * @param item
     * @param isAnim
     */
    public void addLastItem(T item, boolean isAnim) {
        datas.add(item);
        if (isAnim) {
            notifyItemInserted(datas.size() - 1);
        } else {
            notifyDataSetChanged();
        }
    }

    public void addFirstItem(T item, boolean isAnim) {
        datas.add(0, item);
        if (isAnim) {
            notifyItemInserted(0);
        } else {
            notifyDataSetChanged();
        }
    }

    public void addFirstItem(T item) {
        addFirstItem(item, false);
    }

    public void add2First(ArrayList<T> datas, boolean isAnim) {
        this.datas.addAll(0, datas);
        if (isAnim) {
            notifyItemInserted(0);
        } else {
            notifyDataSetChanged();
        }
    }

    public void add2First(ArrayList<T> datas) {
        add2First(datas, false);
    }

    public void add2Last(ArrayList<T> datas, boolean isAnim) {

        if (isAnim) {
            int count = datas.size();
            this.datas.addAll(datas);
            notifyItemInserted(count);
        } else {
            this.datas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    public void addItem(T item, int position, boolean isAnim) {
        datas.add(position, item);
        if (isAnim) {
            notifyItemInserted(position);
        } else {
            notifyDataSetChanged();
        }
    }


    /**
     * 设置数据
     *
     * @param datdList
     */
    public void setDatdList(ArrayList<T> datdList) {
        this.datas = datdList;
        notifyDataSetChanged();
    }

}
