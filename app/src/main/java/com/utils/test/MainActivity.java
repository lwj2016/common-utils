package com.utils.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;

import com.lwj.utils.AppBackPressExitUtil;
import com.lwj.utils.ToastUtil;

/**
 * Created by lwj on 2017/10/27.
 * lwjfork@gmail.com
 */

public class MainActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    AppBackPressExitUtil exitUtil = new AppBackPressExitUtil(2000L) {
        @Override
        public void onFinish() {
            finish();
        }

        @Override
        public void onInterval() {
            ToastUtil.showToast("再按一次退出程序");
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return exitUtil.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }
}
