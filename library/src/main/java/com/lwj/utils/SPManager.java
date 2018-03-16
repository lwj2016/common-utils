package com.lwj.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.lwj.utils.context.GlobalContext;

/**
 * Created by lwj on 16/4/4.
 * Des:
 */
public class SPManager {
    public static String PREF_NAME = "sp_manager";
    private static SPManager instance;
    private SharedPreferences mSharedPreferences;

    public static SPManager getManager() {
        return getManager(GlobalContext.getContext());
    }

    public static SPManager getManager(Context context) {
        return getManager(context, PREF_NAME);
    }

    public static SPManager getManager(Context context, String preName) {
        if (instance == null || preName.equals(PREF_NAME)) {
            instance = new SPManager(context, preName);
        }
        return instance;
    }

    private SPManager(Context context, String preName) {
        this.mSharedPreferences = context.getSharedPreferences(preName, Context.MODE_PRIVATE);
        PREF_NAME = preName;
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
        this.mSharedPreferences.edit().putInt(key, value).apply();
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

    public long getLong(String key) {
        return this.mSharedPreferences.getLong(key, 0L);
    }

    public long getLong(String key, long defaultValue) {
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

    public float getFloat(String key) {
        return this.mSharedPreferences.getFloat(key, 0.0F);
    }

    public float getFloat(String key, float defaultValue) {
        return this.mSharedPreferences.getFloat(key, defaultValue);
    }


    public SharedPreferences getSharedPreferences() {
        return this.mSharedPreferences;
    }

    public void clear() {
        this.mSharedPreferences.edit().clear().apply();
    }
}