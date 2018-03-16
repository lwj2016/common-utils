package com.utils.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.lwj.utils.AppBackPressExitUtil;
import com.lwj.utils.ByteUnit;
import com.lwj.utils.NetUtil;
import com.lwj.utils.ToastUtil;
import com.lwj.utils.ViewUtil;
import com.lwj.utils.log.LogUtil;
import com.utils.test.json.TestJsonConverter;

import java.io.Serializable;

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


        double d = 100;

        LogUtil.d("100kb is %sKb", ByteUnit.KB.toKbs(100) + "");
        LogUtil.d("100kb is %sMb", ByteUnit.KB.toMbs(100) + "");
        LogUtil.d("100kb is %sGb", ByteUnit.KB.toGbs(100) + "");
        LogUtil.d("100kb is %sTb", ByteUnit.KB.toTbs(100) + "");


        LogUtil.d("100Mb is %sKb", ByteUnit.MB.toKbs(100) + "");
        LogUtil.d("100Mb is %sMb", ByteUnit.MB.toMbs(100) + "");
        LogUtil.d("100Mb is %sGb", ByteUnit.MB.toGbs(100) + "");
        LogUtil.d("100Mb is %sTb", ByteUnit.MB.toTbs(100) + "");


        LogUtil.d("100Gb is %sKb", ByteUnit.GB.toKbs(100) + "");
        LogUtil.d("100Gb is %sMb", ByteUnit.GB.toMbs(100) + "");
        LogUtil.d("100Gb is %sGb", ByteUnit.GB.toGbs(100) + "");
        LogUtil.d("100Gb is %sTb", ByteUnit.GB.toTbs(100) + "");


        LogUtil.d("100Tb is %sKb", ByteUnit.TB.toKbs(100) + "");
        LogUtil.d("100Tb is %sMb", ByteUnit.TB.toMbs(100) + "");
        LogUtil.d("100Tb is %sGb", ByteUnit.TB.toGbs(100) + "");
        LogUtil.d("100Tb is %sTb", ByteUnit.TB.toTbs(100) + "");

        String string = "#5";

        TestModel testModel = new TestModel();

        LogUtil.d("json %s", TestJsonConverter.getInstance().obj2JsonStr(testModel));

        TestModel result = TestJsonConverter.getInstance().jsonStr2Obj("{\"d\":\"k\"}", TestModel.class);

        LogUtil.d("result %s", result.toString() + "");


    }


    AppBackPressExitUtil exitUtil = new AppBackPressExitUtil(this) {
        @Override
        public void onInterval() {
            View view = ViewUtil.inflate(R.layout.toast);
            ViewUtil.setTvText(view, R.id.tv_msg, "再按一次退出程序");
            ToastUtil.get()
                    .setView(view)
                    .setGravity(Gravity.CENTER)
                    .setDuration(Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return exitUtil.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    public class TestModel implements Serializable {

        public double d = 5d;


        @Override
        public String toString() {
            return "TestModel{" +
                    "d=" + d +
                    '}';
        }
    }
}
