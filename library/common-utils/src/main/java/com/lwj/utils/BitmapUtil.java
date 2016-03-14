package com.lwj.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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

    public static void saveBitmap2File(File saveDir, String jpgName,
                                       Bitmap bitmap, Bitmap.CompressFormat format, int width, int height) {
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }
        File jpgFile = new File(saveDir, jpgName);
        try {
            jpgFile.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        FileOutputStream fOut = null;
        try {
            fOut = new FileOutputStream(jpgFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }
        if (width * height != 0) {
            Bitmap newBitmap = scaleBitmap(bitmap, width, height); // 根据所要的图片做相应修改
            newBitmap.compress(format, 100, fOut);
        } else
            bitmap.compress(format, 100, fOut);
        try {
            if (fOut != null)
                fOut.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (fOut != null)
                fOut.close();
            bitmap.recycle();
        } catch (IOException e) {
            e.printStackTrace();
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
    public static Bitmap getScaleBitmap(Bitmap bitmap, int reqWidth,
                                        int reqHeight) {
        if (bitmap == null) {
            return null;
        }
        float scale = 1f;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = (float) reqWidth / (float) width;
        float scaleHeight = (float) reqHeight / (float) height;
        if (scaleWidth == 1 && scaleHeight == 1) {// 不需要放大或缩小
            return bitmap;
        } else {// 取最小值
            scale = scaleWidth > scaleHeight ? scaleHeight : scaleWidth;
        }
        /*
         * else if ((scaleWidth > 1 && scaleHeight > 1) || (scaleWidth < 1 &&
		 * scaleHeight < 1)) {// 等比例同时放大或缩小,取最小值 scale = scaleWidth >
		 * scaleHeight ? scaleHeight : scaleWidth; } else {//
		 * 一个放大，一个缩小，取缩小的值(小于1，即取小值) scale = scaleWidth > scaleHeight ?
		 * scaleHeight : scaleWidth; }
		 */
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        Bitmap newBitmap = null;
        try {
            newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                    matrix, true);
        } catch (Exception e) {
            e.printStackTrace();
            System.gc();
        }
        return newBitmap;
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int newWidth, int newHeight) {
        if (bitmap == null)
            return null;

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHight);
        Bitmap newBitmap = null;
        try {
            newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                    matrix, true);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
        }
        if (newBitmap != bitmap) {
            bitmap.recycle();
        }
        return newBitmap;
    }

    public static Bitmap scaleBitmapW(Bitmap bitmap, int newWidth) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleWidth);
        Bitmap newBitmap = null;
        try {
            newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                    matrix, true);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
        }
        return newBitmap;
    }

    /**
     * 将给定图片维持宽高比缩放后，截取正中间的正方形部分。
     *
     * @param bitmap     原图
     * @param edgeLength 希望得到的正方形部分的边长
     * @return 缩放截取正中部分后的位图。
     */
    public static Bitmap centerSquareScaleBitmap(Bitmap bitmap, int edgeLength) {
        if (null == bitmap || edgeLength <= 0) {
            return null;
        }
        Bitmap result = bitmap;
        int widthOrg = bitmap.getWidth();
        int heightOrg = bitmap.getHeight();
        if (widthOrg > edgeLength && heightOrg > edgeLength) {
            // 压缩到一个最小长度是edgeLength的bitmap
            int longerEdge = (int) (edgeLength * Math.max(widthOrg, heightOrg) / Math
                    .min(widthOrg, heightOrg));
            int scaledWidth = widthOrg > heightOrg ? longerEdge : edgeLength;
            int scaledHeight = widthOrg > heightOrg ? edgeLength : longerEdge;
            Bitmap scaledBitmap;
            try {
                scaledBitmap = Bitmap.createScaledBitmap(bitmap, scaledWidth,
                        scaledHeight, true);
            } catch (Exception e) {
                return null;
            }
            // 从图中截取正中间的正方形部分。
            int xTopLeft = (scaledWidth - edgeLength) / 2;
            int yTopLeft = (scaledHeight - edgeLength) / 2;
            try {
                result = Bitmap.createBitmap(scaledBitmap, xTopLeft, yTopLeft,
                        edgeLength, edgeLength);
                scaledBitmap.recycle();
            } catch (Exception e) {
                return null;
            }
        }
        return result;
    }

    public static Bitmap scaleBitmapH(Bitmap bitmap, int newHeight) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleHeight, scaleHeight);
        Bitmap newBitmap = null;
        try {
            newBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height,
                    matrix, true);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
        }
        return newBitmap;
    }

    public static Bitmap toRoundCorner(Bitmap bitmap, int pixels)
            throws Throwable {
        Bitmap output = null;
        try {
            output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
                    Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
        }
        if (output == null)
            return null;
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        // final float roundPx = pixels;
        float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        int scale = 5;
        float tmp;
        if (bitmap.getWidth() < bitmap.getHeight()) {
            tmp = bitmap.getWidth() / scale;
        } else {
            tmp = bitmap.getHeight() / scale;
        }
        if (tmp > pixels) {
            roundPx = tmp;
        }
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * 可以自由设置圆角的半径 而不是像toRoundCorner函数 圆角半径至少为width或者高较小者的1/5
     *
     * @param bitmap
     * @param pixels
     * @return
     * @throws Throwable
     */
    public static Bitmap toRoundCornerFree(Bitmap bitmap, int pixels)
            throws Throwable {
        Bitmap output = null;
        try {
            output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
                    Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
            System.gc();
        }
        if (output == null)
            return null;
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        // final float roundPx = pixels;
        float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        // int scale = 5;
        // float tmp;
        // if (bitmap.getWidth() < bitmap.getHeight()) {
        // tmp = bitmap.getWidth() / scale;
        // } else {
        // tmp = bitmap.getHeight() / scale;
        // }
        // if (tmp > pixels) {
        // roundPx = tmp;
        // }
        // canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    public static Bitmap getLocalBitmap(String imgPath) {
        return getLocalBitmap(new File(imgPath));
    }

    public static Bitmap getLocalBitmap(File imgFile) {
        Bitmap bmp = null;
        if (imgFile.exists() && imgFile.isFile()) {
            InputStream is = null;
            try {
                is = new FileInputStream(imgFile.getAbsolutePath());
                bmp = BitmapFactory.decodeStream(is);
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

    //等比例缩放
    public static Bitmap getScaleWH(Bitmap bitmap, int maxWitdh, int maxlength) {
        Matrix matrix = new Matrix();
        int width = bitmap.getWidth();// 获取资源位图的宽
        int height = bitmap.getHeight();// 获取资源位图的高
        float w = maxWitdh / bitmap.getWidth();
        float h = maxlength / bitmap.getHeight();
        matrix.postScale(w, h);// 获取缩放比例
        // 根据缩放比例获取新的位图
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
        return newbmp;
    }

    public static Bitmap getScaledLocalBitmap(String imgPath, int sampleSize) {
        File imgFile = new File(imgPath);
        Bitmap bmp = null;
        if (imgFile.exists() && imgFile.isFile()) {
            InputStream is = null;
            try {
                is = new FileInputStream(imgPath);
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = sampleSize;
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
     * 根据指定的宽、高，计算出成比例缩小的倍数，进行缩小。
     *
     * @param imgPath
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static Bitmap getScaledLocalBitmap(String imgPath, int reqWidth,
                                              int reqHeight) {
        File imgFile = new File(imgPath);
        Bitmap bmp = null;
        if (imgFile.exists() && imgFile.isFile()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(imgPath, options);
            options.inSampleSize = calculateInSampleSize(options, reqWidth,
                    reqHeight);
            options.inJustDecodeBounds = false;
            bmp = BitmapFactory.decodeFile(imgPath, options);
        }
        return bmp;
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
     * 获取图片文件的信息，是否旋转了90度，如果是则反转
     *
     * @param bitmap 需要旋转的图片
     * @param path   图片的路径
     */
    public static Bitmap reviewPicRotate(Bitmap bitmap, String path) {
        int degree = getPicRotate(path);
        if (degree != 0) {
            Matrix m = new Matrix();
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            m.setRotate(degree); // 旋转angle度
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, m, true);// 从新生成图片
        }
        return bitmap;
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


    public static final int UNCONSTRAINED = -1;

    public static BitmapFactory.Options getOptions(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        return options;
    }

    /**
     * get bitmap without oom
     *
     * @param path
     * @param options
     * @return
     */
    public static Bitmap getBitmapByPath(String path, BitmapFactory.Options options, int screenWidth, int screenHeight) {
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
        if (options != null) {
            Rect r = getScreenRegion(screenWidth, screenHeight);
            int w = r.width();
            int h = r.height();
            int maxSize = w > h ? w : h;
            int inSimpleSize = computeSampleSize(options, maxSize, w * h);
            options.inSampleSize = inSimpleSize;
            options.inJustDecodeBounds = false;
        }
        Bitmap b = BitmapFactory.decodeStream(in, null, options);
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return b;
    }


    public static Bitmap getBitmapByPath(String path, int width, int height) {
        if (path == null) {
            return null;
        }

        return getBitmapByPath(path, getOptions(path), width, height);
    }


    private static Rect getScreenRegion(int width, int height) {
        return new Rect(0, 0, width, height);
    }

    /**
     * @param options
     * @param minSideLength
     * @param maxNumOfPixels
     * @return
     */
    public static int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == UNCONSTRAINED) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
        int upperBound =
                (minSideLength == UNCONSTRAINED) ? 128 : (int) Math.min(Math.floor(w / minSideLength),
                        Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if ((maxNumOfPixels == UNCONSTRAINED) && (minSideLength == UNCONSTRAINED)) {
            return 1;
        } else if (minSideLength == UNCONSTRAINED) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }
}
