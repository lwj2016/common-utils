/**
 * Filename : GlobalContext.java Author : CX Date : 2013-8-12
 * <p>
 * Copyright(c) 2011-2013 Mobitide Android Team. All Rights Reserved.
 */
package com.lwj.utils.context;

import android.app.Application;
import android.content.Context;

import com.lwj.utils.log.LogUtil;

import java.lang.reflect.Method;

/**
 * Created by lwj on 2018/4/10.
 * lwjfork@gmail.com
 * 全局 Context
 */
public class GlobalContext {

    private static Application sApplication;


    public static void setApplication(Application _application) {
        sApplication = _application;
    }

    /**
     * 获得全局的context
     *
     * @return 全局的context   由 application
     */

    public synchronized static Context getContext() {
        if (sApplication == null) {
            init();
        }
        return sApplication;
    }

    static {
        init();
    }

    @SuppressWarnings("all")
    public static void init() {
        if (sApplication == null) {
            try {

                Class aClass = Class.forName("android.app.ActivityThread");
                Method method = aClass.getMethod("currentApplication");
                Application application = (Application) method.invoke(null, (Object[]) null);
                if (application != null) {
                    sApplication = application;
                    LogUtil.d("Get global Context %s from %s.%s",
                            sApplication.getClass().getSimpleName(),
                            aClass.getName(),
                            method.getName());
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                Class aClass = Class.forName("android.app.AppGlobals");
                Method method = aClass.getMethod("getInitialApplication");
                Application application = (Application) method.invoke(null, (Object[]) null);
                if (application != null) {
                    sApplication = application;
                    LogUtil.d("invoke %s from %s.%s",
                            sApplication.getClass().getSimpleName(),
                            aClass.getName(),
                            method.getName());
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            throw new IllegalStateException("GlobalContext is not initialed, it is recommend to init with application context.");
        }
    }

}
