package com.lwj.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.lwj.utils.context.GlobalContext;

/**
 * Created by lwj on 16/4/4.
 * Des:
 */
public class PrefUtil {
    public static final String PREF_NAME = "PrefUtil";
    private static PrefUtil instance;
    private SharedPreferences mSharedPreferences;

    public static PrefUtil getInstance() {
        return getInstance(GlobalContext.getContext());
    }

    public static PrefUtil getInstance(Context context) {
        if (instance == null) {
            instance = new PrefUtil(context);
        }
        return instance;
    }

    private PrefUtil(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveBoolean(String key, boolean bool) {
        this.mSharedPreferences.edit().putBoolean(key, bool).apply();
    }

    public boolean getBoolean(String key) {
        return this.mSharedPreferences.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean bool) {
        return this.mSharedPreferences.getBoolean(key, bool);
    }

    public void saveString(String key, String value) {
        this.mSharedPreferences.edit().putString(key, value).apply();
    }

    public String getString(String key) {
        return this.mSharedPreferences.getString(key, "");
    }

    public String getString(String key, String defaultValue) {
        return this.mSharedPreferences.getString(key, defaultValue);
    }

    public void saveInt(String key, int value) {
        this.mSharedPreferences.edit().putInt(key,value).apply();
    }

    public int getInt(String key) {
        return this.mSharedPreferences.getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return this.mSharedPreferences.getInt(key, defaultValue);
    }

    public void saveLong(String key, long value) {
        this.mSharedPreferences.edit().putLong(key, value).apply();
    }

    public Long getLong(String key) {
        return this.mSharedPreferences.getLong(key, 0L);
    }

    public Long getLong(String key, long defaultValue) {
        long value;
        try {
            value = this.mSharedPreferences.getLong(key, defaultValue);
        } catch (Exception var7) {
            var7.printStackTrace();
            value = 0L;
        }

        return value;
    }

    public void saveFloat(String key, float value) {
        this.mSharedPreferences.edit().putFloat(key, value).apply();
    }

    public Float getFloat(String key) {
        return this.mSharedPreferences.getFloat(key, 0.0F);
    }

    public Float getFloat(String key, float defaultValue) {
        return this.mSharedPreferences.getFloat(key, defaultValue);
    }

    public SharedPreferences getSharedPreferences() {
        return this.mSharedPreferences;
    }

    public void clear() {
        this.mSharedPreferences.edit().clear().apply();
    }
}