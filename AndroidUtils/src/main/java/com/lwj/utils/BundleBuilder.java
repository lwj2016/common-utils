package com.lwj.utils;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created:2018/5/21
 * User：lwjfork
 * Email:lwjfork@gmail.com
 * Des:
 * ====================
 */

public class BundleBuilder {

    private Bundle resultBundle = new Bundle();

    public static BundleBuilder getBuilder() {

        return new BundleBuilder();
    }

    public Bundle builder() {

        return resultBundle;
    }

    public BundleBuilder putAll(Bundle bundle) {
        resultBundle.putAll(bundle);
        return this;
    }

    @TargetApi(21)
    public BundleBuilder putAll(PersistableBundle bundle) {
        resultBundle.putAll(bundle);
        return this;
    }

    public BundleBuilder putBundle(String key, Bundle value) {
        resultBundle.putBundle(key, value);
        return this;
    }

    @TargetApi(18)
    public BundleBuilder putBinder(String key, IBinder value) {
        resultBundle.putBinder(key, value);
        return this;
    }

    public BundleBuilder putByte(String key, byte value) {
        resultBundle.putByte(key, value);
        return this;
    }

    public BundleBuilder putByteArray(String key, byte[] value) {
        resultBundle.putByteArray(key, value);
        return this;
    }

    public BundleBuilder putBoolean(String key, boolean value) {
        resultBundle.putBoolean(key, value);
        return this;
    }

    public BundleBuilder putBooleanArray(String key, boolean[] value) {
        resultBundle.putBooleanArray(key, value);
        return this;
    }

    public BundleBuilder putChar(String key, char value) {
        resultBundle.putChar(key, value);
        return this;
    }

    public BundleBuilder putCharArray(String key, char[] value) {
        resultBundle.putCharArray(key, value);
        return this;
    }

    public BundleBuilder putCharSequence(String key, CharSequence value) {
        resultBundle.putCharSequence(key, value);
        return this;
    }

    public BundleBuilder putCharSequenceArray(String key, CharSequence[] value) {
        resultBundle.putCharSequenceArray(key, value);
        return this;
    }

    public BundleBuilder putCharSequenceArrayList(String key, ArrayList<CharSequence> value) {
        resultBundle.putCharSequenceArrayList(key, value);
        return this;
    }


    public BundleBuilder putChar(String key, double value) {
        resultBundle.putDouble(key, value);
        return this;
    }

    public BundleBuilder putCharArray(String key, double[] value) {
        resultBundle.putDoubleArray(key, value);
        return this;
    }

    public BundleBuilder putFloatArray(String key, float[] value) {
        resultBundle.putFloatArray(key, value);
        return this;
    }

    public BundleBuilder putFloat(String key, float value) {
        resultBundle.putFloat(key, value);
        return this;
    }


    public BundleBuilder putInt(String key, int value) {
        resultBundle.putInt(key, value);
        return this;
    }

    public BundleBuilder putIntArray(String key, int[] value) {
        resultBundle.putIntArray(key, value);
        return this;
    }

    public BundleBuilder putIntegerArrayList(String key, ArrayList<Integer> value) {
        resultBundle.putIntegerArrayList(key, value);
        return this;
    }

    public BundleBuilder putLong(String key, long value) {
        resultBundle.putLong(key, value);
        return this;
    }

    public BundleBuilder putLongArray(String key, long[] value) {
        resultBundle.putLongArray(key, value);
        return this;
    }


    public BundleBuilder putParcelable(String key, Parcelable value) {
        resultBundle.putParcelable(key, value);

        return this;
    }

    public BundleBuilder putParcelableArray(String key, Parcelable[] value) {
        resultBundle.putParcelableArray(key, value);
        return this;
    }

    public BundleBuilder putParcelableArrayList(String key, ArrayList<Parcelable> value) {
        resultBundle.putParcelableArrayList(key, value);
        return this;
    }

    public BundleBuilder putSerializable(String key, Serializable value) {
        resultBundle.putSerializable(key, value);
        return this;
    }

    public BundleBuilder putShort(String key, short value) {
        resultBundle.putShort(key, value);

        return this;
    }

    public BundleBuilder putShortArray(String key, short[] value) {
        resultBundle.putShortArray(key, value);
        return this;
    }

    @TargetApi(21)
    public BundleBuilder putSize(String key, Size value) {
        resultBundle.putSize(key, value);
        return this;
    }

    @TargetApi(21)
    public BundleBuilder putSizeF(String key, SizeF value) {
        resultBundle.putSizeF(key, value);
        return this;
    }

    public BundleBuilder putSparseParcelableArray(String key, SparseArray<? extends Parcelable> value) {
        resultBundle.putSparseParcelableArray(key, value);

        return this;
    }

    public BundleBuilder putString(String key, String value) {
        resultBundle.putString(key, value);

        return this;
    }

    public BundleBuilder putStringArray(String key, String[] value) {
        resultBundle.putStringArray(key, value);
        return this;
    }

    public BundleBuilder putStringArrayList(String key, ArrayList<String> value) {
        resultBundle.putStringArrayList(key, value);
        return this;
    }


}
