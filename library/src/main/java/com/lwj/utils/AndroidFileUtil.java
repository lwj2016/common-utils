package com.lwj.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by lwj on 2018/3/16.
 * lwjfork@gmail.com
 */

public class AndroidFileUtil extends FileUtil {

    private static final String FILEPATH = "file";

    public static File getFileDiskCacheDir(Context context, String fileName) throws IOException {
        return getFileDiskCacheDir(context, fileName, true);
    }

    public static File getFileDiskCacheDir(Context context, String fileName, boolean isCreate) throws IOException {
        File folder = getDiskCacheDir(context, FILEPATH);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        File file = new File(folder, fileName);
        if (!file.exists() && isCreate) {
            file.createNewFile();
        }
        return file;
    }

    /**
     * Get a usable cache directory (external if available, internal otherwise).
     *
     * @param context    The context to use
     * @param uniqueName A unique directory name to append to the cache dir
     * @return The cache dir
     */
    public synchronized static File getDiskCacheDir(Context context, String uniqueName) {
        // Check if media is mounted or storage is built-in, if so, try and use external cache dir
        // otherwise use internal cache dir
        String cachePath = null;
        if (isSDCardMounted()) { // sd 卡
            cachePath = getExternalCacheDir(context).getPath();
        } else if (isExternalStorageRemovable()) {  // 其他扩展存储
            cachePath = getExternalCacheDir(context).getPath();
        } else {
            cachePath = context.getCacheDir().getPath();// 默认放入缓存中了
        }
        FileUtil.createNewFolder(cachePath + File.separator + uniqueName);

        return new File(cachePath + File.separator + uniqueName);
    }


    public synchronized static String getCustomFolderPath(Context context, String folerName) {
        String cachePath = null;
        if (isSDCardMounted()) { // sd 卡
            cachePath = getExternalCacheDir(context).getPath();
        } else if (isExternalStorageRemovable()) {  // 其他扩展存储
            cachePath = getExternalCacheDir(context).getPath();
        } else {
            cachePath = context.getCacheDir().getPath();// 默认放入缓存中了
        }
        cachePath = cachePath + File.separator + folerName;
        FileUtil.createNewFolder(cachePath);
        return cachePath;
    }


    /**
     * @param context
     * @param uniqueName
     * @return 获取内部存储。/data/data 下的
     */
    public synchronized static File getInterCacheDir(Context context, String uniqueName) {
        // Check if media is mounted or storage is built-in, if so, try and use external cache dir
        // otherwise use internal cache dir
        return new File(context.getCacheDir().getPath() + File.separator + uniqueName);
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

    @TargetApi(8)
    public static File getExternalCacheDir(Context context) {
        if (OSUtils.hasFroyo()) {
            return context.getExternalCacheDir() == null ? getExCacheFile(context) : context.getExternalCacheDir();
        }
        return getExCacheFile(context);
    }

    private static File getExCacheFile(Context context) {
        final String cacheDir = "/Android/data/" + context.getPackageName() + "/cache/";
        return new File(Environment.getExternalStorageDirectory().getPath() + cacheDir);
    }

    /**
     * 文件存到sd卡
     *
     * @param name
     * @param content
     */
    public static void saveStr2SD(String name, String content) {

        FileOutputStream fos = null;
        try {
            File file = new File(Environment.getExternalStorageDirectory(), name);
            fos = new FileOutputStream(file);
            fos.write(content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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

    /**
     * 获取SD卡的根目录
     *
     * @return
     */
    public static String getSDCardBaseDir() {
        if (isSDCardMounted()) {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
        return null;
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

}
