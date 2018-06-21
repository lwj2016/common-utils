package com.lwj.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Created by lwj on 2018/3/5.
 * lwjfork@gmail.com
 * 文件解压缩工具类
 */

public class ZipUtil {
    /**
     * 解压文件
     *
     * @param zipFile
     * @param folderPath
     *
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
     *
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
     *
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
