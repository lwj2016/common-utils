package com.lwj.utils;

import android.os.Build;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.lwj.utils.context.GlobalContext;
import com.lwj.utils.log.LogUtil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created:2018/6/25
 * User：lwjfork
 * Email:lwjfork@gmail.com
 * Des:
 * ====================
 */

public class WebViewUtil {

    public static final String JAVA_SCRIPT = "javascript:";
    public static final String REGX = "; ";

    public static void executeJS(final WebView webView, String jsMethod, final ValueCallback callback, Object... jsParams) {
        final String url = buildJSUrl(jsMethod, jsParams);
        LogUtil.d("execute JS code %s", url);
        webView.post(new Runnable() {
            @Override
            public void run() {
                if (OSUtils.hasKitKat()) {  // 19
                    webView.evaluateJavascript(url, callback);
                } else {
                    webView.loadUrl(url);
                }
            }
        });
    }

    public static void executeJS(WebView webView, String jsMethod, Object... jsParams) {
        executeJS(webView, jsMethod, null, jsParams);
    }

    public static void loadUrl(final WebView webView, final String jsMethod, Object... jsParams) {
        final String url = buildJSUrl(jsMethod, jsParams);
        LogUtil.d("execute JS code %s", url);
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl(url);
            }
        });

    }

    public static String buildJSUrl(String jsMethod, Object... jsParams) {
        StringBuilder params = new StringBuilder();
        StringBuilder result = new StringBuilder();
        for (Object jsParam : jsParams) {
            if (params.length() > 0) {
                params.append(",");
            }
            params.append("'");
            params.append(jsParam);
            params.append("'");
        }
        result.append(JAVA_SCRIPT).append(jsMethod);
        result.append("(");
        result.append(params);
        result.append(")");
        return result.toString();
    }


    public static WebSettings setCommonSetting(WebSettings webSetting, boolean isJSEnable) {
        setJavaScriptEnabled(webSetting, isJSEnable);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { //  5.0以后 https url load http image
            webSetting.setMixedContentMode(webSetting.getMixedContentMode());
        }
        // 允许打开窗口
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        // 禁止保存表单数据
        webSetting.setSaveFormData(false);
        // 禁止保存密码
        webSetting.setSavePassword(false);
        // 缩放
        setZoom(webSetting, true);
        return webSetting;
    }

    public static WebSettings setCommonSetting(WebSettings webSetting, boolean isJSEnable, String cahcheFile) {
        setCommonSetting(webSetting, isJSEnable);
        setCache(webSetting, cahcheFile);
        return webSetting;
    }

    public static WebSettings setZoom(WebSettings webSetting, boolean isSupportZoom) {
        if (isSupportZoom) {
            //缩放操作
            webSetting.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
            webSetting.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
            webSetting.setDisplayZoomControls(false); //隐藏原生的缩放控件
        } else {
            //缩放操作
            webSetting.setSupportZoom(false); //支持缩放，默认为true。是下面那个的前提。
            webSetting.setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
            webSetting.setDisplayZoomControls(true); //隐藏原生的缩放控件
        }
        //设置自适应屏幕，两者合用
        webSetting.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSetting.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
        return webSetting;
    }

    public static WebSettings setJavaScriptEnabled(WebSettings settings, boolean isEnable) {
        settings.setJavaScriptEnabled(isEnable);
        return settings;
    }

    public static WebSettings setCache(WebSettings webSetting, String file) {
        if (NetUtil.isNetConnected()) {
            webSetting.setCacheMode(WebSettings.LOAD_DEFAULT);//根据cache-control决定是否从网络上取数据。
        } else {
            webSetting.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);//没网，则从本地获取，即离线加载
        }

        webSetting.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        webSetting.setDatabaseEnabled(true);   //开启 database storage API 功能
        webSetting.setAppCacheEnabled(true);//开启 Application Caches 功能
        webSetting.setAppCachePath(file); //设置  Application Caches 缓存目录
        return webSetting;
    }


    public static void resumeWebView(WebView webView) {
        if (webView == null) {
            return;
        }
        webView.onResume();
        webView.resumeTimers();
        LogUtil.d("WebView %s", "resume");
    }

    public static void pauseWebView(WebView webView) {
        if (webView == null) {
            return;
        }
        webView.onPause();
        webView.pauseTimers();
        LogUtil.d("WebView %s", "pause");
    }


    public static void destroyWebView(WebView webView) {
        if (webView != null) {
            webView.stopLoading();
            webView.clearHistory();
            webView.clearView();
            webView.removeAllViews();
            // 禁止 JS 代码继续执行
            webView.getSettings().setJavaScriptEnabled(false);
            ViewGroup parent = (ViewGroup) webView.getParent();
            if (parent != null) {
                parent.removeView(webView);
            }
            webView.destroy();
            webView = null;
        }
        LogUtil.d("WebView %s", "destroy");
    }

    public static void main(String[] arags) {
        String jsMethod = "test";
        System.out.println(buildJSUrl(jsMethod, "11", 1, 1));
    }


    public static void setCookie(WebView webView, String url, Map<String, String> values) {

        if (!OSUtils.hasLollipop()) {
            CookieSyncManager.createInstance(GlobalContext.getContext());
        } else {
            CookieManager.getInstance().setAcceptCookie(true);
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        }
        Set<Map.Entry<String, String>> set = values.entrySet();
        Iterator<Map.Entry<String, String>> iterator = set.iterator();
        CookieManager cookieManager = CookieManager.getInstance();
        while (iterator.hasNext()) {
            StringBuilder itemValue = new StringBuilder();
            Map.Entry<String, String> entry = iterator.next();
            String key = entry.getKey();
            String value = entry.getValue();
            itemValue.append(key).append("=").append(value);
            cookieManager.setCookie(url, itemValue.toString());
        }
        if (!OSUtils.hasLollipop()) {
            CookieSyncManager.createInstance(GlobalContext.getContext()).sync();
        }
    }


    public static Map<String, String> getCookie(String url) {
        HashMap<String, String> cookieValue = new HashMap<>();
        CookieManager cookieManager = CookieManager.getInstance();
        String cookie = cookieManager.getCookie(url);
        if (cookie != null && cookie.length() > 0) {
            String[] array = cookie.split(REGX);
            for (String s : array) {
                String[] itemValue = s.split("=");
                if (itemValue != null && itemValue.length == 2) {
                    cookieValue.put(itemValue[0], itemValue[1]);
                }
            }
        }
        return cookieValue;
    }

    public static void removeAllCookie() {
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);// 允许接受 Cookie
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            cookieManager.removeAllCookie();// 移除
        } else {
            cookieManager.removeSessionCookies(null);// 移除

        }
        cookieManager.removeAllCookie();
    }

}
