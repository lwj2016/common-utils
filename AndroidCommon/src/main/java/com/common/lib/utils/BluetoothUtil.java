package com.common.lib.utils;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by lwj on 2018/3/20.
 * lwjfork@gmail.com
 *
 * @see android.Manifest.permission#BLUETOOTH
 * @see android.Manifest.permission#BLUETOOTH_ADMIN
 */

public class BluetoothUtil {

    public static boolean isSupportBluetooth() {
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
        BroadcastUtil.registerGlobalReceiver(context, receiver, BluetoothAdapter.ACTION_DISCOVERY_STARTED);
    }

    /**
     * 注册搜索到的广播
     *
     * @param context
     * @param receiver
     * @see BluetoothDevice#ACTION_FOUND
     */
    public static void registerFoundReceiver(Context context, BroadcastReceiver receiver) {
        BroadcastUtil.registerGlobalReceiver(context, receiver, BluetoothDevice.ACTION_FOUND);
    }


    /**
     * 注册搜索结束的广播
     *
     * @param context
     * @param receiver
     */
    public static void registerFinishReceiver(Context context, BroadcastReceiver receiver) {
        BroadcastUtil.registerGlobalReceiver(context, receiver, BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
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

    /**
     * 与设备配对 参考源码：platform/packages/apps/Settings.git
     * /Settings/src/com/android/settings/bluetooth/CachedBluetoothDevice.java
     */
    static public boolean createBond(Class btClass, BluetoothDevice btDevice)
            throws Exception {
        Method createBondMethod = btClass.getMethod("createBond");
        Boolean returnValue = (Boolean) createBondMethod.invoke(btDevice);
        return returnValue.booleanValue();
    }

    /**
     * 与设备解除配对 参考源码：platform/packages/apps/Settings.git
     * /Settings/src/com/android/settings/bluetooth/CachedBluetoothDevice.java
     */
    static public boolean removeBond(Class<?> btClass, BluetoothDevice btDevice)
            throws Exception {
        Method removeBondMethod = btClass.getMethod("removeBond");
        Boolean returnValue = (Boolean) removeBondMethod.invoke(btDevice);
        return returnValue.booleanValue();
    }

    /**
     * ping 蓝牙设备
     *
     * @param btClass
     * @param btDevice
     * @param str
     * @return
     * @throws Exception
     */
    public static boolean setPin(Class<? extends BluetoothDevice> btClass, BluetoothDevice btDevice,
                                 String str) throws Exception {

        try {
            Method removeBondMethod = btClass.getDeclaredMethod("setPin", new Class[]{byte[].class});
            Boolean returnValue = (Boolean) removeBondMethod.invoke(btDevice,
                    new Object[]
                            {str.getBytes()});
            Log.e("returnValue", "" + returnValue);
        } catch (SecurityException e) {
            // throw new RuntimeException(e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            // throw new RuntimeException(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;

    }
}
