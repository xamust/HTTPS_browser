package ru.airkhv.st;

import android.util.Log;


import java.io.BufferedInputStream;
import java.io.InputStream;


import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by svk on 01.07.2015.
 */
public class MyHttpConnection {
    public InputStream MyHttpConnection(String url) {
        InputStream inputHttp = null;
        try {


            URL urlIX = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlIX.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0) ");
            connection.setDoInput(true);
            connection.setDoInput(true);
            inputHttp = new BufferedInputStream(connection.getInputStream());


        } catch (Exception ecr) {
            Log.d("BEER", ecr.toString() + " MyHttpConnection ERROR!!!");
        }
        return inputHttp;
    }
}
