package com.bc.bao.html;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity implements View.OnClickListener{
    private WebView mWV;
    private Button bt;
    private WebAppInfo webAppInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWV= (WebView) findViewById(R.id.wv);
        bt= (Button) findViewById(R.id.bt);
        bt.setOnClickListener(this);
        mWV.loadUrl("file:///android_asset/index.html");
        mWV.getSettings().setJavaScriptEnabled(true);
        webAppInfo=new WebAppInfo(this);
        mWV.addJavascriptInterface(webAppInfo,"app");
    }

    @Override
    public void onClick(View v) {
        webAppInfo.showName("输入内容");
    }

    class WebAppInfo{
        private Context context;
        public WebAppInfo(Context context){
            this.context=context;
        }
        @JavascriptInterface
        public void sayHello(String str){
            Toast.makeText(context,"="+str,Toast.LENGTH_LONG).show();
        }
        public void showName(String name){
            mWV.loadUrl("javascript:showName('" + name + "')");
        }
    }



}
