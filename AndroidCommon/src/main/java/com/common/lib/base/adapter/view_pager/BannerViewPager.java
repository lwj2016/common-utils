package com.common.lib.base.adapter.view_pager;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.util.Timer;


/**
 * Created:2018/6/20
 * User：liuwenjie
 * Email:liuwnejie180423@credithc.com
 * Des:
 * ====================
 */

public class BannerViewPager extends ViewPager {
    
    private Timer timer;
    private TimerTask timerTask;
    private Handler handler;
    
    private long delay = 2 * 1000L;
    private long period = 2 * 1000L;
    private int MSG_CODE = 1001;
    
    public BannerViewPager(Context context) {
        super(context);
        init();
    }
    
    public BannerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    
    private void init() {
        timer = new Timer();
        
        handler = new LoopHandler();
    }
    
    public void setDelay(long delay) {
        this.delay = delay;
    }
    
    public void setPeriod(long period) {
        this.period = period;
    }
    
    /**
     * 开始循环
     */
    public void startLoop() {
        PagerAdapter pagerAdapter = getAdapter();
        if(pagerAdapter.getCount() <= 1) {
            return;
        }
        timerTask = new TimerTask();
        timer.schedule(timerTask, delay, period);
    }
    
    /**
     * 结束循环
     */
    public void stopLoop() {
        if(timerTask == null) {
            return;
        }
        timerTask.cancel();
        timer.purge();
        handler.removeCallbacksAndMessages(null);
    }
    
    public class TimerTask extends java.util.TimerTask {
        
        @Override
        public void run() {
            handler.sendEmptyMessage(MSG_CODE);
        }
    }
    
    public class LoopHandler extends Handler {
        
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            PagerAdapter pagerAdapter = getAdapter();
            if(pagerAdapter != null) {
                int index = getCurrentItem();
                if(index + 1 < pagerAdapter.getCount()) {
                    setCurrentItem(index + 1);
                }
                else {
                    setCurrentItem(0);
                }
            }
        }
    }
    
    @Override
    public void setAdapter(PagerAdapter adapter) {
        WrappBanerPagerAdapter realAdapter = new WrappBanerPagerAdapter(adapter);
        super.setAdapter(realAdapter);
        if(realAdapter.getCount() <= 1) {
            return;
        }
        setCurrentItem(Integer.MAX_VALUE / 2);
    }
    
    public class WrappBanerPagerAdapter extends PagerAdapter {
        
        PagerAdapter pagerAdapter;
        
        
        public WrappBanerPagerAdapter(PagerAdapter pagerAdapter) {
            this.pagerAdapter = pagerAdapter;
        }
        
        @Override
        public int getCount() {
            int realCount = getRealCount();
            if(realCount <= 1) {
                return realCount;
            }
            return Integer.MAX_VALUE;
        }
        
        public int getRealCount() {
            return pagerAdapter.getCount();
        }
        
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return pagerAdapter.isViewFromObject(view, object);
        }
        
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            return pagerAdapter.instantiateItem(container, getRealIndex(position));
        }
        
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            pagerAdapter.destroyItem(container, getRealIndex(position), object);
        }
        
        public int getRealIndex(int position) {
            return position % getRealCount();
        }
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_CANCEL || ev.getAction() == MotionEvent.ACTION_UP) { // 触摸取消
            startLoop();
        }
        else {
            stopLoop();
        }
        return super.onTouchEvent(ev);
    }
}
