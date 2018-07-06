package com.utils.test;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.lwj.utils.ActivityUtil;
import com.lwj.utils.AppBackPress;
import com.lwj.utils.ColorUtil;
import com.lwj.utils.DrawableUtil;
import com.lwj.utils.ResUtil;
import com.lwj.utils.ShapeUtil;
import com.lwj.utils.SysIntentUtil;
import com.lwj.utils.ToastUtil;
import com.lwj.utils.ViewUtil;
import com.lwj.utils.log.LogUtil;


/**
 * Created by lwj on 2017/10/27.
 * lwjfork@gmail.com
 */

public class MainActivity extends Activity implements AppBackPress.OnBackPressListener {


    AppBackPress backPress = new AppBackPress();
    TextView tv_empty;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_empty = ViewUtil.findViewById(this, R.id.tv_empty);
        tv_empty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtil.startActivity(MainActivity.this, WebActivity.class);
            }
        });


        backPress.setOnBackPressListener(this);


        ColorStateList stateList = ColorUtil.getColorStateListBuilder()
                .addColorResState(android.R.color.holo_red_dark, android.R.attr.state_pressed)
                .addColorResState(android.R.color.holo_blue_bright)
                .getColorStateList();

        tv_empty.setTextColor(stateList);
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
