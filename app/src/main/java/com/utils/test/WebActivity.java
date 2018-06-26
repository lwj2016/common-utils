package com.utils.test;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.lwj.utils.ResUtil;
import com.lwj.utils.ToastUtil;
import com.lwj.utils.WebViewUtil;
import com.lwj.utils.log.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created:2018/6/25
 * User：lwjfork
 * Email:lwjfork@gmail.com
 * Des:
 * ====================
 */

public class WebActivity extends Activity {

    WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        webView = new WebView(this);
        setContentView(webView);
        String url = ResUtil.getPathFromAssets("web.html");
        HashMap<String, String> cookie = new HashMap<>();
        cookie.put("uid", "111");
        cookie.put("name", "lwj");
        WebViewUtil.setCookie(webView, url, cookie);
        webView.loadUrl(url);
        WebSettings webSettings = webView.getSettings();
        WebViewUtil.setCommonSetting(webSettings, true);
        webView.addJavascriptInterface(this, "android");
        Map<String, String> result = WebViewUtil.getCookie(url);

        LogUtil.d("result %s", result.toString());

    }


    public int index = 0;

    @JavascriptInterface
    public void toastMessage(String msg) {
        index++;
        WebViewUtil.loadUrl(webView, "test", msg, index);
    }

    @JavascriptInterface
    public void toast(String result, int index) {
        ToastUtil.show(result);
        LogUtil.d("toast %d", index);
    }

    @Override
    protected void onPause() {
        super.onPause();
        WebViewUtil.pauseWebView(webView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        WebViewUtil.resumeWebView(webView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        WebViewUtil.destroyWebView(webView);
    }
}
