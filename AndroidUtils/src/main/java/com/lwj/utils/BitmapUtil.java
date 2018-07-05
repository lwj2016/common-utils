package com.lwj.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.support.annotation.ColorInt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Struct;


/**
 * Created by lwj on 16/3/9.
 * lwjfork@gmail.com
 */
public class BitmapUtil {
    public static void saveBitmap2jpg(File saveDir, String jpgName,
                                      Bitmap bitmap) {
        saveBitmap2jpg(saveDir, jpgName, bitmap, 0, 0);
    }

    public static void saveBitmap2png(File saveDir, String pngName,
                                      Bitmap bitmap) {
        saveBitmap2png(saveDir, pngName, bitmap, 0, 0);
    }

    public static void saveBitmap2jpg(File saveDir, String jpgName,
                                      Bitmap bitmap, int width, int height) {
        saveBitmap2File(saveDir, jpgName, bitmap, Bitmap.CompressFormat.JPEG,
                width, height);
    }

    public static void saveBitmap2png(File saveDir, String pngName,
                                      Bitmap bitmap, int width, int height) {
        saveBitmap2File(saveDir, pngName, bitmap, Bitmap.CompressFormat.PNG,
                width, height);
    }

    private static void saveBitmap2File(File saveDir, String jpgName,
                                        Bitmap bitmap, Bitmap.CompressFormat format, int width, int height) {


        File file;

        try {
            file = FileUtil.createFile(saveDir, jpgName);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        if (file != null) {
            FileOutputStream fOut = null;
            try {
                fOut = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return;
            }

            if (width * height != 0) {
                Bitmap newBitmap = scaleBitmap(bitmap, width, height); // 根据所要的图片做相应修改
                newBitmap.compress(format, 100, fOut);
            } else {
                bitmap.compress(format, 100, fOut);
            }
            try {
                fOut.flush();
                fOut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 根据指定的宽、高，获取成比例放大或缩小的bitMap.
     *
     * @param bitmap
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap scaleBitmap(Bitmap bitmap, int reqWidth,
                                     int reqHeight) {
        if (bitmap == null) {
            return null;
        }

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width == reqWidth && height == reqHeight) {
            return bitmap;
        }
        float scaleWidth = (float) reqWidth / (float) width;
        float scaleHeight = (float) reqHeight / (float) height;

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newBitmap = null;
        try {
            newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                    matrix, false);
            recycleBitmap(bitmap, newBitmap);
        } catch (Exception e) {
            e.printStackTrace();
            System.gc();
        }
        return newBitmap;
    }

    public static Bitmap scaleBitmapByWidth(Bitmap bitmap, int newWidth) {
        int width = bitmap.getWidth();
        if (newWidth == width) {
            return bitmap;
        }
        int height = bitmap.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleWidth);
        Bitmap newBitmap = null;
        try {
            newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                    matrix, true);
            recycleBitmap(bitmap, newBitmap);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
        }
        return newBitmap;
    }

