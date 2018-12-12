package com.common.lib.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;


import com.common.lib.GlobalContext;
import com.common.lib.utils.log.LogUtil;

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

public final class WebViewUtil {

    public static final String JAVA_SCRIPT = "javascript:";
    public static final String REGX = "; ";

    @SuppressWarnings("SameParameterValue")
    @TargetApi(19)
    public static void executeJS(final WebView webView, String jsMethod, final ValueCallback<String> callback, Object... jsParams) {
        final String url = buildJSUrl(jsMethod, jsParams);
        LogUtil.d("execute JS code %s", url);
        webView.post(new Runnable() {
            @Override
            public void run() {
                if (OSUtils.hasKitKat_19()) {  // 19
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

        if (OSUtils.hasLollipop_21()) { //  5.0以后 https url load http image
            webSetting.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        // 禁止保存表单数据
        webSetting.setSaveFormData(false);
        // 支持js自动打开窗口
        webSetting.setJavaScriptCanOpenWindowsAutomatically(true);
        // 当webview调用requestFocus时为webview设置节点
        webSetting.setNeedInitialFocus(true);
        // 缩放
        setZoom(webSetting, true);
        return webSetting;
    }


    public static void setSafe(WebView webView) {
        if (OSUtils.hasHoneycomb_11()) {
            // 在4.0至4.2的Android系统上，Webview会增加searchBoxJavaBredge_，导致远程代码执行。攻击者可以向页面植入Javascript，通过反射在客户端中执行任意恶意代码。
            webView.removeJavascriptInterface("searchBoxJavaBredge_");
        }
        // 禁止保存密码
        webView.getSettings().setSavePassword(false);
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

        return webSetting;
    }

    public static WebSettings setAutoFit(WebSettings webSetting, boolean isAuto) {
        //设置自适应屏幕，两者合用
        webSetting.setUseWideViewPort(isAuto); //将图片调整到适合webview的大小
        webSetting.setLoadWithOverviewMode(isAuto); // 缩放至屏幕的大小
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
        webView.resumeTimers();
        webView.onResume();
        LogUtil.d("WebView %s", "resume");
    }

    public static void pauseWebView(WebView webView) {
        if (webView == null) {
            return;
        }
        webView.pauseTimers();
        webView.onPause();
        LogUtil.d("WebView %s", "pause");
    }


    public static void destroyWebView(WebView webView) {
        if (webView != null) {
            webView.stopLoading();
            // fix ZoomButton 引起的 crash bug
            webView.setVisibility(View.GONE);
            // 禁止 JS 代码继续执行
            webView.getSettings().setJavaScriptEnabled(false);
            webView.clearHistory();
            webView.clearFormData();
            webView.clearSslPreferences();
            webView.freeMemory();
            webView.clearView();
            webView.removeAllViews();
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
        System.out.println(buildJSUrl(jsMethod, "11", "1", 133));
    }


    public static void setCookie(WebView webView, String url, Map<String, String> values) {

        if (!OSUtils.hasLollipop_21()) {
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
        if (!OSUtils.hasLollipop_21()) {
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

    /**
     * 判断 WebView 是否可以返回
     *
     * @param webView
     * @return
     */
    public static boolean isCanGoBack(WebView webView) {
        return webView.canGoBack() && webView.copyBackForwardList().getSize() > 0;
    }


    /**
     * Gets encode post params.
     * encode this params
     *
     * @param params the params
     * @return the encode post params
     * @author Created by liuwenjie on 2018/08/06 13:46
     */
    public static String getEncodePostParams(Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> iterator = params.keySet().iterator();
        String key = "";
        String value = "";
        while (iterator.hasNext()) {
            key = iterator.next();
            value = params.get(key);
            sb.append(URLUtil.encode(key))
                    .append("=")
                    .append(URLUtil.encode(value));
            sb.append("&");
        }
        sb.deleteCharAt(sb.lastIndexOf("&"));
        return sb.toString();
    }

    public static void postUrl(WebView webView, String url, Map<String, String> params) {
        webView.postUrl(url, StrUtil.str2Bytes(getEncodePostParams(params)));
    }

    public static void postUrl(WebView webView, String url) {
        webView.postUrl(url, StrUtil.str2Bytes(getEncodePostParams(null)));
    }


//    @Nullable
//    public static Bitmap captureView(WebView webView) {
//        webView.capturePicture();
//        if (!OSUtils.hasJELLY_BEAN_MR2_18()) { // 4.3 及以下
//            Bitmap bitmap = null;
//            Picture picture = webView.capturePicture();
//            int width = picture.getWidth();
//            int height = picture.getHeight();
//            if (width > 0 && height > 0) {
//                bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//                Canvas canvas = new Canvas(bitmap);
//                picture.draw(canvas);
//            }
//            return bitmap;
//        }
//
//        return null;
//    }

}
