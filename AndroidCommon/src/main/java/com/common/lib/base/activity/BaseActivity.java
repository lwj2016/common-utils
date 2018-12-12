package com.common.lib.base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.common.lib.base.life.LifeActivity;

/**
 * Created by lwj on 2018/3/2.
 * lwjfork@gmail.com
 */

public abstract class BaseActivity extends LifeActivity implements IBaseActivity {


    public Activity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(getRootView(this));
        initBundleData(this);
        setUpViews();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }

    /**
     * 显示Toast
     *
     * @param message  msg String res
     * @param duration toast duration
     * @see Toast#LENGTH_LONG
     * @see Toast#LENGTH_SHORT
     */
    public void showToast(String message, int duration) {
        Toast.makeText(mContext, message, duration).show();
    }
}