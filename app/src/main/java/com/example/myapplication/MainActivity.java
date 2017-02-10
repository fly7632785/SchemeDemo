package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private String url = "http://wpa.qq.com/msgrd?v=3&uin=522648467&site=qq&menu=yes";
    private String toMeMain = "jafir://main.app?key1=shceme还可以通过参数传值      获取到值啦";
    private String toMeLogin = "jafir://login.app";
    /**
     * 经测试 现在新版本的微信基本都已经不能通过scheme跳到对应的界面了
     */
    private String toWixin = "weixin://";
    private String localUrl = "file:///android_asset/index.html";
    private String successUrl = "file:///android_asset/success.html";
    private String localMainUrl = "file:///android_asset/main.html";
    private String localLoginUrl = "file:///android_asset/login.html";

    private WebView webView;
    private WebView webView1;
    private WebView webView2;

    /**
     * 通过不同的scheme打开本地app的不同界面
     *
     * @param v
     */
    public void openMain(View v) {
        webView.loadUrl(toMeMain);
    }

    public void openQQ(View v) {
        webView.loadUrl(url);
    }

    public void openLogin(View v) {
        webView.loadUrl(toMeLogin);
    }

    public void openWx(View v) {
        webView.loadUrl(toWixin);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        WebViewClient webViewClient1 = new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                view.loadUrl(request.getUrl().toString());
//                Log.d("deug", " shouldOverrideUrlLoading url:" + request.getUrl().toString());
//                return true;
//            }
//
//            @Override
//            public WebResourceResponse shouldInterceptRequest(final WebView view, WebResourceRequest request) {
//                String url = request.getUrl().toString();
//                Log.d("deug", "shouldInterceptRequest url:" + request.getUrl().toString());
//                if (url.startsWith("http") || url.startsWith("https")) { //http和https协议开头的执行正常的流程
//                    return super.shouldInterceptRequest(view, request);
//                } else {  //其他的URL则会开启一个Acitity然后去调用原生APP
//                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//                    startActivity(in);
//                    view.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            view.loadUrl(localUrl);
//                        }
//                    });
//                    return null;
//                }
//            }
//
//        };

        WebViewClient webViewClient = new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(final WebView view, String url) {
                if (url.startsWith("http") || url.startsWith("https")) { //http和https协议开头的执行正常的流程
                    return super.shouldInterceptRequest(view, url);
                } else {  //其他的URL则会开启一个Acitity然后去调用原生APP
                    Intent in = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    startActivity(in);
                    view.post(new Runnable() {
                        @Override
                        public void run() {
                            view.loadUrl(successUrl);
                        }
                    });
                    return null;
                }
            }

        };


        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(webViewClient);
        webView1 = (WebView) findViewById(R.id.main_webview);
        webView1.getSettings().setJavaScriptEnabled(true);
        webView1.setWebViewClient(webViewClient);
        webView2 = (WebView) findViewById(R.id.login_webview);
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.setWebViewClient(webViewClient);
        webView.loadUrl(localUrl);
        webView1.loadUrl(localMainUrl);
        webView2.loadUrl(localLoginUrl);


    }
}
