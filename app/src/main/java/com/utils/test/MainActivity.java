package com.utils.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.lwj.utils.AppBackPressExitUtil;
import com.lwj.utils.NetUtil;
import com.lwj.utils.ToastUtil;
import com.lwj.utils.ViewUtil;
import com.lwj.utils.log.LogUtil;

/**
 * Created by lwj on 2017/10/27.
 * lwjfork@gmail.com
 */

public class MainActivity extends Activity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (NetUtil.isNetConnected()) {
            LogUtil.d("isNetConnected---> %s", true);

            if (NetUtil.is2GConnected()) {
                LogUtil.d("is2GConnected---> %s", true);
            }

            if (NetUtil.is3GConnected()) {
                LogUtil.d("is3GConnected---> %s", true);
            }

            if (NetUtil.is4GConnected()) {
                LogUtil.d("is4GConnected---> %s", true);
            }

            if (NetUtil.isWifiConnected()) {
                LogUtil.d("isWifiConnected---> %s", true);
            }
        }


    }


    AppBackPressExitUtil exitUtil = new AppBackPressExitUtil(2000L) {
        @Override
        public void onFinish() {
            finish();
        }

        @Override
        public void onInterval() {

//            View view = ViewUtil.inflate(R.layout.toast);
//            ViewUtil.setTvText(view, R.id.tv_msg, "再按一次退出程序");
//            ToastUtil.get()
//                    .setView(view)
//                    .setGravity(Gravity.CENTER)
//                    .setDuration(Toast.LENGTH_SHORT).show();


            ToastUtil.get().setMsg("再按一次退出程序").setGravity(Gravity.CENTER).show();

        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return exitUtil.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }
}
