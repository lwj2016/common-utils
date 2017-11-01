package com.lwj.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by lwj on 16/3/9.
 * lwjfork@gmail.com
 */
public class MD5Util {


    public static final String MD5_KEY = "MD5";


    /**
     * 将字符串装换为MD5
     *
     * @param str
     * @return
     */
    public static String str2Md5Str(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance(MD5_KEY);
            md.update(str.getBytes());
            BigInteger bigInteger = new BigInteger(md.digest());
            return bigInteger.toString(16);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return str;
    }

    public static String str2MD5Str(String str, int start, int end) {
        String md5 = str2Md5Str(str);

        if (md5 != null) {
            if (md5.length() > end) {
                return md5.substring(start, end);
            }
        }
        return md5;
    }


    public static String str2MD5_16Str(String str) {
        return str2MD5Str(str, 8, 24);
    }


    public static int str2MD5Int(String str, int start, int end) {
        String md5 = str2Md5Str(str);
        int result = 0;
        if (md5 != null) {
            if (md5.length() > end) {
                String sunStr = md5.substring(start, end);
                return Integer.parseInt(sunStr, 16);
            }
        }
        return result;
    }


    public static String str2MD5_16Int(String str) {
        return str2MD5Str(str, 8, 24);
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

    /**
     * get the md5 of file
     *
     * @param filename
     * @return
     */
    public static String md5File(String filename) {
        InputStream fis;
        byte[] buffer = new byte[1024];
        int numRead = 0;
        MessageDigest md5;
        try {
            fis = new FileInputStream(filename);
            md5 = MessageDigest.getInstance(MD5_KEY);
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
