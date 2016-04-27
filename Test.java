package ru.airkhv.st;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by svk on 10.07.2015.
 */
public class Test extends WebViewClient {

    @Override
    public WebResourceResponse shouldInterceptRequest (WebView view, String url){

        String[] exp = url.split("\\.");
        String[] run = url.split(":");
        String http = "http";
        String CONTENT_TYPE_SPLIT = ";";
        String patternString = "(charset=)(.+)";
        Pattern CONTENT_TYPE_PATTERN = Pattern.compile(patternString);
        //temp
        String css = "css";
        String js = "js";
        String ico = "ico";

        String mimeType = null;
        String encoding = null;

        InputStream inputWeb = null;

   //     Log.d("BEER", url + " " + view.toString() +  " shouldInterceptRequest");

        //?????????!!!(??????)
        String jqurl = "://ma.airkhv.ru/js/jquery-1.7.2.min.js";
        if(url.compareTo("https"+jqurl) == 0 | url.compareTo("http"+jqurl) == 0) {url = "http://code.jquery.com/jquery-1.7.2.min.js";}
        //?????????!!!(?????)

      //  Log.d("BEER", url + " onLoadResource");
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
            inputWeb = new BufferedInputStream(connection.getInputStream());
            mimeType = connection.getContentType();
            encoding = connection.getContentEncoding();
         /*   Log.d("BEER", connection.getRequestMethod() +
                    " " +connection.getResponseMessage() +
                    " " + connection.getResponseCode() +
                    " " + connection.getReadTimeout() +
                    " " + connection.getContentType() +
                    " " + connection.getContentEncoding());*/
            // use for content filtering (go down)
            Log.d("BEER","mimeType " + mimeType + ", encoding " + encoding);
            if(mimeType != null && mimeType.contains(CONTENT_TYPE_SPLIT)) {
                String[] split = mimeType.split(CONTENT_TYPE_SPLIT);
                mimeType = split[0];

           //     Log.d("BEER","mimeType " + mimeType + " split " + split[1]);
                Matcher matcher = CONTENT_TYPE_PATTERN.matcher(split[1]);

                if(matcher.find()) {
                    encoding = matcher.group(2);
                    Log.d("BEER","encoding " + encoding);
                }
            }



        } catch (Exception ecr) {
            Log.d("BEER", ecr.toString() + " MyHttpConnection ERROR!!!");
        }
        Log.d("BEER","Response "+ mimeType + " " + encoding);
     //   return new WebResourceResponse(mimeType,encoding,inputWeb);
        return null;
    }
}
