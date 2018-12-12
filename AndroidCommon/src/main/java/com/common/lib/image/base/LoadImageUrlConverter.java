package com.common.lib.image.base;

/**
 * Created by lwj on 2017/9/14.
 * lwjfork@gmail.com
 */

public interface LoadImageUrlConverter {


    int FILE = 1;
    int NET = 1 + FILE;
    int CONTENT = 1 + NET;
    int ASSETS = 1 + CONTENT;
    int DRAWABLE = 1 + ASSETS;


    String convertNetUrl(String url);

    String convertFile(String filePath);

    String convertContent(String contentPath);

    String convertAssets(String assets);

    String convertDrawable(String drawable);

    String convert(int type, String url);


}
