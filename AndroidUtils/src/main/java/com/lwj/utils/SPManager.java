package com.lwj.utils;

import android.app.Application;
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
 * <p>
 * /data/date/{packageName}/shared_prefs
 */
public final class SPManager extends GlobalContext {
    // com.google.support----> com_google_support
    private static String PREF_NAME = StrUtil.replaceAll(AppUtil.getPackageName(), "\\.", "_");

    private SharedPreferences mSharedPreferences;

    private SharedPreferences.Editor editor;

    private static Context context;

    private static void init(Context _context) {
        if (context != null) {
            return;
        }
        if (_context instanceof Application) {
            context = _context;
            return;
        }
        context = _context.getApplicationContext();
    }

    private static final LinkedHashMap<String, SPManager> managers = new LinkedHashMap<>(3, 0.75F, true);

    public static SPManager getManager() {
        init(getContext());
        return getManager(PREF_NAME);
    }

    public static SPManager getManager(String preName) {
        init(getContext());
        SPManager manager = managers.get(preName);
        if (manager == null) {
            manager = new SPManager(preName);
            managers.put(preName, manager);
        }
        return manager;
    }

    @SuppressWarnings("all")
    private SPManager(String preName) {
        this.mSharedPreferences = context.getSharedPreferences(preName, Context.MODE_PRIVATE);
        editor = this.mSharedPreferences.edit();
    }


    public SPManager save(String key, Object value) {
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
        } else {
            saveObject(key, value);
        }

        return this;
    }


    public SPManager remove(String key) {
        editor.remove(key);
        return this;
    }


    public SPManager removeAndCommit(String key) {
        editor.remove(key).commit();
        return this;
    }


    public void apply(String key, Object value) {
        if (value instanceof String) {
            applyString(key, (String) value);
        } else if (value instanceof Boolean) {
            applyBoolean(key, (boolean) value);
        } else if (value instanceof Float) {
            applyFloat(key, (float) value);
        } else if (value instanceof Integer) {
            applyInt(key, (int) value);
        } else if (value instanceof Long) {
            applyLong(key, (Long) value);
        } else {
            applyObject(key, value);
        }
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean commit(String key, Object value) {
        if (value instanceof String) {
            return commitString(key, (String) value);
        } else if (value instanceof Boolean) {
            return commitBoolean(key, (boolean) value);
        } else if (value instanceof Float) {
            return commitFloat(key, (float) value);
        } else if (value instanceof Integer) {
            return commitInt(key, (int) value);
        } else if (value instanceof Long) {
            return commitLong(key, (Long) value);
        } else {
            return commitObject(key, value);
        }
    }


    public Object get(String key, Object defValue) {

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

            return getObject(key, defValue);
        }
//        //找不到的话会返回默认的数值
//        return defValue;
    }


    public SPManager saveBoolean(String key, boolean bool) {
        editor.putBoolean(key, bool);
        return this;
    }

    public boolean commitBoolean(String key, boolean bool) {
        return saveBoolean(key, bool).commit();
    }

    public void applyBoolean(String key, boolean bool) {
        saveBoolean(key, bool).apply();
    }

    public boolean getBoolean(String key) {
        return this.mSharedPreferences.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean bool) {
        return this.mSharedPreferences.getBoolean(key, bool);
    }

    public SPManager saveString(String key, String value) {
        editor.putString(key, value);
        return this;
    }

    public boolean commitString(String key, String bool) {
        return saveString(key, bool).commit();
    }

    public void applyString(String key, String bool) {
        saveString(key, bool).apply();
    }


    public String getString(String key) {
        return this.mSharedPreferences.getString(key, "");
    }

    public String getString(String key, String defaultValue) {
        return this.mSharedPreferences.getString(key, defaultValue);
    }

    public SPManager saveInt(String key, int value) {
        editor.putInt(key, value);
        return this;
    }


    public boolean commitInt(String key, int value) {
        return saveInt(key, value).commit();
    }

    public void applyInt(String key, int value) {
        saveInt(key, value).apply();
    }

    public int getInt(String key) {
        return this.mSharedPreferences.getInt(key, 0);
    }

    public int getInt(String key, int defaultValue) {
        return this.mSharedPreferences.getInt(key, defaultValue);
    }

    public SPManager saveLong(String key, long value) {
        editor.putLong(key, value);
        return this;
    }


    public boolean commitLong(String key, long value) {
        return saveLong(key, value).commit();
    }

    public void applyLong(String key, long value) {
        saveLong(key, value).apply();
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

    public SPManager saveFloat(String key, float value) {
        editor.putFloat(key, value);
        return this;
    }


    public void applyFloat(String key, float value) {
        saveFloat(key, value).apply();
    }

    public boolean commitFloat(String key, float value) {
        return saveFloat(key, value).commit();
    }


    public float getFloat(String key) {
        return this.mSharedPreferences.getFloat(key, 0.0F);
    }

    public float getFloat(String key, float defaultValue) {
        return this.mSharedPreferences.getFloat(key, defaultValue);
    }


    public SPManager saveObject(String key, Object value) {
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
        return this;
    }


    public void applyObject(String key, Object value) {
        saveObject(key, value).apply();
    }

    public boolean commitObject(String key, Object value) {
        return saveObject(key, value).commit();
    }

    public Object getObject(String key, Object defValue) {
        Object obj = null;
        try {
            String productString = getString(key, "");
            if (StrUtil.isEmpty(productString)) {
                return defValue;
            }
            byte[] base64Product = Base64.decode(productString, Base64.DEFAULT);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Product);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (obj == null) {
            obj = defValue;
        }
        return obj;
    }

    public Object getObject(String key) {
        return getObject(key, null);
    }


    public SharedPreferences getSharedPreferences() {
        return this.mSharedPreferences;
    }

    public void clear() {
        editor.clear();
    }


    public boolean commit() {
        return editor.commit();
    }

    public void apply() {
        editor.apply();
    }
}