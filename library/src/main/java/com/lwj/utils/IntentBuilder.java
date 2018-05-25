package com.lwj.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.ShareCompat;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created:2018/5/21
 * User：liuwenjie
 * Email:lwjfork@gmail.com
 * Des:
 * ====================
 */

public class IntentBuilder {

    private Intent resultIntent = new Intent();

    public static IntentBuilder getBuilder() {

        return new IntentBuilder();
    }

    public Intent builder() {
        return resultIntent;
    }

    public IntentBuilder setClass(Context packageContext, Class<?> cls){
        resultIntent.setClass(packageContext,cls);
        return this;
    }

    public IntentBuilder setClass(ComponentName component){
        resultIntent.setComponent(component);
        return this;
    }


    public IntentBuilder putBundle(String key, Bundle value) {
        resultIntent.putExtra(key, value);
        return this;
    }


    public IntentBuilder putByte(String key, byte value) {
        resultIntent.putExtra(key, value);
        return this;
    }

    public IntentBuilder putByteArray(String key, byte[] value) {
        resultIntent.putExtra(key, value);
        return this;
    }

    public IntentBuilder putBoolean(String key, boolean value) {
        resultIntent.putExtra(key, value);
        return this;
    }

    public IntentBuilder putBooleanArray(String key, boolean[] value) {
        resultIntent.putExtra(key, value);
        return this;
    }

    public IntentBuilder putChar(String key, char value) {
        resultIntent.putExtra(key, value);
        return this;
    }

    public IntentBuilder putCharArray(String key, char[] value) {
        resultIntent.putExtra(key, value);
        return this;
    }

    public IntentBuilder putCharSequence(String key, CharSequence value) {
        resultIntent.putExtra(key, value);
        return this;
    }

    public IntentBuilder putCharSequenceArray(String key, CharSequence[] value) {
        resultIntent.putExtra(key, value);
        return this;
    }

    public IntentBuilder putCharSequenceArrayList(String key, ArrayList<CharSequence> value) {
        resultIntent.putExtra(key, value);
        return this;
    }


    public IntentBuilder putChar(String key, double value) {
        resultIntent.putExtra(key, value);
        return this;
    }

    public IntentBuilder putCharArray(String key, double[] value) {
        resultIntent.putExtra(key, value);
        return this;
    }

    public IntentBuilder putFloatArray(String key, float[] value) {
        resultIntent.putExtra(key, value);
        return this;
    }

    public IntentBuilder putFloat(String key, float value) {
        resultIntent.putExtra(key, value);
        return this;
    }


    public IntentBuilder putInt(String key, int value) {
        resultIntent.putExtra(key, value);
        return this;
    }

    public IntentBuilder putIntArray(String key, int[] value) {
        resultIntent.putExtra(key, value);
        return this;
    }

    public IntentBuilder putIntegerArrayList(String key, ArrayList<Integer> value) {
        resultIntent.putExtra(key, value);
        return this;
    }

    public IntentBuilder putLong(String key, long value) {
        resultIntent.putExtra(key, value);
        return this;
    }

    public IntentBuilder putLongArray(String key, long[] value) {
        resultIntent.putExtra(key, value);
        return this;
    }


    public IntentBuilder putParcelable(String key, Parcelable value) {
        resultIntent.putExtra(key, value);

        return this;
    }

    public IntentBuilder putParcelableArray(String key, Parcelable[] value) {
        resultIntent.putExtra(key, value);
        return this;
    }

    public IntentBuilder putParcelableArrayList(String key, ArrayList<Parcelable> value) {
        resultIntent.putExtra(key, value);
        return this;
    }

    public IntentBuilder putSerializable(String key, Serializable value) {
        resultIntent.putExtra(key, value);
        return this;
    }

    public IntentBuilder putShort(String key, short value) {
        resultIntent.putExtra(key, value);

        return this;
    }

    public IntentBuilder putShortArray(String key, short[] value) {
        resultIntent.putExtra(key, value);
        return this;
    }


    public IntentBuilder putString(String key, String value) {
        resultIntent.putExtra(key, value);

        return this;
    }

    public IntentBuilder putStringArray(String key, String[] value) {
        resultIntent.putExtra(key, value);
        return this;
    }

    public IntentBuilder putStringArrayList(String key, ArrayList<String> value) {
        resultIntent.putExtra(key, value);
        return this;
    }


}
