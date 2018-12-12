package com.common.lib.image.base;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.common.lib.image.utils.ReSize;


/**
 * Created by lwj on 2016/12/30.
 * lwjfork@gmail.com
 */

public interface IBaseImageLoader {


    void setConverter(LoadImageUrlConverter urlConverter);

    /**
     * 设置log 等级
     *
     * @param isLog 是否打印日志
     * @see android.util.Log
     */
    void setLog(boolean isLog);

    //----圆形图加载
    void loadImageCircle(Context context, int imageType, ImageView imageView, String image, int defaultImg, int errorImg, ReSize reSize, boolean isAnim, int iterations, int blurRadius);

    //----圆形图加载
    void loadImageCircle(Fragment fragment, int imageType, ImageView imageView, String image, int defaultImg, int errorImg, ReSize reSize, boolean isAnim, int iterations, int blurRadius);

    //----圆形图带边框加载
    void loadImageCircleWithBorder(Context context, int imageType, ImageView imageView, String image, int defaultImg, int errorImg, int borderWidth, int borderColor, ReSize reSize, boolean isAnim, int iterations, int blurRadius);

    //----圆形图带边框加载
    void loadImageCircleWithBorder(Fragment fragment, int imageType, ImageView imageView, String image, int defaultImg, int errorImg, int borderWidth, int borderColor, ReSize reSize, boolean isAnim, int iterations, int blurRadius);

    //----圆角图加载
    void loadRoundImage(Context context, int imageType, ImageView imageView, String image, int defaultImg, int errorImg, float tlradius, float trradius, float brRadius, float blRadius, ReSize reSize, boolean isAnim, int iterations, int blurRadius);

    //----圆角图加载
    void loadRoundImage(Fragment fragment, int imageType, ImageView imageView, String image, int defaultImg, int errorImg, float tlradius, float trradius, float brRadius, float blRadius, ReSize reSize, boolean isAnim, int iterations, int blurRadius);


    //---普通加载
    void loadImage(Context context, int imageType, ImageView imageView, String image, int defaultImg, int errorImg, ReSize reSize, boolean isAnim, int iterations, int blurRadius);

    //---普通加载
    void loadImage(Fragment fragment, int imageType, ImageView imageView, String image, int defaultImg, int errorImg, ReSize reSize, boolean isAnim, int iterations, int blurRadius);

    //  加载 gif 图

    void loadGifImage(Context context, int imageType, ImageView imageView, String image, boolean isAnim, int iterations, int blurRadius);


    void loadGifImage(Fragment fragment, int imageType, ImageView imageView, String image, boolean isAnim, int iterations, int blurRadius);


}
