package com.common.lib.base.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.common.lib.base.life.LifeFragment;


/**
 * Created by lwj on 2017/1/16.
 * lwjfork@gmail.com
 */

public abstract class BaseFragment extends LifeFragment {

    protected Activity mContext;

    //  add  contentView
    protected abstract int getRootViewId();

    /**
     * init view
     *
     * @param view contentView
     */
    protected void setUpViews(View view) {

    }


    private View setContentView(LayoutInflater inflater) {
        View view = null;
        if (getRootViewId() > 0) {
            view = inflater.inflate(getRootViewId(), null);
        }
        return view;
    }

    /**
     * bundle data
     *
     * @param bundle
     */
    protected void onBundleData(Bundle bundle) {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        Bundle bundle = getArguments();
        if (bundle != null && !bundle.isEmpty()) {
            onBundleData(bundle);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = setContentView(inflater);
        if (view != null) {
            setUpViews(view);
        }
        return view;
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T findViewByID(View parentView, int id) {
        return (T) parentView.findViewById(id);
    }

    public void showToast(String message) {
        showToast(message, Toast.LENGTH_SHORT);
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

    public void showToast(@StringRes int strRes) {
        showToast(strRes, Toast.LENGTH_SHORT);
    }

    /**
     * 显示Toast
     *
     * @param strRes   msg String res
     * @param duration toast duration
     * @see Toast#LENGTH_LONG
     * @see Toast#LENGTH_SHORT
     */
    public void showToast(@StringRes int strRes, int duration) {
        showToast(getResources().getString(strRes), duration);
    }
}