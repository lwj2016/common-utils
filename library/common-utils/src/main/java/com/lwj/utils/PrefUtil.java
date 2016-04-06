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
        if(instance == null) {
            instance = new PrefUtil(GlobalContext.getContext());
        }
        return instance;
    }

    private PrefUtil(Context context) {
        this.mSharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public void saveBoolean(String key, boolean bool) {
        this.mSharedPreferences.edit().putBoolean(key, bool).commit();
    }

    public boolean getBoolean(String key) {
        return this.mSharedPreferences.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean bool) {
        return this.mSharedPreferences.getBoolean(key, bool);
    }

    public void saveString(String key, String value) {
        this.mSharedPreferences.edit().putString(key, value).commit();
    }

    public String getString(String key) {
        return this.mSharedPreferences.getString(key, "");
    }

    public String getString(String key, String defaultValue) {
        return this.mSharedPreferences.getString(key, defaultValue);
    }

    public void saveInt(String key, Integer value) {
        this.mSharedPreferences.edit().putInt(key, value.intValue()).commit();
    }

    public int getInt(String key) {
        return this.mSharedPreferences.getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return this.mSharedPreferences.getInt(key, defaultValue);
    }

    public void saveLong(String key, Long value) {
        this.mSharedPreferences.edit().putLong(key, value.longValue()).commit();
    }

    public Long getLong(String key) {
        return Long.valueOf(this.mSharedPreferences.getLong(key, 0L));
    }

    public Long getLong(String key, long defaultValue) {
        long value;
        try {
            value = this.mSharedPreferences.getLong(key, defaultValue);
        } catch (Exception var7) {
            var7.printStackTrace();
            value = 0L;
        }

        return Long.valueOf(value);
    }

    public void saveFloat(String key, float value) {
        this.mSharedPreferences.edit().putFloat(key, value).commit();
    }

    public Float getFloat(String key) {
        return Float.valueOf(this.mSharedPreferences.getFloat(key, 0.0F));
    }

    public Float getFloat(String key, float defaultValue) {
        return Float.valueOf(this.mSharedPreferences.getFloat(key, defaultValue));
    }

    public SharedPreferences getSharedPreferences() {
        return this.mSharedPreferences;
    }
    public  void clear(){
        this.mSharedPreferences.edit().clear().commit();
    }
}