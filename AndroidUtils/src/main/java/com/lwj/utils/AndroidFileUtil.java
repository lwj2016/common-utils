package com.lwj.utils;

import android.annotation.TargetApi;
import android.app.Application;
import android.os.Environment;
import android.os.StatFs;

import com.lwj.utils.context.GlobalContext;

import java.io.File;
import java.io.IOException;
import java.sql.Struct;

/**
 * Created by lwj on 2018/3/16.
 * lwjfork@gmail.com
 */

public class AndroidFileUtil extends FileUtil {


    private static Application getContext() {
        return GlobalContext.getContext();
    }

    /**
     * @param folderName
     * @return
     * @throws IOException
     * @see #getExternalCacheDir() 优先 SD卡 路径  #
     * @see #getCacheFile(String) 其次 APP 路径
     */
    public synchronized static File getCustomDirs(String folderName) throws IOException {
        File file;
        if (isSDCardMounted() || !isExternalStorageRemovable()) { // SD卡挂载了或者有效
            file = getExternalCacheDirFolder(folderName);
        } else {
            file = getCacheDir(folderName);
        }
        return file;

    }

    /**
     * @param folderName
     * @return
     * @throws IOException
     * @see #getExternalCacheDir() 优先 SD卡 路径  #
     * @see #getCacheFile(String) 其次 APP 路径
     */
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

    /**
     * @param folderName
     * @return
     * @throws IOException
     * @see #getExternalCacheDir() 优先 SD卡 路径  #
     * @see #getCacheFile(String) 其次 APP 路径
     */
    public synchronized static File getCustomFile(String folderName) throws IOException {
        File file;
        if (isSDCardMounted() || !isExternalStorageRemovable()) { // SD卡挂载了或者有效
            file = getExternalCacheDirFile(folderName);
        } else {
            file = getCacheFile(folderName);
        }
        return file;

    }

    /**
     * @param folderName
     * @return
     * @throws IOException
     * @see #getExternalCacheDir() 优先 SD卡 路径  #
     * @see #getCacheFile(String) 其次 APP 路径
     */
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
        writeStr2File(content, file);
    }


    /**
     * @return /data/data/{packageName}/cache/
     */
    public synchronized static String getCacheDirPath() {

        return getContext().getCacheDir().getPath();
    }

    /**
     * @param uniqueName
     * @return /data/data/{packageName}/cache/xxx
     */
    public synchronized static File getCacheDir(String uniqueName) {
        return createDirectory(getCacheDirPath(), uniqueName);
    }


    /**
     * @param uniqueName
     * @return /data/data/{packageName}/cache/xxx
     */
    public synchronized static File getCacheFile(String uniqueName) throws IOException {
        return createFile(getCacheDirPath(), uniqueName);
    }


    /**
     * @return /data/data/{packageName}/files/
     */
    public synchronized static String getFilesDirPath() {
        return getContext().getFilesDir().getPath();
    }

    /**
     * @param uniqueName
     * @return /data/data/{packageName}/files/xxx
     */
    public synchronized static File getFilesDir(String uniqueName) {
        return createDirectory(getFilesDirPath(), uniqueName);
    }

    /**
     * @param uniqueName
     * @return /data/data/{packageName}/files/xxx
     */
    public synchronized static File getFilesFile(String uniqueName) throws IOException {
        return createFile(getFilesDirPath(), uniqueName);
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
        return createFile(getExternalCacheDir().getAbsolutePath(), file);
    }

    public static String getExternalCacheDirFilePath(String file) throws IOException {
        return getFilePath(getExternalCacheDir().getAbsolutePath(), file);
    }

    public static File getExternalCacheDirFolder(String file) throws IOException {
        return createDirectory(getExternalCacheDir().getAbsolutePath(), file);
    }

    public static String getExternalCacheDirFolderPath(String file) throws IOException {
        return getFilePath(getExternalCacheDir().getAbsolutePath(), file);
    }

    /***
     *
     * @return SDCard/Android/data/{pm}/cache/
     */
    public static File getExternalCacheDir() {
        File file = null;
        if (OSUtils.hasFroyo_8()) {
            file = getContext().getExternalCacheDir();
        }
        if (file == null) {
            file = createExternalCacheDirPath();
        }
        return file;
    }

    /***
     *
     * @return SDCard/Android/data/{pm}/cache/
     */
    public static String getExternalCacheDirPath() {
        File file = getExternalCacheDir();
        if (file != null) {
            return file.getAbsolutePath();
        }
        return "";
    }


    /**
     * 有则返回，无则自己创建
     * <p>
     * /**
     *
     * @return /Android/data/{packageName}/cache/
     */
    public static File createExternalCacheDirPath() {
        String path = StrUtil.join(File.separator
                , Environment.getExternalStorageDirectory().getPath()
                , "Android"
                , "data"
                , AppUtil.getPackageName()
                , "cache" + File.separator
        );
        return new File(path);
    }

    /**
     * Check if external storage is built-in or removable.
     *
     * @return True if external storage is removable (like an SD card), false
     * otherwise.
     */
    @TargetApi(9)
    public static boolean isExternalStorageRemovable() {
        if (OSUtils.hasGingerbread_9()) {
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
