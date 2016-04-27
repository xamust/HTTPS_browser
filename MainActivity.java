package ru.airkhv.st;

import android.annotation.SuppressLint;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class MainActivity extends ActionBarActivity {

    private MyWebView mWebView;
    String url2 = "http://site";

    public void onCreate(Bundle savedInstanceState) {
        //full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mWebView = (MyWebView) findViewById(R.id.webView);

       // mWebView.setWebViewClient(new MyWebViewClient());
        mWebView.setWebChromeClient(new MyWebChromeClient());

        //test
        mWebView.setWebViewClient(new Test());

        // включаем поддержку JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        // указываем страницу загрузки
        mWebView.getSettings().setDomStorageEnabled(true);
        //cache
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
      //
      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            mWebView.getSettings().setAllowFileAccessFromFileURLs(true);
            mWebView.getSettings().setAllowUniversalAccessFromFileURLs(true);
        }*/

        try {
            Log.d("BEER","START");
            mWebView.loadUrl(url2);


        } catch (Exception e) {
            Log.d("BEER","Exception MainActivity " + e.toString());
        }
    }
}


