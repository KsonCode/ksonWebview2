package com.example.kson.ksonwebview;

import android.webkit.JavascriptInterface;

public class ChouJiangBean {

    @JavascriptInterface
    public void hello(String s){
        System.out.println("========"+s);
    }
    @JavascriptInterface
    public void hello2(){
        System.out.println("我收到了js的数据");
    }
    @JavascriptInterface
    public void hello3(){
        System.out.println("我收到了js的数据");
    }
}
