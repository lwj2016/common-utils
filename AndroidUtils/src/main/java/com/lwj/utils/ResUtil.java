package com.lwj.utils;

import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.AnimRes;
import android.support.annotation.ArrayRes;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.RawRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.lwj.utils.context.GlobalContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;

/**
 * Created by lwj on 16/3/9.
 * lwjfork@gmail.com
 */
public class ResUtil {

    /**
     * 获得全局的resources
     *
     * @return resources
     */
    public static Resources getResources() {
        return GlobalContext.getContext().getResources();
    }


    /**
     * 从strings.xml中读取String
     *
     * @param resId String res id
     *
     * @return string
     */
    public static String getString(@StringRes int resId) {
        return getResources().getString(resId);
    }


    /**
     * 从colors.xml中读取color
     *
     * @param resId color id
     *
     * @return color
     */
    public static int getColor(@ColorRes int resId) {
        return ContextCompat.getColor(GlobalContext.getContext(), resId);
    }

    /**
     * create Drawable by color
     *
     * @param resId color id
     *
     * @return Drawable
     */
    public static Drawable getColorDrawable(@ColorRes int resId) {
        return new ColorDrawable(getColor(resId));
    }


    /**
     * 从colors.xml中读取color
     *
     * @param resId color id
     *
     * @return color
     */
    public static ColorStateList getColorStateList(@ColorRes int resId) {
        return ContextCompat.getColorStateList(GlobalContext.getContext(), resId);
    }


    /**
     * 从arrays.xml中读取array[]
     *
     * @param resId String array id
     *
     * @return String[]
     */
    public static String[] getStringArray(@ArrayRes int resId) {
        return getResources().getStringArray(resId);
    }


    /**
     * 从dimens.xml里读取dimen
     *
     * @param resId dimensid
     *
     * @return dimen
     */
    public static int getDimen(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }


    public static Drawable getDrawable(@DrawableRes int drawableResId) {
        try {
            return ContextCompat.getDrawable(GlobalContext.getContext(), drawableResId);
        } catch (Exception e) {
            return null;
        }
    }


    /**
     * 获取 int 数组
     *
     * @param resId IntegerArray id
     *
     * @return IntegerArray
     */
    public static int[] getIntegerArray(@ArrayRes int resId) {

        return getResources().getIntArray(resId);
    }


    /**
     * 获取  AssetManager
     *
     * @return AssetManager
     */
    public static AssetManager getAssetManager() {
        return getResources().getAssets();
    }


    /**
     * @param res
     * @param args
     *
     * @return
     */
    public static String getFormatString(String res, Object... args) {
        return String.format(res, args);
    }

    /**
     * @param resId
     * @param args
     *
     * @return
     */
    public static String getFormatString(@StringRes int resId, Object... args) {
        return getFormatString(ResUtil.getString(resId), args);
    }

    /**
     * 获取动画
     *
     * @param animationID
     *
     * @return
     */
    public static Animation getAnimation(@AnimRes int animationID) {
        return AnimationUtils.loadAnimation(GlobalContext.getContext(), animationID);
    }


    /**
     * @param filename
     *
     * @return 从 assets 里读文件
     */
    public static String getStrFromAssets(String filename) {
        InputStream is;

        Writer writer = new StringWriter();
        char[] buffer = new char[8 * 1024];
        try {
            is = getResources().getAssets().open(filename);
            Reader reader = new BufferedReader(new InputStreamReader(is));
            int n = 0;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }


    /**
     * @param filename
     *
     * @return 从 assets 里读文件
     */
    public static String getPathFromAssets(String filename) {

        return "file:///android_asset/" + filename;
    }


    public static String readRawStrById(@RawRes int id) {
        return readRawStrById(id, "UTF-8");
    }

    public static String readRawStrById(@RawRes int id, String encoding) {
        Charset charset = Charset.forName(encoding);
        return readRawStrById(id, charset);
    }


    public static String readRawStrById(@RawRes int id, Charset charset) {
        String text = null;
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;

        try {
            inputReader = new InputStreamReader(getResources().openRawResource(id));
            bufReader = new BufferedReader(inputReader);
            String line = null;
            StringBuffer buffer = new StringBuffer();

            while ((line = bufReader.readLine()) != null) {
                buffer.append(line);
            }

            text = new String(buffer.toString().getBytes(), charset);
        } catch (Exception var16) {
            var16.printStackTrace();
        } finally {
            try {
                if (bufReader != null) {
                    bufReader.close();
                }

                if (inputReader != null) {
                    inputReader.close();
                }
            } catch (Exception var15) {
                var15.printStackTrace();
            }

        }
        return text;
    }

}
