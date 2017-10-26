package com.lwj.utils;

import android.content.Context;
import android.os.Environment;

import com.lwj.utils.log.LogUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by lwj on 16/3/9.
 * lwjfork@gmail.com
 */
public class FileUtil {


    /**
     * @param _context
     * @param filename
     * @return 从 assets 里读文件
     */
    public static String getStrFromAssets(Context _context, String filename) {
        InputStream is;

        Writer writer = new StringWriter();
        char[] buffer = new char[8 * 1024];
        try {
            is = _context.getResources().getAssets().open(filename);
            Reader reader = new BufferedReader(new InputStreamReader(is));
            int n = 0;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    /**
     * 文件存到sd卡
     *
     * @param name
     * @param content
     */
    public static void saveToSDCard(String name, String content) {

        FileOutputStream fos = null;
        try {
            //Environment.getExternalStorageDirectory()。获取sd卡的路径
            File file = new File(Environment.getExternalStorageDirectory(), name);
            fos = new FileOutputStream(file);
            fos.write(content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

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

    public synchronized static void saveObject(Object object, String path, String name) {
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

    public static boolean downFile(String url, String tempDir, String savePath, String fileName) {
        boolean isFinish = false;
        long fileLength;
        File tempFile;
        try {
            URL myUrl = new URL(url);
            URLConnection conn = myUrl.openConnection();
            conn.connect();


            fileLength = conn.getContentLength();
            InputStream is = conn.getInputStream();
            if (is == null) {
                isFinish = false;
            }
            tempFile = new File(tempDir + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(tempFile);
            byte[] buf = new byte[4096];
            int numread;
            while (true) {
                numread = is.read(buf);
                if (numread <= 0) {
                    break;
                }
                fos.write(buf, 0, numread);
            }
            fos.flush();
            fos.getFD().sync();
            fos.close();
            is.close();


            if (fileLength == tempFile.length()) {
                isFinish = moveFile(tempFile.getAbsolutePath(), savePath);
            }
            tempFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isFinish;
    }

    public static Object readObject(String path, String name) {
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
        return object;

    }


    /**
     * 移动文件
     *
     * @param srcFileName 源文件完整路径
     * @param destDirName 目的目录完整路径
     * @return 文件移动成功返回true，否则返回false
     */
    public static boolean moveFile(String srcFileName, String destDirName) {

        File srcFile = new File(srcFileName);

        if (!srcFile.exists() || !srcFile.isFile()) return false;

        File destDir = new File(destDirName);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        // APPLOG.log("file path " + destDirName + srcFile.getName());
        return srcFile.renameTo(new File(destDirName + File.separator + srcFile.getName()));
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
        File fileDel = new File(path);
        if (fileDel.exists()) {
            File[] files = fileDel.listFiles();
            for (File file : files) {
                file.delete();
            }
        }
    }

    /**
     * 删除单个文件
     *
     * @param path
     */
    public static void deleteFile(String path) {
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            file.delete();
        }
    }

    /**
     * @param path
     * @return true if file is exits,otherwise false
     */
    public static boolean isFileExists(String path) {

        if (StringUtil.isEmpty(path)) {
            return false;
        }

        return new File(path).exists();
    }

    public static boolean isFileExists(File file) {

        if (file == null) {
            return false;
        }

        return file.exists();
    }

    public static boolean createNewFolder(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                return file.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean createNewFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                return file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * 从sdcard读取数据
     *
     * @param fileName
     * @return
     * @throws java.io.IOException
     */
    public static String readFileSdcardFile(String fileName) throws IOException {
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

    public static long getLastModified(String fileName) {
        File file = new File(fileName);
        return getLastModified(file);
    }

    public static long getLastModified(File file) {
        return file.lastModified();
    }


    /**
     * 解压文件
     *
     * @param zipFile
     * @param folderPath
     * @throws IOException
     * @throws IOException
     */
    public static boolean unZip(String zipFile, String folderPath) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile));
        String subDirName = "";
        ZipEntry zipEntry = zipInputStream.getNextEntry();
        File file = new File(folderPath);
        file.deleteOnExit();
        file.mkdir();
        while (zipEntry != null) {
            subDirName = zipEntry.getName();
            if (zipEntry.isDirectory()) {//  目录-创建目录
                subDirName = subDirName.substring(0, subDirName.length() - 1);
                File subFolder = new File(folderPath + File.separator + subDirName);
                subFolder.mkdir();
            } else {  // 不是目录的- 写文件
                File subFile = new File(folderPath + File.separator + subDirName);
                boolean isSuccess = subFile.createNewFile();
                FileOutputStream out = new FileOutputStream(subFile);
                int len;
                byte[] buffer = new byte[1024];
                while ((len = zipInputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, len);
                    out.flush();
                }
                out.close();
            }
            zipInputStream.closeEntry();
            zipEntry = zipInputStream.getNextEntry();
        }

        zipInputStream.close();
        return true;
    }


    /**
     * 解压文件
     *
     * @param zipFile
     * @param folderPath
     * @throws IOException
     */
    public static boolean unZip(String zipFile, String folderPath, boolean isDeleteZip) throws IOException {
        boolean isSuccess = unZip(zipFile, folderPath);
        if (isDeleteZip && isSuccess) {
            new File(zipFile).deleteOnExit();
        }
        return isSuccess;
    }


    /**
     * 压缩文件
     *
     * @param filepath 源文件路径
     * @param zipPath  压缩后路径
     * @throws IOException 异常
     */
    public static void zipFile(String filepath, String zipPath) throws IOException {
        File file = new File(filepath);
        if (file.isDirectory()) {
            zipDirectory(file, zipPath);
        } else {
            zipSingleFile(file, zipPath);
        }
    }

    /**
     * 压缩单个文件
     */
    private static void zipSingleFile(File file, String zipPath) throws IOException {
        File zipFile = new File(zipPath);
        InputStream input = new FileInputStream(file);
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        zipOut.putNextEntry(new ZipEntry(file.getName()));
        int temp = 0;
        while ((temp = input.read()) != -1) {
            zipOut.write(temp);
        }
        input.close();
        zipOut.close();
    }


    /**
     * 压缩 目录
     */
    private static void zipDirectory(File file, String zipPath) throws IOException {
        File zipFile = new File(zipPath);
        InputStream input = null;
        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; ++i) {
                input = new FileInputStream(files[i]);
                zipOut.putNextEntry(new ZipEntry(file.getName() + File.separator + files[i].getName()));
                int temp = 0;
                while ((temp = input.read()) != -1) {
                    zipOut.write(temp);
                }
                input.close();
            }
        }
        zipOut.close();
    }
}
