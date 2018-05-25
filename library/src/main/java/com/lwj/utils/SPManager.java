package com.lwj.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.lwj.utils.context.GlobalContext;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedHashMap;

/**
 * Created by lwj on 16/4/4.
 * Des:
 */
public class SPManager {
    private static String PREF_NAME = "sp_manager";

    private SharedPreferences mSharedPreferences;


    private static final LinkedHashMap<String, SPManager> managers = new LinkedHashMap<>(3, 0.75F, true);

    public static SPManager getManager() {
        return getManager(GlobalContext.getContext());
    }

    public static SPManager getManager(Context context) {
        return getManager(context, PREF_NAME);
    }

    public static SPManager getManager(Context context, String preName) {
        SPManager manager = managers.get(preName);
        if (manager == null) {
            manager = new SPManager(context, preName);
            managers.put(preName, manager);
        }
        return manager;
    }

    private SPManager(Context context, String preName) {
        this.mSharedPreferences = context.getSharedPreferences(preName, Context.MODE_PRIVATE);
        PREF_NAME = preName;
    }


    public void save(String key, Object value) {
        if (value instanceof String) {
            saveString(key, (String) value);
        } else if (value instanceof Boolean) {
            saveBoolean(key, (boolean) value);
        } else if (value instanceof Float) {
            saveFloat(key, (float) value);
        } else if (value instanceof Integer) {
            saveInt(key, (int) value);
        } else if (value instanceof Long) {
            saveLong(key, (Long) value);
        }else {
            saveObject(key, value);
        }
    }

    public Object get(String key,Object defValue){

        if (defValue instanceof String) {
            return getString(key, (String) defValue);
        } else if (defValue instanceof Boolean) {
            return getBoolean(key, (boolean) defValue);
        } else if (defValue instanceof Float) {
            return getFloat(key, (float) defValue);
        } else if (defValue instanceof Integer) {
            return getInt(key, (int) defValue);
        } else if (defValue instanceof Long) {
            return getLong(key, (long) defValue);
        } else {
            Object obj = null;
            try {
                String productString = getString(key, "");
                byte[] base64Product = Base64.decode(productString, Base64.DEFAULT);
                ByteArrayInputStream bais = new ByteArrayInputStream(base64Product);
                ObjectInputStream ois = new ObjectInputStream(bais);
                obj = ois.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return obj;
        }
//        //找不到的话会返回默认的数值
//        return defValue;
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


    public void saveObject(String key, Object value) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(value);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String base64Product = Base64.encodeToString(baos.toByteArray(), Base64.DEFAULT);
        saveString(key, base64Product);

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