package com.common.lib.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by lwj on 2018/5/9.
 * lwjfork@gmail.com
 */

public class TabManager implements TabHost.OnTabChangeListener {
    private final FragmentActivity mActivity;
    private final TabHost mTabHost;
    private final int mContainerId;
    private final HashMap<String, TabInfo> mTabs = new HashMap<>();
    private final HashMap<String, View> tagViews = new HashMap<>();
    private TabInfo mLastTab = null;
    private ArrayList<OnTabSelectListener> onTabSelectListeners = new ArrayList<>();
    private OnInterceptSwitchListener onTabSwitchListener;

    public TabManager(FragmentActivity activity, @IdRes int tabHostId, @IdRes int containerId) {
        this(activity, (TabHost) activity.findViewById(tabHostId), containerId);
    }

    public TabManager(FragmentActivity activity, TabHost tabHost, int containerId) {
        this.mActivity = activity;
        this.mTabHost = tabHost;
        this.mContainerId = containerId;
        mTabHost.setup();
        mTabHost.getTabWidget().setDividerDrawable(null);
        this.mTabHost.setOnTabChangedListener(this);
    }


    public void addTab(TabHost.TabSpec tabSpec, Class<?> clss, Bundle args) {
        tabSpec.setContent(new DummyTabFactory(this.mActivity));
        String tag = tabSpec.getTag();
        TabInfo info = new TabInfo(tag, clss, args, mTabs.size());
        info.setTagView(tagViews.get(tag));
        info.fragment = this.mActivity.getSupportFragmentManager().findFragmentByTag(tag);
        if (info.fragment != null && !info.fragment.isDetached()) {
            FragmentTransaction ft = this.mActivity.getSupportFragmentManager().beginTransaction();
            ft.hide(info.fragment);
            ft.commitAllowingStateLoss();
        }
        this.mTabs.put(tag, info);
        this.mTabHost.addTab(tabSpec);
    }

    public void addTab(TabHost.TabSpec tabSpec, Class<?> clss) {
        addTab(tabSpec, clss, null);
    }


    public void addTab(@StringRes int strResId, @LayoutRes int layoutID, Class<?> clss, Bundle bundle) {
        String tag = mActivity.getString(strResId);
        View view = View.inflate(mActivity, layoutID, null);
        tagViews.put(tag, view);
        addTab(mTabHost.newTabSpec(tag).setIndicator(view), clss, bundle);
    }

    public void addTab(@StringRes int strResId, @LayoutRes int layoutID, Class<?> clss) {
        addTab(strResId, layoutID, clss, null);
    }


    @Override
    public void onTabChanged(String tabId) {
        if (onTabSwitchListener != null && onTabSwitchListener.onInterceptSwitch(mTabs.get(tabId).index, tabId)) {
            if (mLastTab != null) { // 未初始化
                mTabHost.setCurrentTabByTag(mLastTab.tag); // 拦截以后需要复原原来选择的tab
            }
            return;
        }
        TabInfo newTab = this.mTabs.get(tabId);
        if (this.mLastTab != newTab) {
            FragmentTransaction ft = this.mActivity.getSupportFragmentManager().beginTransaction();
            if (this.mLastTab != null && this.mLastTab.fragment != null) {
                ft.hide(this.mLastTab.fragment);
            }
            if (newTab != null) {
                if (newTab.fragment == null) {
                    newTab.fragment = Fragment.instantiate(this.mActivity, newTab.clss.getName(), newTab.args);
                    ft.add(this.mContainerId, newTab.fragment, newTab.tag);
                } else {
                    ft.show(newTab.fragment);
                }
            }
            if (onTabSelectListeners != null && mLastTab != null) {
                for (OnTabSelectListener onTabChangeListener : onTabSelectListeners) {
                    onTabChangeListener.onUnSelect(mLastTab.getTag(), mLastTab.getIndex(), mLastTab.getTagView());
                }
            }
            this.mLastTab = newTab;
            ft.commitAllowingStateLoss();
            this.mActivity.getSupportFragmentManager().executePendingTransactions();
            if (onTabSelectListeners != null) {
                for (OnTabSelectListener onTabChangeListener : onTabSelectListeners) {
                    onTabChangeListener.onSelect(mLastTab.getTag(), mLastTab.getIndex(), mLastTab.getTagView());
                }
            }
        }
    }


    public void setCurrentTab(int index) {
        mTabHost.setCurrentTab(index);
    }

    public void setCurrentTabByTag(String tag) {
        mTabHost.setCurrentTabByTag(tag);
    }

    public void addOnTabSelectListeners(OnTabSelectListener listener) {
        onTabSelectListeners.add(listener);
    }

    public void setOnInterceptSwitchListener(OnInterceptSwitchListener onTabSwitchListener) {
        this.onTabSwitchListener = onTabSwitchListener;
    }


    static class DummyTabFactory implements TabHost.TabContentFactory {
        private final Context mContext;

        public DummyTabFactory(Context context) {
            this.mContext = context;
        }

        @Override
        public View createTabContent(String tag) {

            View v = new View(this.mContext);
            v.setMinimumWidth(0);
            v.setMinimumHeight(0);
            return v;
        }
    }

    static final class TabInfo {
        private final String tag;
        private final Class<?> clss;
        private final Bundle args;
        private Fragment fragment;
        private int index = 0;
        private View tagView;

        TabInfo(String _tag, Class<?> _class, Bundle _args, int index) {
            this.tag = _tag;
            this.clss = _class;
            this.args = _args;
            this.index = index;
            this.tagView = tagView;
        }

        public int getIndex() {
            return index;
        }

        public String getTag() {
            return tag;
        }

        @Nullable
        public View getTagView() {
            return tagView;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public void setTagView(View tagView) {
            this.tagView = tagView;
        }
    }

    public interface OnInterceptSwitchListener {
        /**
         * intercept switch
         *
         * @param index id 索引
         * @param tabId id switch
         * @return true--不拦截正常选择，false 拦截选择
         */
        boolean onInterceptSwitch(int index, String tabId);


    }


    public interface OnTabSelectListener {

        void onSelect(String tagId, int index, View tagView);

        void onUnSelect(String tagId, int index, View tagView);


    }
}
