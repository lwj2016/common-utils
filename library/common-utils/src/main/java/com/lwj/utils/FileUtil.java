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


    public synchronized static void saveObject(Object object, String path, String name) {
        FileOutputStream f_out = null;
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        try {
            f_out = new FileOutputStream(path + File.separator + name);
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

    public static boolean isSDCardReady() {
        String STATE = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(STATE);
    }
}
