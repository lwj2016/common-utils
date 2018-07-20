package com.utils.test;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.lwj.utils.AppBackPress;
import com.lwj.utils.BroadcastUtil;
import com.lwj.utils.ColorUtil;
import com.lwj.utils.SysIntentUtil;
import com.lwj.utils.ToastUtil;
import com.lwj.utils.ViewUtil;
import com.lwj.utils.log.LogUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by lwj on 2017/10/27.
 * lwjfork@gmail.com
 */

public class MainActivity extends Activity implements AppBackPress.OnBackPressListener {


    AppBackPress backPress = new AppBackPress();
    TextView tv_empty;
    BroadcastReceiver receiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_empty = ViewUtil.findViewById(this, R.id.tv_empty);
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


        backPress.setOnBackPressListener(this);


        ColorStateList stateList = ColorUtil.getColorStateListBuilder()
                .addColorResState(android.R.color.holo_red_dark, android.R.attr.state_pressed)
                .addColorResState(android.R.color.holo_blue_bright)
                .buildColor();

        tv_empty.setTextColor(stateList);


        receiver = BroadcastUtil.register(this, new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String test = intent.getStringExtra("test");
                LogUtil.e("test %s", test);
            }
        }, "Test");


        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(new AppInterceptor1());
        builder.addInterceptor(new AppInterceptor2());
        builder.addNetworkInterceptor(new NetWorkInterceptor1());
        builder.addNetworkInterceptor(new NetWorkInterceptor2());
        OkHttpClient client = builder.build();

        Request request = new Request.Builder().url("https://blog.csdn.net/fightingXia/article/details/70947701").build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                LogUtil.d("----> %s","test");
            }
        });
    }


    public static class AppInterceptor1 implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            LogUtil.d("----> %s","AppInterceptor1 before");
            Request request = chain.request();
            Response response = chain.proceed(request);
            LogUtil.d("----> %s","AppInterceptor1 end");
            return response;
        }
    }


    public static class AppInterceptor2 implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            LogUtil.d("----> %s","AppInterceptor2 before");
            Request request = chain.request();
            Response response = chain.proceed(request);
            LogUtil.d("----> %s","AppInterceptor2 end");
            return response;
        }
    }


    public static class NetWorkInterceptor1 implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            LogUtil.d("----> %s","NetWorkInterceptor1 before");
            Request request = chain.request();
            Response response = chain.proceed(request);
            LogUtil.d("----> %s","NetWorkInterceptor1 end");
            return response;
        }
    }


    public static class NetWorkInterceptor2 implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            LogUtil.d("----> %s","NetWorkInterceptor2 before");
            Request request = chain.request();
            Response response = chain.proceed(request);
            LogUtil.d("----> %s","NetWorkInterceptor2 end");
            return response;
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return backPress.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.e("onDestroy %s", "onDestroy");
        BroadcastUtil.unregister(this, receiver);
    }

    @Override
    public void onBackPressedInterval() {
        ToastUtil.show("再按一次退出！");
    }

    @Override
    public void onBackPressedFinish() {
        SysIntentUtil.goHome(this);
    }


}
