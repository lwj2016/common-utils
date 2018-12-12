package com.common.lib.utils;



import com.common.lib.utils.log.LogUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by lwj on 16/3/9.
 * lwjfork@gmail.com
 */
public final class FileUtil extends ZipUtil {


    /**
     * @param path directory path
     * @param name file name
     * @return really path for this file
     */
    public static String getFilePath(String path, String name) {
        if (path == null) {
            LogUtil.e("ERROR ---%s", "file path must not be null");
            return null;
        } else if (name == null) {
            LogUtil.e("ERROR ---%s", "file name must not be null");
            return null;
        } else if (name.endsWith(File.separator)) {
            LogUtil.e("ERROR --file name --" + name + "-- can not be end with %s", File.separator);
            return null;
        }
        StringBuilder filePath = new StringBuilder();
        if (path.endsWith(File.separator) && name.startsWith(File.separator)) {
            filePath.append(path);
            filePath.append(name.substring(1));
            return filePath.toString();
        } else if (path.endsWith(File.separator)) {
            filePath.append(path).append(name);
            return filePath.toString();
        } else {
            filePath.append(path).append(File.separator).append(name);
            return filePath.toString();
        }
    }


    /**
     * 创建目录
     *
     * @param path
     * @return
     */
    public static File createDirectory(String path) {
        File dir = new File(path);
        if (dir.isFile()) {
            boolean isDelete = dir.delete();
            if (!isDelete) { // 删除失败
                return null;
            }
        }
        boolean isHaveDir = dir.exists();
        if (!isHaveDir) { // 目录不存在，创建目录
            isHaveDir = dir.mkdirs();
        }
        if (!isHaveDir) { // 目录创建失败了
            return null;
        }
        return dir;
    }


    /**
     * 创建目录
     *
     * @param path
     * @return
     */
    public static File createDirectory(String path, String name) {
        return createDirectory(getFilePath(path, name));
    }


    /**
     * 创建文件
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static File createFile(String path) throws IOException {
        File file = new File(path);
        if (file.exists()) {
            return file;
        }
        boolean isCreate = file.createNewFile();
        if (isCreate) {
            return file;
        }
        return null;
    }

    /**
     * 创建文件
     *
     * @param dir
     * @param fileName
     * @return
     * @throws IOException
     */
    public static File createFile(File dir, String fileName) throws IOException {
        if (dir == null) {
            return null;
        }
        if (dir.isFile()) {
            boolean isDelete = dir.delete();
            if (!isDelete) { // 删除失败
                return null;
            }
        }
        boolean isHaveDir = true;
        if (!dir.exists()) { // 目录已经存在
            isHaveDir = dir.mkdirs();
        }
        if (!isHaveDir) { // 目录创建失败了
            return null;
        }

        return createFile(dir.getAbsolutePath() + File.separator + fileName);
    }


    /**
     * 创建文件
     *
     * @param dirPath
     * @param fileName
     * @return
     * @throws IOException
     */
    public static File createFile(String dirPath, String fileName) throws IOException {
        File dir = createDirectory(dirPath);
        if (dir == null) {
            return null;
        }
        return createFile(dir.getAbsolutePath() + File.separator + fileName);
    }


    /**
     * @param object obj
     * @param path   directory
     * @param name   file name
     */
    public synchronized static <T extends Serializable> void saveObj2File(T object, String path, String name) {
        String filePath = getFilePath(path, name);
        if (filePath == null) {
            return;
        }
        FileOutputStream f_out = null;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            f_out = new FileOutputStream(filePath);
            ObjectOutputStream s = new ObjectOutputStream(f_out);
            s.writeObject(object);
            s.flush();
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (f_out != null) {
                try {
                    f_out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    @SuppressWarnings("unchecked")
    public synchronized static <T> T readObjFromFile(String path, String name) {
        FileInputStream f_in = null;
        Object object = null;
        String filePath = path + File.separator + name;
        if (!isFileExists(filePath)) {
            LogUtil.w("%s", "no File  " + filePath);
            return null;
        }

        try {
            f_in = new FileInputStream(filePath);
            ObjectInputStream s = new ObjectInputStream(f_in);
            object = s.readObject();
            s.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            if (f_in != null) {
                try {
                    f_in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            return (T) object;
        } catch (ClassCastException e) {

            return null;
        }
    }


    /**
     * 移动文件
     *
     * @param srcFileName 源文件完整路径
     * @param desFileName 目的目录完整路径
     * @return 文件移动成功返回true，否则返回false
     */
    public static boolean moveFile(String srcFileName, String desFileName) {
        File srcFile = new File(srcFileName);
        if (!srcFile.exists() || !srcFile.isFile()) return false;

        File destFile = new File(desFileName);
        if (destFile.exists()) {
            return false;
        }
        return srcFile.renameTo(destFile);
    }

    /**
     * 删除包含该文件夹在内的所有文件
     *
     * @param file
     */
    public static void deleteFilesAll(File file) {
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File f : files) {
                if (f.isFile()) {
                    f.delete();
                } else {
                    deleteFilesAll(f);
                }
            }
            file.delete();
        }
    }

    /**
     * 删除该文件夹内所有文件，不包含该文件夹
     *
     * @param path
     */
    public static void deleteFiles(String path) {
        deleteFilesAll(new File(path));
    }

    /**
     * 删除单个文件
     *
     * @param path
     */
    public static void deleteFile(String path) {
        File file = new File(path);
        file.deleteOnExit();
    }

    /**
     * @param path
     * @return true if file is exits,otherwise false
     */
    public static boolean isFileExists(String path) {
        if (StrUtil.isEmpty(path)) {
            return false;
        }
        return isFileExists(new File(path));
    }

    public static boolean isFileExists(File file) {

        if (file == null) {
            return false;
        }

        return file.exists();
    }


    /**
     * 从文件中读取数据
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static String readStrFromFile(String fileName) throws IOException {
        String res = "";
        BufferedReader br = new BufferedReader(new FileReader(fileName));

        try {

            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = br.readLine()) != null) {
                buffer.append(line);
            }
            res = buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            br.close();
        }
        return res;
    }

    /**
     * 保存数据到文件中
     *
     * @param content
     * @param file
     * @throws IOException
     */
    public static void writeStr2File(String content, File file) throws IOException {

        FileOutputStream fos = null;
        try {
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

    public static long getLastModified(String fileName) {
        File file = new File(fileName);
        return getLastModified(file);
    }

    public static long getLastModified(File file) {
        return file.lastModified();
    }


}
