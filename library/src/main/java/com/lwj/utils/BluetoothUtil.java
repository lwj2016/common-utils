package com.lwj.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;

/**
 * Created by lwj on 2018/3/20.
 * lwjfork@gmail.com
 *
 * @see android.Manifest.permission#BLUETOOTH
 * @see android.Manifest.permission#BLUETOOTH_ADMIN
 */

public class BluetoothUtil {

    public static boolean isSupporBluetooth() {
        return BluetoothAdapter.getDefaultAdapter() != null;
    }

    public static BluetoothAdapter getAdapter() {
        return BluetoothAdapter.getDefaultAdapter();
    }

    @SuppressWarnings("MissingPermission")
    public static boolean isOpen() {
        BluetoothAdapter adapter = getAdapter();
        if (adapter == null) {
            return false;
        }
        return adapter.isEnabled();
    }

    @SuppressWarnings("MissingPermission")
    public static boolean open() {
        BluetoothAdapter adapter = getAdapter();
        if (adapter == null) {
            return false;
        }
        if (!adapter.isEnabled()) {
            return adapter.enable();
        }
        return true;
    }

    /**
     * 注册搜索到的广播
     *
     * @param context
     * @param receiver
     * @see BluetoothAdapter#ACTION_DISCOVERY_STARTED
     */
    public static void registerStartDiscoveryReceiver(Context context, BroadcastReceiver receiver) {
        BroadcasUtil.register(context, receiver, BluetoothAdapter.ACTION_DISCOVERY_STARTED);
    }

    /**
     * 注册搜索到的广播
     *
     * @param context
     * @param receiver
     * @see BluetoothDevice#ACTION_FOUND
     */
    public static void registerFoundReceiver(Context context, BroadcastReceiver receiver) {
        BroadcasUtil.register(context, receiver, BluetoothDevice.ACTION_FOUND);
    }


    /**
     * 注册搜索结束的广播
     *
     * @param context
     * @param receiver
     */
    public static void registerFinishReceiver(Context context, BroadcastReceiver receiver) {
        BroadcasUtil.register(context, receiver, BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
    }

    /**
     * 开始搜索
     */
    @SuppressWarnings("MissingPermission")
    public static void startDiscovery() {
        BluetoothAdapter adapter = getAdapter();
        if (adapter != null) {
            adapter.startDiscovery();
        }
    }

    /**
     * 取消搜索
     */
    @SuppressWarnings("MissingPermission")
    public static void cancelDiscovery() {
        BluetoothAdapter adapter = getAdapter();
        if (adapter != null) {
            adapter.cancelDiscovery();
        }
    }

}
