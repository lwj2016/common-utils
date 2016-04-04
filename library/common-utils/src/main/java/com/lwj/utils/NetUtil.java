package com.lwj.utils;

import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.lwj.utils.log.LogUtil;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class NetUtil {
    /**
     * 检查网络连接状态
     *
     * @param context
     * @return
     */
    public static boolean checkNetwork(Context context) {
        if (context != null) {
            ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()) {
                return true;
            }
        }

        return false;
    }

    /**
     * 判断wifi是否打开
     *
     * @param context
     * @return
     */
    public static boolean chckWifiState(Context context) {
        if (context == null) {
            LogUtil.e("%s", "context is null");
            return false;
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Service.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getTypeName().equalsIgnoreCase("WIFI") && info[i].isConnected()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public static String getHostUrl(String url) {
        int end = -1;
        if (url.startsWith("http://"))
            end = url.indexOf("/", 7);
        else if (url.startsWith("https://"))
            end = url.indexOf("/", 8);
        else if (url.startsWith("ftp://"))
            end = url.indexOf("/", 6);
        if (end == -1)
            return url;
        else
            return url.substring(0, end);
    }

    public static String getDomain(String url) {
        int start = 0;
        int end = -1;
        if (url.startsWith("http://")) {
            start = 7;
            end = url.indexOf("/", 7);
        } else if (url.startsWith("https://")) {
            start = 8;
            end = url.indexOf("/", 8);
        } else if (url.startsWith("ftp://")) {
            start = 6;
            end = url.indexOf("/", 6);
        }
        if (end == -1)
            return url.substring(start);
        else
            return url.substring(start, end);
    }

    public static String encodeUrlParamter(String url) {
        int qIndex = url.indexOf('?');
        if (qIndex == -1)
            return url;
        String host = url.substring(0, qIndex + 1);
        String paramStr = url.substring(qIndex + 1);
        String[] params = paramStr.split("&");
        StringBuffer sbUrl = new StringBuffer(host);
        if (params != null)
            for (String param : params) {
                int eIndex = param.indexOf('=');
                String name = param.substring(0, eIndex + 1);
                String value = param.substring(eIndex + 1);
                try {
                    sbUrl.append(name).append(URLEncoder.encode(value, "gb2312"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        return sbUrl.toString();
    }

    public static String encodeParamter(String param, String charsetName, int times)
            throws UnsupportedEncodingException {
        String encodedParam = param;
        for (int i = 0; i < times; i++) {
            encodedParam = URLEncoder.encode(encodedParam, charsetName);
        }
        return encodedParam;
    }

    public static List<NameValuePair> parseUrl(String url) {
        List<NameValuePair> l = null;
        try {
            l = URLEncodedUtils.parse(new URI(url), "utf-8");
        } catch (URISyntaxException e) {
        } catch (IllegalArgumentException e) {
        }
        return l;
    }

    public static String getParam(List<NameValuePair> params, String name) {
        if (params == null) {
            return "";
        }
        for (NameValuePair p : params) {
            if (p.getName().equals(name)) {
                return p.getValue();
            }
        }
        return "";
    }

    public static String getParam(String url, String name) {
        List<NameValuePair> l = parseUrl(url);
        return getParam(l, name);
    }

    public static Bitmap loadBitmapFromURL(String url) {
        if (!url.contains("http://")) {
            url = "http://" + url;
        }
        LogUtil.d("Thread--- %s ------ start load -----%s", Thread.currentThread().getId(), url);
        Bitmap bmp = null;
        try {
            byte[] imageBytes = loadRawDataFromURL(url);
            bmp = BitmapFactory.decodeByteArray(imageBytes, 0,
                    imageBytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bmp;
    }

    // 。

    /**
     * 给定一个URL，从这个URL下载原始数据块
     *
     * @param urlStr url
     * @return
     * @throws Exception
     */
    public static byte[] loadRawDataFromURL(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 配置基础网络链接参数
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(3000);

        InputStream is = conn.getInputStream();
        BufferedInputStream bis = new BufferedInputStream(is);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        final int BUFFER_SIZE = 1024 * 5;
        final int EOF = -1;

        int c;
        byte[] buf = new byte[BUFFER_SIZE];

        while (true) {
            c = bis.read(buf);
            if (c == EOF)
                break;
            baos.write(buf, 0, c);
        }
        conn.disconnect();
        is.close();
        byte[] data = baos.toByteArray();
        baos.flush();
        return data;
    }
}