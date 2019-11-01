package com.example.bailang20191029;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Main2Activity extends BaseActivity {


    private WebView webView;

    @Override
    protected void initView() {
        webView = findViewById(R.id.webview01);

    }

    @Override
    protected void initData() {
        getData();
    }

    private void getData() {
        Intent intent = getIntent();
        final String key = intent.getStringExtra("key");
        webView.loadUrl(key);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(key);
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                Log.e("AGT","开始");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.e("AGT","完成");
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                Log.e("AGT","标题是："+title);
            }

        });
    }

    @Override
    protected int layoutoId() {
        return R.layout.activity_main2;
    }
}
