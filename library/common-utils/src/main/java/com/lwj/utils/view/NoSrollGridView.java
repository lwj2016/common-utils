package com.lwj.utils.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by lwj on 16/8/30.
 * liuwenjie@goumin.com
 */
public class NoSrollGridView extends GridView {
    public NoSrollGridView(Context context) {
        super(context);
    }

    public NoSrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoSrollGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(21)
    public NoSrollGridView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
