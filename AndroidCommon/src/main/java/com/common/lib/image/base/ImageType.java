package com.common.lib.image.base;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by lwj on 2018/11/6.
 * lwjfork@gmail.com
 */
@Retention(SOURCE)
@IntDef({LoadImageUrlConverter.FILE, LoadImageUrlConverter.NET, LoadImageUrlConverter.CONTENT, LoadImageUrlConverter.DRAWABLE, LoadImageUrlConverter.ASSETS})
public @interface ImageType {
}
