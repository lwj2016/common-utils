package com.common.lib.image.base;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.common.lib.image.util.ReSize;
import com.lwj.utils.R;


/**
 * Created by liuwenjie on 2017/11/14.
 * lwjfork@gmail.com
 */

public class ImageLoaderBuilder implements IImageLoader {
    
    
    private int defaultRes = R.drawable.default_load_img;
    
    private int errorRes = R.drawable.error_load_img;
    
    private int borderColor = Color.WHITE;
    
    private int borderWidth = 10;
    
    
    private float topLeftRadius;
    private float topRightRadius;
    private float bottomRightRadius;
    private float bottomLeftRadius;
    
    private boolean isCircle = false;
    
    private boolean isCircleBorder = false;
    
    private boolean isRound = false;
    private boolean isAnim = true;
    
    private boolean isGif;
    
    private int iterations;
    private int blurRadius;
    private boolean isBlur = false;
    
    private Context context;
    private Fragment fragment;
    private ReSize resize = new ReSize(400, 400);
    
    
    private String url;
    
    private int type = LoadImageUrlConverter.NET;
    
    
    private IBaseImageLoader iBaseImageLoader;
    
    public static ImageLoaderBuilder get(IBaseImageLoader iBaseImageLoader) {
        return new ImageLoaderBuilder(iBaseImageLoader);
    }
    
    public ImageLoaderBuilder(IBaseImageLoader iBaseImageLoader) {
        this.iBaseImageLoader = iBaseImageLoader;
    }
    
    
    @Override
    public void load(ImageView imageView) {
        if(isCircle) { // 加载圆形
            if(!isBlur) { // 高斯模糊
                iterations = 0;
                blurRadius = 0;
            }
            if(context != null) {
                iBaseImageLoader.loadImageCircle(context, type, imageView, url, defaultRes, errorRes, resize, isAnim, iterations, blurRadius);
            }
            else if(fragment != null) {
                iBaseImageLoader.loadImageCircle(fragment, type, imageView, url, defaultRes, errorRes, resize, isAnim, iterations, blurRadius);
            }
            return;
        }
        if(isCircleBorder) {  // 加载圆形带border
            
            if(!isBlur) { // 高斯模糊
                iterations = 0;
                blurRadius = 0;
            }
            
            if(context != null) {
                iBaseImageLoader.loadImageCircleWithBorder(context, type, imageView, url, borderWidth, borderColor, defaultRes, errorRes, resize, isAnim, iterations, blurRadius);
            }
            else if(fragment != null) {
                iBaseImageLoader.loadImageCircleWithBorder(fragment, type, imageView, url, borderWidth, borderColor, defaultRes, errorRes, resize, isAnim, iterations, blurRadius);
            }
            return;
        }
        if(isRound) { // 圆角
            if(!isBlur) { // 高斯模糊
                iterations = 0;
                blurRadius = 0;
            }
            if(context != null) {
                iBaseImageLoader.loadRoundImage(context, type, imageView, url, defaultRes, errorRes, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, resize, isAnim, iterations, blurRadius);
            }
            else if(fragment != null) {
                iBaseImageLoader.loadRoundImage(fragment, type, imageView, url, defaultRes, errorRes, topLeftRadius, topRightRadius, bottomRightRadius, bottomLeftRadius, resize, isAnim, iterations, blurRadius);
            }
            
            return;
        }
        if(isGif) {
            if(!isBlur) { // 高斯模糊
                iterations = 0;
                blurRadius = 0;
            }
            if(context != null) {
                iBaseImageLoader.loadGifImage(context, type, imageView, url, defaultRes, errorRes);
            }
            else if(fragment != null) {
                iBaseImageLoader.loadGifImage(fragment, type, imageView, url, defaultRes, errorRes);
            }
            return;
        }
        // 普通图加载
        
        if(!isBlur) { // 高斯模糊
            iterations = 0;
            blurRadius = 0;
        }
        if(context != null) {
            iBaseImageLoader.loadImage(context, type, imageView, url, defaultRes, errorRes, resize, isAnim, iterations, blurRadius);
        }
        else if(fragment != null) {
            iBaseImageLoader.loadImage(fragment, type, imageView, url, defaultRes, errorRes, resize, isAnim, iterations, blurRadius);
        }

    }
    
    
    @Override
    public IImageLoader init(Context context) {
        this.context = context;
        return this;
    }
    
    @Override
    public IImageLoader init(Fragment fragment) {
        this.fragment = fragment;
        return this;
    }
    
    
    @Override
    public IImageLoader withDefaultRes(int defaultRes) {
        this.defaultRes = defaultRes;
        return this;
    }
    
    
    @Override
    public IImageLoader withErrorRes(int errorRes) {
        this.errorRes = errorRes;
        return this;
    }
    
    
    @Override
    public IImageLoader withCircle() {
        this.isCircleBorder = false;
        this.isCircle = true;
        this.isRound = false;
        return this;
    }
    
    @Override
    public IImageLoader withCircleBorder(int width, int colors) {
        this.isCircleBorder = true;
        this.borderWidth = width;
        this.borderColor = colors;
        return this;
    }
    
    @Override
    public IImageLoader withResize(ReSize resize) {
        this.resize = resize;
        return this;
    }
    
    
    @Override
    public IImageLoader withRound(float radius) {
        return withRound(radius, radius, radius, radius);
    }
    
    @Override
    public IImageLoader withRound(float topLeftRadius, float topRightRadius, float bottomRightRadius, float bottomLeftRadius) {
        this.isCircleBorder = false;
        this.isCircle = false;
        this.isRound = true;
        this.topLeftRadius = topLeftRadius;
        this.topRightRadius = topRightRadius;
        this.bottomRightRadius = bottomRightRadius;
        this.bottomLeftRadius = bottomLeftRadius;
        return this;
    }
    
    @Override
    public IImageLoader isAnim(boolean isAnim) {
        this.isAnim = isAnim;
        return this;
    }
    
    @Override
    public IImageLoader withUrl(String url, int type) {
        this.url = url;
        this.type = type;
        return this;
    }
    
    @Override
    public IImageLoader withUrl(String url) {
        return withUrl(url, type);
    }
    
    
    @Override
    public IImageLoader isGif(boolean isGif) {
        this.isGif = isGif;
        return this;
    }
    
    @Override
    public IImageLoader withBlur(int iterations, int blurRadius) {
        this.isBlur = true;
        this.iterations = iterations;
        this.blurRadius = blurRadius;
        return this;
    }
}
