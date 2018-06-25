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
        webView.loadUrl(ResUtil.getPathFromAssets("web.html"));
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(this, "android");
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
}
