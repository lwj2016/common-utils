package com.lwj.utils;

import android.os.Build;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created:2018/6/25
 * User：lwjfork
 * Email:lwjfork@gmail.com
 * Des:
 * ====================
 */

public class WebViewUtil {

    public static final String JAVA_SCRIPT = "javascript:";

    public static void executeJS(final WebView webView, String jsMethod, final ValueCallback callback, Object... jsParams) {
        final String url = buildJSUrl(jsMethod, jsParams);
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
        webView.onResume();
        webView.resumeTimers();
    }

    public static void pasueWebView(WebView webView) {
        webView.onPause();
        webView.pauseTimers();
    }


    public static void destoryWebView(WebView webView) {
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
    }

    public static void main(String[] arags) {
        String jsMethod = "test";
        System.out.println(buildJSUrl(jsMethod, "11", 1, 1));
    }

}
