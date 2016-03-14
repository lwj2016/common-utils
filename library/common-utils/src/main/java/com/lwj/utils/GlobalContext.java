/**
 * Filename : GlobalContext.java Author : CX Date : 2013-8-12
 * <p/>
 * Copyright(c) 2011-2013 Mobitide Android Team. All Rights Reserved.
 */
package com.lwj.utils;

import android.app.Application;
import android.content.Context;

/**
 * GlobalContext.getContext() 获取全局实例
 *
 * @author CX
 */
public class GlobalContext {

    private static Application sApplication;

    /**
     * 获得全局的context
     *
     * @return
     */
    public static Context getContext() {
        if (sApplication == null) {
            throw new NullPointerException("GlobalContext == null. Please init first");
        }
        //这么搞是为了方便切换context
        return sApplication;
    }

    public static void setApplication(Application _application) {
        sApplication = _application;
    }
}
