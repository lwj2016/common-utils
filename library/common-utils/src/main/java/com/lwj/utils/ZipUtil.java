package com.lwj.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;

/**
 * Created by lwj on 2017/3/20.
 * lwjfork@gmail.com
 */

public class ZipUtil {


    /**
     * 解压文件
     *
     * @param zipFile
     * @param folderPath
     * @throws ZipException
     * @throws IOException
     */
    public static void unZip(String zipFile, String folderPath) throws IOException {
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
    }

}
