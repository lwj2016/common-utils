package com.lwj.utils;

import android.util.Base64;

import java.nio.charset.Charset;

/**
 * Created by lwj on 16/6/28.
 * Des:
 */
public class Base64Util {


    /**
     * @param src     the data to encode
     * @param charset convert data to  byte
     * @param flag  controls certain features of the encoded output.
     *               Passing {@code DEFAULT} results in output that
     *               adheres to RFC 2045.
     * @return result by encode
     * @see android.util.Base64
     */
    public static String encodeToString(String src, Charset charset, int flag) {

        byte[] data = src.getBytes(charset);

        return encodeToString(data, flag);
    }


    public static String encodeToString(String src, int flag) {

        byte[] data = src.getBytes();

        return encodeToString(src, Charset.defaultCharset(), flag);
    }


    public static String encodeToString(String src) {

        return encodeToString(src, Base64.DEFAULT);
    }


    public static String encodeToString(byte[] data, int flag) {
        return Base64.encodeToString(data, flag);
    }

    public static String encodeToString(byte[] data) {
        return encodeToString(data, Base64.DEFAULT);
    }


    /**
     * @param src     the data to encode
     * @param charset convert data to  byte
     * @param flag
     * @return result by encode
     * @see android.util.Base64
     */
    public static byte[] encodeToByte(String src, Charset charset, int flag) {

        byte[] data = src.getBytes(charset);

        return encodeToByte(data, flag);
    }


    public static byte[] encodeToByte(String src, int flag) {

        byte[] data = src.getBytes();

        return encodeToByte(src, Charset.defaultCharset(), flag);
    }


    public static byte[] encodeToByte(String src) {

        return encodeToByte(src, Base64.DEFAULT);
    }

    public static byte[] encodeToByte(byte[] data) {
        return encodeToByte(data, Base64.DEFAULT);
    }

    public static byte[] encodeToByte(byte[] data, int flag) {
        return Base64.encode(data, flag);
    }


    /**
     * @param src     the data to encode
     * @param charset convert data to  byte
     * @param flag
     * @return result by encode
     * @see android.util.Base64
     */
    public static String decodeToString(String src, Charset charset, int flag) {
        byte[] data = decodeToByte(src, flag);
        return new String(data, charset);
    }


    public static String decodeToString(String src, int flag) {

        return decodeToString(src, Charset.defaultCharset(), flag);
    }

    public static String decodeToString(String src) {

        return decodeToString(src, Base64.DEFAULT);
    }


    public static byte[] decodeToByte(String src, int flag) {
        return Base64.decode(src, flag);
    }


    public static byte[] decodeToByte(String src) {

        return decodeToByte(src, Base64.DEFAULT);
    }


}
