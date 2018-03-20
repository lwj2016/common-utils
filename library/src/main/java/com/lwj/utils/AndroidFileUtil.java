package com.lwj.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.io.IOException;

/**
 * Created by lwj on 2018/3/16.
 * lwjfork@gmail.com
 */

public class AndroidFileUtil extends FileUtil {


    public synchronized static File getCustomDirs(Context context, String folderName) throws IOException {
        File file;
        if (isSDCardMounted() || !isExternalStorageRemovable()) { // SD卡挂载了或者有效
            file = getExternalCacheDirFolder(context, folderName);
        } else {
            file = getCacheDir(context, folderName);
        }
        return file;

    }

    public synchronized static String getCustomFolderPath(Context context, String folderName) throws IOException {
        File file;
        if (isSDCardMounted() || !isExternalStorageRemovable()) { // SD卡挂载了或者有效
            file = getExternalCacheDirFolder(context, folderName);
        } else {
            file = getCacheDir(context, folderName);
        }
        if (file != null) {
            return file.getAbsolutePath();
        }
        return null;

    }

    public synchronized static File getCustomFile(Context context, String folderName) throws IOException {
        File file;
        if (isSDCardMounted() || !isExternalStorageRemovable()) { // SD卡挂载了或者有效
            file = getExternalCacheDirFile(context, folderName);
        } else {
            file = getCacheFile(context, folderName);
        }
        return file;

    }

    public synchronized static String getCustomFilePath(Context context, String folderName) throws IOException {
        File file;
        if (isSDCardMounted() || !isExternalStorageRemovable()) { // SD卡挂载了或者有效
            file = getExternalCacheDirFile(context, folderName);
        } else {
            file = getCacheFile(context, folderName);
        }
        if (file != null) {
            return file.getAbsolutePath();
        }
        return null;

    }


    /**
     * 文件存到sd卡
     *
     * @param name
     * @param content
     */
    public static void saveStr2FilePriorSD(Context context, String name, String content) throws IOException {

        File file = getExternalCacheDirFile(context, name);
        if (file == null) {
            file = getCacheFile(context, name);
        }
        writeStr2File(file, content);
    }


    /**
     * @param context
     * @return /data/data/{packageName}/cache/
     */
    public synchronized static String getCacheDirPath(Context context) {

        return context.getCacheDir().getPath();
    }

    /**
     * @param context
     * @param uniqueName
     * @return /data/data/{packageName}/cache/xxx
     */
    public synchronized static File getCacheDir(Context context, String uniqueName) {
        return getFolder(getCacheDirPath(context), uniqueName);
    }


    /**
     * @param context
     * @param uniqueName
     * @return /data/data/{packageName}/cache/xxx
     */
    public synchronized static File getCacheFile(Context context, String uniqueName) throws IOException {
        return getFile(getCacheDirPath(context), uniqueName);
    }


    /**
     * @param context
     * @return /data/data/{packageName}/files/
     */
    public synchronized static String getFilesDirPath(Context context) {
        return context.getFilesDir().getPath();
    }

    /**
     * @param context
     * @param uniqueName
     * @return /data/data/{packageName}/files/xxx
     */
    public synchronized static File getFilesDir(Context context, String uniqueName) {
        return getFolder(getFilesDirPath(context), uniqueName);
    }

    /**
     * @param context
     * @param uniqueName
     * @return /data/data/{packageName}/files/xxx
     */
    public synchronized static File getFilesFile(Context context, String uniqueName) throws IOException {
        return getFile(getFilesDirPath(context), uniqueName);
    }


    /**
     * 判断SD卡是否被挂载
     *
     * @return
     */
    public static boolean isSDCardMounted() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }


    public static File getExternalCacheDirFile(Context context, String file) throws IOException {
        return getFile(getExternalCacheDir(context).getAbsolutePath(), file);
    }

    public static String getExternalCacheDirFilePath(Context context, String file) throws IOException {
        return getFilePath(getExternalCacheDir(context).getAbsolutePath(), file);
    }

    public static File getExternalCacheDirFolder(Context context, String file) throws IOException {
        return getFolder(getExternalCacheDir(context).getAbsolutePath(), file);
    }

    public static String getExternalCacheDirFolderPath(Context context, String file) throws IOException {
        return getFolderPath(getExternalCacheDir(context).getAbsolutePath(), file);
    }


    public static File getExternalCacheDir(Context context) {
        File file = null;
        if (OSUtils.hasFroyo()) {
            file = context.getExternalCacheDir();
        }
        if (file == null) {
            file = createExternalCacheDirPath(context);
        }
        return file;
    }

    public static String getExternalCacheDirPath(Context context) {
        File file = getExternalCacheDir(context);
        if (file != null) {
            return file.getAbsolutePath();
        }
        return "";
    }


    /**
     * 有则返回，无则自己创建
     *
     * @param context
     * @return
     */
    public static File createExternalCacheDirPath(Context context) {
        StringBuilder path = new StringBuilder();
        path.append(Environment.getExternalStorageDirectory().getPath());
        path.append("/Android/data").append(context.getPackageName());
        path.append("/cache").append(File.separator);
        return new File(path.toString());
    }

    /**
     * Check if external storage is built-in or removable.
     *
     * @return True if external storage is removable (like an SD card), false
     * otherwise.
     */
    @TargetApi(9)
    public static boolean isExternalStorageRemovable() {
        if (OSUtils.hasGingerbread()) {
            return Environment.isExternalStorageRemovable();
        }
        return true;
    }

    /**
     * 获取sd卡的大小
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static long getSDAllSizeKB() {
        if (isSDCardMounted()) {
            // get path of sdcard
            File path = Environment.getExternalStorageDirectory();
            StatFs sf = new StatFs(path.getPath());
            // get single block size(Byte)
            long blockSize = sf.getBlockSize();
            // 获取所有数据块数
            long allBlocks = sf.getBlockCount();
            // 返回SD卡大小
            return (allBlocks * blockSize) / 1024; // KB
        }
        return 0;
    }

    /**
     * 获取sd卡可用大小
     *
     * @return
     */
    @SuppressWarnings("deprecation")
    public static long getSDAvalibleSizeKB() {
        if (isSDCardMounted()) {
            File path = Environment.getExternalStorageDirectory();
            StatFs sf = new StatFs(path.getPath());
            long blockSize = sf.getBlockSize();
            long avaliableSize = sf.getAvailableBlocks();
            return (avaliableSize * blockSize) / 1024;// KB
        }
        return 0;
    }

    /**
     * 刷新图库
     *
     * @param context
     * @param file
     */
    public static void refreshGallery(Context context, File file) {
        BroadcasUtil.refreshGallery(context, file);
    }
}
