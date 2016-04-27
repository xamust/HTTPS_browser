package ru.airkhv.st;

import android.text.Layout;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by svk on 03.07.2015.
 */
public class MyWebChromeClient extends WebChromeClient{

    @Override
    public void onProgressChanged(WebView view, int newProgress){
        Log.d("BEER", newProgress + " onProgressChanged");
    }
}
