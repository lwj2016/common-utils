package com.lwj.utils;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by lwj on 2016/3/8.
 * lwjfork@gmail.com
 */
public class ViewUtil {

    public static <V extends View> V find(Activity ac, int id) {
        return (V) ac.findViewById(id);
    }

    public static <V extends View> V find(View view, int id) {
        return (V) view.findViewById(id);
    }

    public static void viewGone(View _view) {
        if (_view != null && _view.getVisibility() != View.GONE) {
            _view.setVisibility(View.GONE);
        }

    }

    public static void viewVisible(View _view) {
        if (_view != null && _view.getVisibility() != View.VISIBLE) {
            _view.setVisibility(View.VISIBLE);
        }

    }

    public static <T> T getItemModel(ListView listView, int i) {

        if (listView == null) {
            return null;
        }
        if (i < 0) {
            return null;
        }
        int headerCount = listView.getHeaderViewsCount();
        if (i >= headerCount) {
            return (T) listView.getAdapter().getItem(i);
        }
        return null;
    }
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if(listAdapter != null) {
            int totalHeight = 0;

            for(int params = 0; params < listAdapter.getCount(); ++params) {
                View listItem = listAdapter.getView(params, (View)null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }

            ViewGroup.LayoutParams var5 = listView.getLayoutParams();
            var5.height = totalHeight + listView.getDividerHeight() * (listAdapter.getCount() - 1);
            listView.setLayoutParams(var5);
        }
    }
}
