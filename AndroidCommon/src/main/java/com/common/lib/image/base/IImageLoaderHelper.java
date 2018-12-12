package com.common.lib.image.base;

import android.content.Context;

import com.common.lib.image.download.DownLoadListener;


/**
 * Created by lwj on 2017/11/14.
 * lwjfork@gmail.com
 */

public interface IImageLoaderHelper {


    //  获取硬盘缓存大小
    long getDiskCacheSize(Context context);

    //  清除内存缓存
    void clearMemoryCache(Context context);

    // 清除硬盘缓存
    void clearDiskCache(Context context);

    // 清除硬盘缓存／内存缓存
    void clearCache(Context context);

    // 获取缓存目录
    String getCacheDir(Context context);

    // 暂停请求
    void pause(Context context);

    //  恢复请求
    void resume(Context context);

    void onTrimMemory(int level);

    void onLowMemory();


    void downLoad(String url, DownLoadListener listener);

    void downLoad(String url, DownLoadListener listener, int width, int height);


    boolean isMemoryCache(String url, int imageType);

    boolean isDiskCache(String url, int imageType);


}
