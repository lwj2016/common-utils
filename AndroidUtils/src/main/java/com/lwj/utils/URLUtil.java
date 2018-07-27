package com.lwj.utils;

import com.lwj.utils.log.LogUtil;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by lwj on 16/6/7.
 * Des:
 */
public class URLUtil {

    /**
     * Gets url from str.
     *
     * @param urlStr the url str
     * @return the url from str
     * @author Created by lwjfork on 2018/07/23 16:07
     */
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
     * @param urlStr the url str
     * @return protocol
     * @author Created by lwjfork on 2018/07/23 16:07
     */
    public static String getProtocol(String urlStr) {
        URL url = getUrlFromStr(urlStr);
        return getProtocol(url);
    }

    /**
     * 获取协议
     *
     * @param url the url
     * @return protocol
     * @author Created by lwjfork on 2018/07/23 16:07
     */
    public static String getProtocol(URL url) {
        return url.getProtocol();
    }

    /**
     * 返回URL的主机
     *
     * @param urlStr the url str
     * @return host
     * @author Created by lwjfork on 2018/07/23 16:07
     */
    public static String getHost(String urlStr) {
        URL url = getUrlFromStr(urlStr);
        return getHost(url);
    }

    /**
     * 返回URL的主机
     *
     * @param url the url
     * @return host
     * @author Created by lwjfork on 2018/07/23 16:07
     */
    public static String getHost(URL url) {
        return url.getHost();
    }


    /**
     * 返回URL路径部分
     *
     * @param urlStr the url str
     * @return path
     * @author Created by lwjfork on 2018/07/23 16:07
     */
    public static String getPath(String urlStr) {
        URL url = getUrlFromStr(urlStr);
        return getPath(url);
    }

    /**
     * 返回URL路径部分
     *
     * @param url the url
     * @return path
     * @author Created by lwjfork on 2018/07/23 16:07
     */
    public static String getPath(URL url) {
        return url.getPath();
    }

    /**
     * 返回URL查询部分。
     *
     * @param urlStr the url str
     * @return query
     * @author Created by lwjfork on 2018/07/23 16:07
     */
    public static String getQuery(String urlStr) {
        URL url = getUrlFromStr(urlStr);
        return getQuery(url);
    }

    /**
     * 返回URL查询部分。
     *
     * @param url the url
     * @return query
     * @author Created by lwjfork on 2018/07/23 16:07
     */
    public static String getQuery(URL url) {
        return url.getQuery();
    }

    /**
     * 获取此 URL 的锚点（也称为"引用"）。
     *
     * @param urlStr the url str
     * @return ref
     * @author Created by lwjfork on 2018/07/23 16:07
     */
    public static String getRef(String urlStr) {
        URL url = getUrlFromStr(urlStr);
        return getRef(url);
    }

    /**
     * 获取此 URL 的锚点（也称为"引用"）。
     *
     * @param url the url
     * @return ref
     * @author Created by lwjfork on 2018/07/23 16:07
     */
    public static String getRef(URL url) {
        return url.getRef();
    }

    /**
     * Gets user info.
     *
     * @param urlStr the url str
     * @return the user info
     * @author Created by lwjfork on 2018/07/23 16:07
     */
    public static String getUserInfo(String urlStr) {
        URL url = getUrlFromStr(urlStr);
        return getUserInfo(url);
    }

    /**
     * Gets user info.
     *
     * @param url the url
     * @return the user info
     * @author Created by lwjfork on 2018/07/23 16:07
     */
    public static String getUserInfo(URL url) {
        return url.getUserInfo();
    }

    /**
     * Gets query map.
     *
     * @param urlStr the url str
     * @return the query map
     * @author Created by lwjfork on 2018/07/23 16:07
     */
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

    /**
     * Is valid url boolean.
     *
     * @param url the url
     * @return the boolean
     * @author Created by lwjfork on 2018/07/23 16:07
     */
    public static boolean isValidUrl(String url) {
        String reg = "((ht|f)tps?):\\/\\/[\\w\\-]+(\\.[\\w\\-]+)+([\\w\\-\\.,@?^=%&:\\/~\\+#]*[\\w\\-\\@?^=%&\\/~\\+#])?";
        return isValidUrl(url, reg);
    }

    /**
     * Is valid url boolean.
     *
     * @param url the url
     * @param reg the reg
     * @return the boolean
     * @author Created by lwjfork on 2018/07/23 16:07
     */
    public static boolean isValidUrl(String url, String reg) {
        return RegularUtil.isMatch(url, reg);
    }




    /**
     * Encode string.
     * default utf-8
     *
     * @param str the str
     * @param enc the enc
     * @return the string
     * @author Created by liuwenjie on 2018/07/25 14:52
     */
    public static String encode(String str, String enc) {

        try {
            return URLEncoder.encode(str, enc);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * Encode string.
     * default utf-8
     *
     * @param str the str
     * @return the string
     * @author Created by liuwenjie on 2018/07/25 14:52
     */
    public static String encode(String str) {
        return encode(str, "utf-8");
    }

    /**
     * Decode string.
     * default utf-8
     *
     * @param str the str
     * @param enc the enc
     * @return the string
     * @author Created by liuwenjie on 2018/07/25 14:52
     */
    public static String decode(String str, String enc) {
        try {
            return URLDecoder.decode(str, enc);
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    /**
     * Decode string.
     * default utf-8
     *
     * @param str the str
     * @return the string
     * @author Created by liuwenjie on 2018/07/25 14:52
     */
    public static String decode(String str) {
        return decode(str, "utf-8");
    }


}
