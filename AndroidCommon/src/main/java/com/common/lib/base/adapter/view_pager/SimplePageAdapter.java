package com.common.lib.base.adapter.view_pager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created:2018/5/30
 * User：lwjfork
 * Des:
 * ====================
 */
@SuppressWarnings({"unchecked",
        "unused",})
public class SimplePageAdapter<T extends View> extends PagerAdapter implements IViewPagerAdapterHelper<T, SimplePageAdapter<T>> {


    private List<T> views = new ArrayList<>();
    private List<CharSequence> titles = new ArrayList<>();

    public SimplePageAdapter(ArrayList<T> views) {
        super();
        this.views = views;
    }

    public SimplePageAdapter() {
        super();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(titles == null || titles.size() < 1) {
            return null;
        }
        if(position <= titles.size() - 1) {
            return titles.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        if(views == null) {
            return 0;
        }
        return views.size();
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = views.get(position);
        container.addView(itemView);
        return itemView;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public SimplePageAdapter<T> addTitle(CharSequence charSequence) {
        titles.add(charSequence);
        return this;
    }

    @Override
    public SimplePageAdapter<T> addItem(T item) {
        views.add(item);
        return this;
    }

    @Override
    public SimplePageAdapter<T> addItemTitles(List<CharSequence> itemTitles) {
        this.titles = itemTitles;
        return this;
    }

    @Override
    public SimplePageAdapter<T> addItems(List<T> items) {
        this.views = items;
        return this;
    }
}
