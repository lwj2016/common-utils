package com.common.lib.image.base;

/**
 * Created by lwj on 2017/9/14.
 * lwjfork@gmail.com
 */

public abstract class BaseLoadImageUrlConverter implements LoadImageUrlConverter {


    @Override
    public String convert(int type, String url) {
        switch (type) {
            case FILE:
                return convertFile(url);
            case NET:
                return convertNetUrl(url);
            case CONTENT:
                return convertContent(url);
            case ASSETS:
                return convertAssets(url);
            case DRAWABLE:
                return convertDrawable(url);
        }
        return url;
    }
}
