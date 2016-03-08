package com.lwj.utils;

import android.widget.ListView;

/**
 * Created by lwj on 2016/3/8.
 * liuwenjie@goumin.com
 */
public class ViewUtil {


    public static <T> T getItemModel(ListView listView,int i){

        if(listView == null){
            return null;
        }
        if(i < 0){
            return  null;
        }
        int headerCount = listView.getHeaderViewsCount();
        if (i >= headerCount) {
            return  (T) listView.getAdapter().getItem(i);
        }
        return null;
    }
}
