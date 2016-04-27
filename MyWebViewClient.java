package ru.airkhv.st;

import android.graphics.Bitmap;

import android.os.Message;
import android.util.Log;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.webkit.HttpAuthHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by svk on 30.06.2015.
 */
public class MyWebViewClient extends WebViewClient {

    @Override
    public WebResourceResponse shouldInterceptRequest (WebView view, String url){

        String[] exp = url.split("\\.");
        String[] run = url.split(":");
        String http = "http";

       //temp
        String css = "css";
        String js = "js";
        String ico = "ico";

        InputStream inputWeb = null;

        Log.d("BEER", url + " " + view.toString() +  " shouldInterceptRequest");

        //?????????!!!(??????)
        String jqurl = "://ma.airkhv.ru/js/jquery-1.7.2.min.js";
        if(url.compareTo("https"+jqurl) == 0 | url.compareTo("http"+jqurl) == 0) {url = "http://code.jquery.com/jquery-1.7.2.min.js";}
        //?????????!!!(?????)


        if(run[0].compareTo(http) == 0)
        {

           // inputWeb = new MyHttpConnection().MyHttpConnection(url);
          //  return null;  /* insert in to programm*/
            //for test...
          HttpURLConnection connection = null;
            URL urlIX;
            try {

                urlIX = new URL(url);
                connection = (HttpURLConnection) urlIX.openConnection();
                connection.setDoOutput(true);
                connection.setChunkedStreamingMode(0);
                connection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0) ");
           //     connection.setReadTimeout(500); //milisec
          //      connection.setConnectTimeout(500);//milisec
          //      connection.setRequestMethod("POST");
           //     connection.setDoInput(true);
           //     connection.setDoInput(true);
        //        connection.connect();

                Log.d("BEER", connection.getRequestMethod() +
                        " " +connection.getResponseMessage() +
                        " " + connection.getResponseCode() +
                        " " + connection.getReadTimeout() +
                        " " + connection.getContentType());
                        // use for content filtering (go down)
                inputWeb = new BufferedInputStream(connection.getInputStream());


            } catch (Exception ecr) {
                Log.d("BEER", ecr.toString() + " MyHttpConnection ERROR!!!");
            }
          /*  finally {

                try {
                    connection.disconnect();

                } catch (Exception e) {
                    Log.d("BEER", e.toString() + " MyHttpConnection ERROR!!!");
                }
            }*/
        }
        else
        {
            inputWeb = new MyHttpsConnection().MyHttpsConnection(url);

        }
//????? ?? ????? "??????????" ????? ?? ??????????? (oldfuck)
        if (exp[exp.length - 1].compareTo(css) == 0){return new WebResourceResponse("text/css",null,inputWeb); }
        else if(exp[exp.length - 1].compareTo(js) == 0){return new WebResourceResponse("application/javascript",null,inputWeb);}
        else if(exp[exp.length - 1].compareTo(ico) == 0){return new WebResourceResponse("image/x-icon",null,inputWeb);}
        else {return new WebResourceResponse("text/html","UTF-8",inputWeb);}


    }
 /*   @Override
    public boolean shouldOverrideUrlLoading( WebView view, String url ){Log.d("BEER", url + " shouldOverrideUrlLoading");view.loadUrl(url);return false;}*/
    @Override
    public void onPageStarted (WebView view, String url, Bitmap favicon) {
        Log.d("BEER", url + " " + view.getUrl() + " onPageStarted");
    }
    @Override
    public void onLoadResource (WebView view, String url) {Log.d("BEER", url + " onLoadResource");}
    @Override
    public void onFormResubmission (WebView view, Message dontResend, Message resend) {Log.d("BEER", resend + " " + dontResend + " onFormResubmission");}
    @Override
    public void onScaleChanged (WebView view, float oldScale, float newScale){Log.d("BEER", oldScale + " " + newScale + " onScaleChanged");}
    @Override
    public void onUnhandledKeyEvent (WebView view, KeyEvent event){Log.d("BEER", event + " onUnhandledKeyEvent_depre");}
    @Override
    public void onPageFinished(WebView view, String url) {Log.d("BEER", url + " onPageFinished");}

    @Override
    public void onUnhandledInputEvent(WebView view, InputEvent event) {
        Log.d("BEER", event + " onUnhandledInputEvent");
    }


}
