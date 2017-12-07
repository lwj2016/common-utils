package com.lwj.utils;

import com.lwj.utils.log.LogUtil;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.lwj.utils.Preconditions.checkNotNUll;

/**
 * Created by lwj on 16/6/7.
 * Des:
 */
public class URLUtil {

    public static void main(String[] args) {
        String url1 = "http://www.runoob.com/java/java-url-processing.html";
        String url2 = "http://www.runoob.com/java/java-url-processing.html?a=3&a&a=4&a=5&b=4#hh";
        String url3 = "http://www.baidu.com/index.html?title=我是包含and符号&&的标题&&&12121212&id=024";
//        System.out.println(getProtocol(url1));
//        System.out.println(getHost(url1));
//        System.out.println(getPath(url1));
//        System.out.println(getQuery(url1));
//        System.out.println(getQueryMap(url1));
//        System.out.println(getRef(url1));
//        System.out.println(getProtocol(url2));
//        System.out.println(getHost(url2));
//        System.out.println(getPath(url2));
//        System.out.println(getQuery(url2));
//        System.out.println(getQueryMap(url2));
//        System.out.println(getRef(url2));
//
//        System.out.println(getProtocol(url3));
//        System.out.println(getHost(url3));
//        System.out.println(getPath(url3));
        System.out.println(getQuery(url3));
        System.out.println(getQueryMap(url3));
        System.out.println(getRef(url3));
    }

    public static URL getUrlFromStr(String urlStr) {
        urlStr = checkNotNUll(urlStr, "URL String must not be null");
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
        if (StringUtil.isEmpty(query)) {
            return mapRequest;
        }
        String[] queryArray = query.split("&");
        for (String str : queryArray) {
            if (StringUtil.isEmpty(str)) {
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
        return RegularUtil.isValid(url, reg);
    }
}
