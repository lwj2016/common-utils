package com.lwj.utils;

import android.app.Service;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.webkit.CookieManager;

import com.lwj.utils.log.LogUtil;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ProtocolException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class NetUtil {
    private static int timeoutMillis = 5000;

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

    /**
     * 用post方式从指定url获取文本文件的字符串
     *
     * @param url
     * @return
     */
    public static String getStringByPost(String url, List<NameValuePair> nameValuePairs) {
        HttpPost httppost = new HttpPost(url);
        // List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
        // nameValuePairs.add(new BasicNameValuePair("id", "12345"));
        // Your DATA

        try {
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpParams params = httpClient.getParams();
        HttpConnectionParams.setConnectionTimeout(params, timeoutMillis);
        HttpConnectionParams.setSoTimeout(params, timeoutMillis);

        CookieManager cookieManager = CookieManager.getInstance();
        String cookie = cookieManager.getCookie(url);
        httppost.addHeader("Cookie", cookie);

        HttpResponse httpResponse;
        try {
            httpResponse = httpClient.execute(httppost);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return EntityUtils.toString(httpResponse.getEntity());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            httppost.abort();
        } catch (IOException e) {
            e.printStackTrace();
            httppost.abort();
        }
        return null;
    }

    public static void addCookies(String url, HttpResponse response) {
        Header[] headers = response.getHeaders("Set-Cookie");
        if (headers != null && headers.length > 0) {
            CookieManager cookieManager = CookieManager.getInstance();
            for (Header h : headers) {
                cookieManager.setCookie(url, h.getValue());
            }
        }
    }
}
