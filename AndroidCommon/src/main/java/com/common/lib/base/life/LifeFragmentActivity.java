package com.common.lib.base.life;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import java.util.ArrayList;

/**
 * Created by lwj on 2017/1/16.
 * lwjfork@gmail.com
 */

public abstract class LifeFragmentActivity extends FragmentActivity {

    public static ArrayList<IActivityLife> mSubscribers = new ArrayList<>();

    public static void addActivityLife(IActivityLife life) {
        mSubscribers.add(life);
    }

    public static void removeActivityLife(IActivityLife life) {
        mSubscribers.remove(life);
    }

    public static void clearLife() {
        mSubscribers.clear();
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreate(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        onRestart(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        onResume(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        onStart(this);
    }


    @Override
    protected void onPause() {
        super.onPause();
        onPause(this);
    }


    @Override
    protected void onStop() {
        super.onStop();
        onStop(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        onDestroy(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        onNewIntent(this);
    }


    private void onCreate(Activity activity) {
        for (IActivityLife iActivityLife : mSubscribers) {
            iActivityLife.onCreate(activity);
        }
    }

    private void onResume(Activity activity) {
        for (IActivityLife iActivityLife : mSubscribers) {
            iActivityLife.onResume(activity);
        }
    }

    private void onStart(Activity activity) {
        for (IActivityLife iActivityLife : mSubscribers) {
            iActivityLife.onStart(activity);
        }
    }

    private void onRestart(Activity activity) {
        for (IActivityLife iActivityLife : mSubscribers) {
            iActivityLife.onRestart(activity);
        }
    }

    private void onPause(Activity activity) {
        for (IActivityLife iActivityLife : mSubscribers) {
            iActivityLife.onPause(activity);
        }
    }

    private void onStop(Activity activity) {
        for (IActivityLife iActivityLife : mSubscribers) {
            iActivityLife.onStop(activity);
        }
    }

    private void onDestroy(Activity activity) {
        for (IActivityLife iActivityLife : mSubscribers) {
            iActivityLife.onDestroy(activity);
        }
    }

    private void onNewIntent(Activity activity) {
        for (IActivityLife iActivityLife : mSubscribers) {
            iActivityLife.onNewIntent(activity);
        }
    }
}