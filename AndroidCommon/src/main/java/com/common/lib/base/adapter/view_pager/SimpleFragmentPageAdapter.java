package com.common.lib.base.adapter.view_pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created:2018/5/21
 * User：lwjfork
 * Email:lwjfork@gmail.com
 * Des:
 * ====================
 */
@SuppressWarnings({"unchecked",
        "unused",
        "WeakerAccess",
        "SameParameterValue"})
public class SimpleFragmentPageAdapter<T extends Fragment> extends FragmentPagerAdapter implements IViewPagerAdapterHelper<T, SimpleFragmentPageAdapter<T>> {


    private List<T> fragments = new ArrayList<>();

    private List<CharSequence> titles = new ArrayList<>();


    public SimpleFragmentPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public SimpleFragmentPageAdapter(FragmentManager fm, List<T> fragments, List<CharSequence> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
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
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public SimpleFragmentPageAdapter<T> addTitle(CharSequence charSequence) {
        titles.add(charSequence);
        return this;
    }

    @Override
    public SimpleFragmentPageAdapter<T> addItem(T item) {
        fragments.add(item);
        return this;
    }

    @Override
    public SimpleFragmentPageAdapter<T> addItemTitles(List<CharSequence> itemTitles) {
        titles = itemTitles;
        return this;
    }

    @Override
    public SimpleFragmentPageAdapter<T> addItems(List<T> items) {
        fragments = items;
        return this;
    }


}
