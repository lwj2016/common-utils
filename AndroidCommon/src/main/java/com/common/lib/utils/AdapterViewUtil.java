package com.common.lib.utils;

import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by lwj on 16/3/15.
 * Des: AdapterViewUtil
 */
public final  class AdapterViewUtil {

    @SuppressWarnings("unchecked")
    public static <T> T getItemModel(AdapterView adapterView, int i) {
        if (adapterView instanceof ListView) {
            return getItemModel((ListView) adapterView, i);
        } else if (adapterView instanceof GridView) {
            return getItemModel((GridView) adapterView, i);
        } else if (adapterView instanceof Gallery) {
            return getItemModel((Gallery) adapterView, i);
        }
        return (T) adapterView.getAdapter().getItem(i);
    }

    @SuppressWarnings("unchecked")
    private static <T> T getItemModel(ListView listView, int i) {

        if (listView == null) {
            return null;
        }
        if (i < 0) {
            return null;
        }
        int headerCount = listView.getHeaderViewsCount();
        int footerCount = listView.getFooterViewsCount();
        ListAdapter adapter = listView.getAdapter();
        int count = adapter.getCount();
        if (i >= headerCount && i < count - footerCount) {
            return (T) listView.getAdapter().getItem(i);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static <T> T getItemModel(GridView gridView, int i) {

        if (gridView == null) {
            return null;
        }
        if (i < 0) {
            return null;
        }
        int count = gridView.getAdapter().getCount();
        if (i >= count) {
            return null;
        }
        return (T) gridView.getAdapter().getItem(i);

    }

    @SuppressWarnings("unchecked")
    private static <T> T getItemModel(Gallery gallery, int i) {

        if (gallery == null) {
            return null;
        }
        if (i < 0) {
            return null;
        }
        int count = gallery.getAdapter().getCount();
        if (i >= count) {
            return null;
        }
        return (T) gallery.getAdapter().getItem(i);

    }


    /**
     * 判断 listview  是否滑动到顶部了
     *
     * @param listView
     * @return true  滑动顶部
     */
    public static boolean isAdapterViewAttach(AbsListView listView) {

        if (listView != null && listView.getChildCount() > 0) {
            if (listView.getChildAt(0).getTop() < 0) {
                return false;
            }

        }
        return true;
    }





}
