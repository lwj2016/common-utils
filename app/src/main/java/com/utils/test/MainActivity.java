package com.utils.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;

import com.lwj.utils.ActivityUtil;
import com.lwj.utils.AppBackPress;
import com.lwj.utils.SysIntentUtil;
import com.lwj.utils.ToastUtil;
import com.lwj.utils.log.LogUtil;


/**
 * Created by lwj on 2017/10/27.
 * lwjfork@gmail.com
 */

public class MainActivity extends Activity implements AppBackPress.OnBackPressListener {


    AppBackPress backPress = new AppBackPress();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_empty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.startActivity(MainActivity.this, WebActivity.class);
            }
        });

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        return backPress.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.e("onDestroy %s", "onDestroy");
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
