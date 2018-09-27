package com.utils.test;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.lwj.utils.AndroidFileUtil;
import com.lwj.utils.AppBackPress;
import com.lwj.utils.BroadcastUtil;
import com.lwj.utils.ColorUtil;
import com.lwj.utils.ResUtil;
import com.lwj.utils.SPManager;
import com.lwj.utils.SpanBuilder;
import com.lwj.utils.ToastUtil;
import com.lwj.utils.ViewUtil;
import com.lwj.utils.WeakActivityHandler;
import com.lwj.utils.WeakHandler;
import com.lwj.utils.log.LogUtil;


/**
 * Created by lwj on 2017/10/27.
 * lwjfork@gmail.com
 */

public class MainActivity extends Activity implements AppBackPress.OnBackPressListener {


    AppBackPress backPress = new AppBackPress();
    TextView tv_empty;
    BroadcastReceiver receiver;


    private TextView tv_span1;
    private TextView tv_span2;

    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermission();
        setContentView(R.layout.activity_main);
        tv_empty = ViewUtil.findViewById(this, R.id.tv_empty);
        tv_span1 = ViewUtil.findViewById(this, R.id.tv_span1);
        tv_span2 = ViewUtil.findViewById(this, R.id.tv_span2);
        tv_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ActivityUtil.startActivity(MainActivity.this, WebActivity.class);
                Intent intent = new Intent();
                intent.setAction("Test");
                intent.putExtra("test", "sssssssdx");
                BroadcastUtil.sendBroadcast(intent);
            }
        });

        LogUtil.d("test-> %s", AndroidFileUtil.createExternalCacheDirPath());


        backPress.setOnBackPressListener(this);


        ColorStateList stateList = ColorUtil.getColorStateListBuilder()
                .addColorResState(android.R.color.holo_red_dark, android.R.attr.state_pressed)
                .addColorResState(android.R.color.holo_blue_bright)
                .buildColor();

        tv_empty.setTextColor(stateList);


        receiver = BroadcastUtil.registerLocalReceiver(this, new

                BroadcastReceiver() {
                    @Override
                    public void onReceive(Context context, Intent intent) {
                        String test = intent.getStringExtra("test");
                        LogUtil.e("test %s", test);
                    }
                }, "Test");


        SPManager.getManager().commit("test", "tt");

        LogUtil.e("test----> %s", SPManager.getManager().getString("test"));

        SPManager.getManager().removeAndCommit("test");

        LogUtil.e("test----> %s", SPManager.getManager().getString("test"));


        int resID = ResUtil.getArrayResId("test");

        LogUtil.e("resID --> %d", resID);

        int[] array = getResources().getIntArray(resID);

        LogUtil.e("resID --> %d", array[0]);
        LogUtil.e("TAG", "resID --> %d", array[0]);

        weakHandler1 = new TestHandler(this);
//        weakHandler1.sendEmptyMessageDelayed(10, 1000L);
        weakHandler2 = new TestHandler(this);
//        weakHandler2.sendEmptyMessageDelayed(100, 2000L);


        String string = new String("二维空间啊卡打开看看 aadddvva111223aaad1dasd2332adfas");

        String[] arrays = string.split("[^0-9]+");

        for (String s : arrays) {
            System.out.println(s);
        }


        tv_span1.setText(SpanBuilder.newInstance()
                .append("蓝色", new ForegroundColorSpan(ResUtil.getColor(android.R.color.holo_blue_dark)))
                .append("红色", new ForegroundColorSpan(ResUtil.getColor(android.R.color.holo_red_dark)))
                .append("hah")
                .buildSpan());


        tv_span2.setText(SpanBuilder.newInstance("蓝色%，%红色¥，¥绿色% base%")
                .setSpan("蓝色", new ForegroundColorSpan(ResUtil.getColor(android.R.color.holo_blue_dark)))
                .setSpan("红色", new ForegroundColorSpan(ResUtil.getColor(android.R.color.holo_red_dark)))
                .setSpan("绿色", new ForegroundColorSpan(ResUtil.getColor(android.R.color.holo_green_dark)))
                .setRegexSpan("%|¥", new RelativeSizeSpan(0.5f))
                .buildSpan());


    }

    private int index;
    private int index1;

    private WeakHandler<MainActivity> weakHandler1;
    private WeakHandler<MainActivity> weakHandler2;

    public static class TestHandler extends WeakActivityHandler<MainActivity> {

        public TestHandler(MainActivity activity) {
            super(activity);
        }

        @Override
        protected void handleMsg(Message message, MainActivity weakObj) {
            int what = message.what;
            if (what == 10) {
                LogUtil.e("weakHandler index --> %d", weakObj.addAndGetIndex());
                sendEmptyMessageDelayed(what, 1000L);
            } else if (what == 100) {
                LogUtil.e("weakHandler index1--> %d", weakObj.addAndGetIndex1());
                sendEmptyMessageDelayed(what, 2000L);
            }

        }
    }


    public int addAndGetIndex() {

        return ++index;
    }


    public int addAndGetIndex1() {

        return ++index1;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return backPress.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.e("onDestroy %s", "onDestroy");
        WeakHandler.destroyHandler(weakHandler1, weakHandler2);
    }

    @Override
    public void onBackPressedInterval() {
        ToastUtil.show("再按一次退出！");
    }

    @Override
    public void onBackPressedFinish() {
        finish();
    }


    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }
    }

}
