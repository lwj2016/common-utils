package com.lwj.utils;

import android.text.TextUtils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lwj on 16/3/9.
 * lwjfork@gmail.com
 */
public class MD5Util {
    /**
     * 将字符串装换为MD5
     *
     * @param str
     * @return
     */
    public static String strToMd5(String str) {
        String md5Str = null;
        if (str != null && str.length() != 0) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(str.getBytes());
                byte b[] = md.digest();
                int i;
                StringBuffer buf = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++) {
                    i = b[offset];
                    if (i < 0)
                        i += 256;
                    if (i < 16)
                        buf.append("0");
                    buf.append(Integer.toHexString(i));
                }
                // 32位
                // md5Str = buf.toString();
                // 16位
                md5Str = buf.toString().substring(8, 24);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return md5Str;
    }

    public static String strToMd5_32(String str) {
        String md5Str = null;
        if (str != null && str.length() != 0) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(str.getBytes());
                byte b[] = md.digest();
                int i;
                StringBuffer buf = new StringBuffer("");
                for (int offset = 0; offset < b.length; offset++) {
                    i = b[offset];
                    if (i < 0)
                        i += 256;
                    if (i < 16)
                        buf.append("0");
                    buf.append(Integer.toHexString(i));
                }
                // 32位
                md5Str = buf.toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return md5Str;
    }

    /**
     * 取md5的低7位转化成int
     *
     * @param md5
     * @return
     */
    public static int md5L8ToInt(String md5) {
        if (md5 == null)
            return 0;
        md5 = md5.replaceAll("-", "");
        if (md5.length() == 0)
            return 0;
        if (md5.length() >= 8)
            md5 = md5.substring(md5.length() - 7);
        return (int) Long.parseLong(md5, 16);
    }

    /**
     * 转换url成md5码，再截取前8位为int值，并取绝对值
     *
     * @param coverUrl
     * @return
     */
    public static int buildSsidByUrl(String coverUrl, String bookUrl) {
        if (!TextUtils.isEmpty(coverUrl)) {
            return Math.abs(md5L8ToInt(strToMd5_32(coverUrl)));
        } else {
            return Math.abs(md5L8ToInt(strToMd5_32(bookUrl)));
        }
    }

    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'A', 'B', 'C', 'D', 'E', 'F'};


    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(HEX_DIGITS[(b[i] & 0xf0) >>> 4]);
            sb.append(HEX_DIGITS[b[i] & 0x0f]);
        }
        return sb.toString();
    }

    public static String md5sum(String filename) {
        InputStream fis;
        byte[] buffer = new byte[1024];
        int numRead = 0;
        MessageDigest md5;
        try {
            fis = new FileInputStream(filename);
            md5 = MessageDigest.getInstance("MD5");
            while ((numRead = fis.read(buffer)) > 0) {
                md5.update(buffer, 0, numRead);
            }
            fis.close();
            return toHexString(md5.digest());
        } catch (Exception e) {
            return filename;
        }
    }
}