    public static Bitmap scaleBitmapByHeight(Bitmap bitmap, int newHeight) {
        int height = bitmap.getHeight();

        if (newHeight == height) {
            return bitmap;
        }
        int width = bitmap.getWidth();
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleHeight, scaleHeight);
        Bitmap newBitmap = null;
        try {
            newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                    matrix, true);
            recycleBitmap(bitmap, newBitmap);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
        }
        return newBitmap;
    }


    public static Bitmap createBitmapByFile(String imgPath) {
        return createBitmapByFile(imgPath, null);
    }

    public static Bitmap createBitmapByFile(File imgFile) {
        return createBitmapByFile(imgFile, null);
    }


    public static Bitmap createBitmapByFile(String imgPath, BitmapFactory.Options options) {
        return createBitmapByFile(new File(imgPath), options);
    }


    public static Bitmap createBitmapByFile(File imgFile, BitmapFactory.Options options) {
        Bitmap bmp = null;
        if (imgFile.exists() && imgFile.isFile()) {
            InputStream is = null;
            try {
                is = new FileInputStream(imgFile.getAbsolutePath());
                bmp = BitmapFactory.decodeStream(is, null, options);
            } catch (Throwable e) {
                e.printStackTrace();
            } finally {
                try {
                    if (is != null)
                        is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bmp;
    }


    /**
     * 获取图片文件的信息，是否旋转了90度，如果是则反转
     *
     * @param path 图片的路径
     */
    public static Bitmap createBitmapByFixRotate(String path) {
        int degree = getPicRotate(path);
        return rotateBitmap(createBitmapByFile(path), degree);
    }


    public static Bitmap matrixBitmap(Bitmap srcBitmap, Matrix matrix) {
        return createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(), srcBitmap.getHeight(), matrix, false);
    }


    public static Bitmap createBitmap(Bitmap srcBitmap, int x, int y, int width, int height,
                                      Matrix matrix, boolean filter) {
        return Bitmap.createBitmap(srcBitmap, x, y, width, height, matrix, filter);
    }

    public static Bitmap createBitmapByFile(String path, int width, int height) {
        if (StrUtil.isEmpty(path)) {
            return null;
        }
        return createBitmapByFile(new File(path), width, height);
    }


    public static Bitmap createBitmapByFile(File imgFile, int width, int height) {
        if (imgFile.exists() && imgFile.isFile()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(imgFile.getAbsolutePath(), options);
            options.inSampleSize = calculateInSampleSize(options, width,
                    height);
            options.inJustDecodeBounds = false;
            return createBitmapByFile(imgFile, options);
        }
        return null;
    }


    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }


    /**
     * 读取图片文件旋转的角度
     *
     * @param path 图片绝对路径
     * @return 图片旋转的角度
     */
    public static int getPicRotate(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }


    public static Bitmap rotateBitmap(Bitmap bitmap, int degree) {
        if (degree % 360 == 0) {
            return bitmap;
        }

        Matrix matrix = new Matrix();
        matrix.setRotate(degree);
        return matrixBitmap(bitmap, matrix);
    }


    /**
     * drawable 转 bitmap
     *
     * @param drawable srcDrawable
     * @return bitmap
     */
    public static Bitmap drawable2Bitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * bitmap 转 drawable
     *
     * @param bitmap srcbitmap
     * @return drawable
     */
    public static Drawable bitmap2Drawable(Bitmap bitmap) {
        return new BitmapDrawable(bitmap);
    }

    /**
     * 获取圆角图片
     *
     * @param srcBitmap
     * @param radius    圆角图的
     * @return Drawable
     */
    public static Drawable bitmap2RoundDrawable(Bitmap srcBitmap, int radius) {
        return bitmap2Drawable(bitmap2RoundBitmap(srcBitmap, radius));
    }


    public Bitmap createBitmap(int width, int height, @ColorInt int color) {
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(color);
        return bitmap;

    }


    /**
     * 获取圆角图片
     *
     * @param srcBitmap
     * @param radius    圆角图
     * @return Bitmap
     */
    public static Bitmap bitmap2RoundBitmap(Bitmap srcBitmap, int radius) {
        Bitmap output = Bitmap.createBitmap(srcBitmap.getWidth(), srcBitmap
                .getHeight(), srcBitmap.getConfig());
        Canvas canvas = new Canvas(output);
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, srcBitmap.getWidth(), srcBitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(0xff424242);
        canvas.drawRoundRect(rectF, radius, radius, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(srcBitmap, rect, rect, paint);

        return output;
    }


    /**
     * 获取圆形图片
     *
     * @param srcBitmap
     * @return Drawable
     */
    public static Drawable bitmap2CircleDrawable(Bitmap srcBitmap) {
        return bitmap2Drawable(cropCircleBitmap(srcBitmap));
    }


    /**
     * 获取圆形图片
     *
     * @param srcBitmap
     * @return Drawable
     */
    public static Bitmap cropCircleBitmap(Bitmap srcBitmap) {
        return cropCircleBitmap(srcBitmap, Color.TRANSPARENT, 0);
    }


    /**
     * 获取圆形图片
     *
     * @param srcBitmap
     * @return Drawable
     */
    public static Bitmap cropCircleBitmap(Bitmap srcBitmap, @ColorInt int borderColor, int borderWidth) {
        int minEdge = Math.min(srcBitmap.getWidth(), srcBitmap.getHeight());
        int dx = (srcBitmap.getWidth() - minEdge) / 2;
        int dy = (srcBitmap.getHeight() - minEdge) / 2;
        // Init shader
        Shader shader = new BitmapShader(srcBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Matrix matrix = new Matrix();
        matrix.setTranslate(-dx, -dy);   // Move the target area to center of the source bitmap
        shader.setLocalMatrix(matrix);
        // Init paint
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setShader(shader);
        // Create and draw circle bitmap
        Bitmap output = Bitmap.createBitmap(minEdge, minEdge, srcBitmap.getConfig());
        Canvas canvas = new Canvas(output);
        if (borderWidth > 0) {
            canvas.drawOval(new RectF(0, 0, minEdge, minEdge), paint);
            Paint borderPaint = new Paint();
            borderPaint.setStyle(Paint.Style.STROKE);
            borderPaint.setStrokeWidth(borderWidth);
            borderPaint.setColor(borderColor);
            borderPaint.setStrokeJoin(Paint.Join.ROUND);
            borderPaint.setStrokeCap(Paint.Cap.ROUND);
            borderPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            int radius = (int) (minEdge / 2f);
            canvas.drawCircle(radius, radius, radius, borderPaint);
        } else {
            canvas.drawOval(new RectF(0, 0, minEdge, minEdge), paint);
        }
        // Recycle the source bitmap, because we already generate a new one
        recycleBitmap(srcBitmap, output);
        return output;
    }

    /**
     * Bitmap recycle
     * 防止在某些情况下两个Bitmap一样，导致使用了已经recycle的Bitmap
     *
     * @param recycle
     * @param another
     */
    public static void recycleBitmap(Bitmap recycle, Bitmap another) {
        if (recycle == null) {
            return;
        }
        if (recycle.isRecycled()) {
            return;
        }
        if (recycle != another) {
            recycle.recycle();
        }
    }


    /**
     * 裁剪Bitmap 为正方形，以最小边为基准
     *
     * @param bitmap
     * @return bitmap
     */
    public static Bitmap cropSquareBitmap(Bitmap bitmap) {
        return cropSquareBitmap(bitmap, Math.min(bitmap.getWidth(), bitmap.getHeight()));
    }

    /**
     * 将给定图片维持宽高比缩放后，截取正中间的正方形部分。
     *
     * @param bitmap 原图
     * @param width  希望得到的正方形部分的边长
     * @return 缩放截取正中部分后的位图。
     */
    public static Bitmap cropSquareBitmap(Bitmap bitmap, int width) {
        if (null == bitmap || 0 >= width) {
            return null;
        }
        int widthOrg = bitmap.getWidth();
        int heightOrg = bitmap.getHeight();
        int minWidth = Math.min(widthOrg, heightOrg);// 获取最小的边
        float scale = (float) width / (float) minWidth;
        int startX = 0;
        int startY = 0;
        if (minWidth < widthOrg) {
            startX = (widthOrg - minWidth) / 2;
        }

        if (minWidth < heightOrg) {
            startY = (heightOrg - minWidth) / 2;
        }
        Matrix matrix = new Matrix();
        matrix.setScale(scale, scale);
        Bitmap result = Bitmap.createBitmap(bitmap, startX, startY, minWidth, minWidth, matrix, true);
        recycleBitmap(bitmap, result);
        return result;
    }

}

