package com.lwj.utils;

import android.annotation.TargetApi;
import android.os.Environment;
import android.os.StatFs;

import com.lwj.utils.context.GlobalContext;

import java.io.File;
import java.io.IOException;

/**
 * Created by lwj on 2018/3/16.
 * lwjfork@gmail.com
 */

public class AndroidFileUtil extends FileUtil {


    public synchronized static File getCustomDirs(String folderName) throws IOException {
        File file;
        if (isSDCardMounted() || !isExternalStorageRemovable()) { // SD卡挂载了或者有效
            file = getExternalCacheDirFolder(folderName);
        } else {
            file = getCacheDir(folderName);
        }
        return file;

    }

    public synchronized static String getCustomFolderPath(String folderName) throws IOException {
        File file;
        if (isSDCardMounted() || !isExternalStorageRemovable()) { // SD卡挂载了或者有效
            file = getExternalCacheDirFolder(folderName);
        } else {
            file = getCacheDir(folderName);
        }
        if (file != null) {
            return file.getAbsolutePath();
        }
        return null;

    }

    public synchronized static File getCustomFile(String folderName) throws IOException {
        File file;
        if (isSDCardMounted() || !isExternalStorageRemovable()) { // SD卡挂载了或者有效
            file = getExternalCacheDirFile(folderName);
        } else {
            file = getCacheFile(folderName);
        }
        return file;

    }

    public synchronized static String getCustomFilePath(String folderName) throws IOException {
        File file;
        if (isSDCardMounted() || !isExternalStorageRemovable()) { // SD卡挂载了或者有效
            file = getExternalCacheDirFile(folderName);
        } else {
            file = getCacheFile(folderName);
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
    public static void saveStr2FilePriorSD(String name, String content) throws IOException {

        File file = getExternalCacheDirFile(name);
        if (file == null) {
            file = getCacheFile(name);
        }
        writeStr2File(file, content);
    }


    /**
     * @return /data/data/{packageName}/cache/
     */
    public synchronized static String getCacheDirPath() {

        return GlobalContext.getContext().getCacheDir().getPath();
    }

    /**
     * @param uniqueName
     *
     * @return /data/data/{packageName}/cache/xxx
     */
    public synchronized static File getCacheDir(String uniqueName) {
        return getFolder(getCacheDirPath(), uniqueName);
    }


    /**
     * @param uniqueName
     *
     * @return /data/data/{packageName}/cache/xxx
     */
    public synchronized static File getCacheFile(String uniqueName) throws IOException {
        return getFile(getCacheDirPath(), uniqueName);
    }


    /**
     *
     * @return /data/data/{packageName}/files/
     */
    public synchronized static String getFilesDirPath() {
        return GlobalContext.getContext().getFilesDir().getPath();
    }

    /**
     * @param uniqueName
     *
     * @return /data/data/{packageName}/files/xxx
     */
    public synchronized static File getFilesDir(String uniqueName) {
        return getFolder(getFilesDirPath(), uniqueName);
    }

    /**
     * @param uniqueName
     *
     * @return /data/data/{packageName}/files/xxx
     */
    public synchronized static File getFilesFile(String uniqueName) throws IOException {
        return getFile(getFilesDirPath(), uniqueName);
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


    public static File getExternalCacheDirFile(String file) throws IOException {
        return getFile(getExternalCacheDir().getAbsolutePath(), file);
    }

    public static String getExternalCacheDirFilePath(String file) throws IOException {
        return getFilePath(getExternalCacheDir().getAbsolutePath(), file);
    }

    public static File getExternalCacheDirFolder(String file) throws IOException {
        return getFolder(getExternalCacheDir().getAbsolutePath(), file);
    }

    public static String getExternalCacheDirFolderPath(String file) throws IOException {
        return getFolderPath(getExternalCacheDir().getAbsolutePath(), file);
    }


    public static File getExternalCacheDir() {
        File file = null;
        if (OSUtils.hasFroyo()) {
            file = GlobalContext.getContext().getExternalCacheDir();
        }
        if (file == null) {
            file = createExternalCacheDirPath();
        }
        return file;
    }

    public static String getExternalCacheDirPath() {
        File file = getExternalCacheDir();
        if (file != null) {
            return file.getAbsolutePath();
        }
        return "";
    }


    /**
     * 有则返回，无则自己创建
     *
     *
     * @return
     */
    public static File createExternalCacheDirPath() {
        StringBuilder path = new StringBuilder();
        path.append(Environment.getExternalStorageDirectory().getPath());
        path.append("/Android/data").append(GlobalContext.getContext().getPackageName());
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
     * @param file 文件
     */
    public static void refreshGallery(File file) {
        BroadcastUtil.refreshGallery(file);
    }

    /**
     * 刷新图库
     *
     * @param filePath 文件路径
     */
    public static void refreshGallery(String filePath) {
        BroadcastUtil.refreshGallery(filePath);
    }
}
