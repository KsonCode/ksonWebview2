package com.example.kson.ksonwebview;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webview);
        initSettings();
        initData();
    }

    private void initData() {
//        webView.loadUrl("http://www.baidu.com");
        //加载assets下的html文件
//        webView.loadUrl("file:///android_asset/hellojs.html");
        webView.loadUrl("http://www.zhaoapi.cn/version/hellojs.html");

        //添加js和android的映射关系，第一个参数是对象，第二个参数是对象引用名称
        webView.addJavascriptInterface(new ChouJiangBean(),"test");
    }

    private void initSettings() {
        //设置支持js交互
        webView.getSettings().setJavaScriptEnabled(true);
        //使用android内置浏览器加载数据
        webView.setWebViewClient(new WebViewClient());
        // 由于设置了弹窗检验调用结果,所以需要支持js对话框
        // webview只是载体，内容的渲染需要使用webviewChromClient类去实现
        // 通过设置WebChromeClient对象处理JavaScript的对话框
        //设置响应js 的Alert()函数
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public boolean onJsAlert(WebView view, String url, String message, final JsResult result) {
                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setTitle("Alert");
                b.setMessage(message);
                b.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        result.confirm();
                    }
                });
                b.setCancelable(false);
                b.create().show();
                return true;
            }

        });
    }

    /**
     * android代码调用js代码
     * @param view
     */
    public void androidtojs(View view) {

        webView.loadUrl("javascript:callJS2('{}')");
    }


}
