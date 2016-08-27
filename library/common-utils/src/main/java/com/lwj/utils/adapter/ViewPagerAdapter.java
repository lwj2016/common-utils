package com.lwj.utils.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.lwj.utils.CollectionUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lwj on 16/8/19.
 * liuwenjie@goumin.com
 *
 * @param <T>   android.support.v4.app.Fragment
 */
public class ViewPagerAdapter<T extends Fragment> extends FragmentPagerAdapter {

    private List<T> mFragmentList = new ArrayList<>();
    private List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
    }

    public ViewPagerAdapter(FragmentManager manager, ArrayList<T> mFragmentList, List<String> mFragmentTitleList) {
        super(manager);
        this.mFragmentList = mFragmentList;
        this.mFragmentTitleList = mFragmentTitleList;
    }

    public ViewPagerAdapter(FragmentManager manager, ArrayList<T> mFragmentList) {
        super(manager);
        this.mFragmentList = mFragmentList;
    }

    @Override
    public T getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(T fragment, String title) {
        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
    }

    public void addFrag(T fragment) {
        mFragmentList.add(fragment);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (CollectionUtil.isListMoreOne(mFragmentList)) {
            return mFragmentTitleList.get(position);
        } else {
            return super.getPageTitle(position);
        }
    }
}