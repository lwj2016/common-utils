package com.common.lib.image.download;

import android.graphics.Bitmap;

/**
 * Created by lwj on 2017/9/20.
 * lwjfork@gmail.com
 */

public interface DownLoadListener {

    void onSuccess(Bitmap bitmap);

    void onFail(Throwable throwable);

    void onStart();
}
