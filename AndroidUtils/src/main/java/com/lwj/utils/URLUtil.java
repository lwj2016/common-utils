package com.lwj.utils;

import com.lwj.utils.log.LogUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by lwj on 16/6/7.
 * Des:
 */
public class URLUtil {

    public static URL getUrlFromStr(String urlStr) {
        urlStr = ExceptionUtil.checkNotNUll(urlStr, "URL String must not be null");
        URL url = null;
        try {
            url = new URL(urlStr);
        } catch (MalformedURLException e) {
            LogUtil.e("this  " + urlStr + " could not be parsed as a URL");
        }
        return url;
    }

    /**
     * 获取协议
     *
     * @param urlStr
     *
     * @return
     */
    public static String getProtocol(String urlStr) {
        URL url = getUrlFromStr(urlStr);
        return getProtocol(url);
    }

    /**
     * 获取协议
     *
     * @return
     */
    public static String getProtocol(URL url) {
        return url.getProtocol();
    }

    /**
     * 返回URL的主机
     *
     * @param urlStr
     *
     * @return
     */
    public static String getHost(String urlStr) {
        URL url = getUrlFromStr(urlStr);
        return getHost(url);
    }

    /**
     * 返回URL的主机
     *
     * @return
     */
    public static String getHost(URL url) {
        return url.getHost();
    }


    /**
     * 返回URL路径部分
     *
     * @param urlStr
     *
     * @return
     */
    public static String getPath(String urlStr) {
        URL url = getUrlFromStr(urlStr);
        return getPath(url);
    }

    /**
     * 返回URL路径部分
     *
     * @return
     */
    public static String getPath(URL url) {
        return url.getPath();
    }

    /**
     * 返回URL查询部分。
     *
     * @param urlStr
     *
     * @return
     */
    public static String getQuery(String urlStr) {
        URL url = getUrlFromStr(urlStr);
        return getQuery(url);
    }

    /**
     * 返回URL查询部分。
     *
     * @return
     */
    public static String getQuery(URL url) {
        return url.getQuery();
    }

    /**
     * 获取此 URL 的锚点（也称为"引用"）。
     *
     * @param urlStr
     *
     * @return
     */
    public static String getRef(String urlStr) {
        URL url = getUrlFromStr(urlStr);
        return getRef(url);
    }

    /**
     * 获取此 URL 的锚点（也称为"引用"）。
     *
     * @return
     */
    public static String getRef(URL url) {
        return url.getRef();
    }

    public static String getUserInfo(String urlStr) {
        URL url = getUrlFromStr(urlStr);
        return getUserInfo(url);
    }

    public static String getUserInfo(URL url) {
        return url.getUserInfo();
    }

    public static Map<String, ArrayList<String>> getQueryMap(String urlStr) {
        Map<String, ArrayList<String>> mapRequest = new HashMap<String, ArrayList<String>>();
        String query = getQuery(urlStr);
        if (StrUtil.isEmpty(query)) {
            return mapRequest;
        }
        String[] queryArray = query.split("&");
        for (String str : queryArray) {
            if (StrUtil.isEmpty(str)) {
                continue;
            }
            ArrayList<String> strings = null;
            String[] kvArray = str.split("=");
            if (mapRequest.containsKey(kvArray[0])) {
                strings = mapRequest.get(kvArray[0]);
            } else {
                strings = new ArrayList<>();
            }
            if (kvArray.length > 1) {
                strings.add(kvArray[1]);
            } else {
                strings.add(null);
            }
            mapRequest.put(kvArray[0], strings);
        }
        return mapRequest;
    }

    public static boolean isValidUrl(String url) {
        String reg = "((ht|f)tps?):\\/\\/[\\w\\-]+(\\.[\\w\\-]+)+([\\w\\-\\.,@?^=%&:\\/~\\+#]*[\\w\\-\\@?^=%&\\/~\\+#])?";
        return isValidUrl(url, reg);
    }

    public static boolean isValidUrl(String url, String reg) {
        return RegularUtil.isMatch(url, reg);
    }
}
