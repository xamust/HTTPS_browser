package ru.airkhv.st;

import android.net.http.SslCertificate;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

/**
 * Created by svk on 01.07.2015.
 */
public class MyHttpsConnection {

    InputStream inter = null;
    SslCertificate mysslCertificate;
    KeyStore trustStore;
    KeyStore keyStore;
    SSLContext context;
    String alias;
    TrustManagerFactory trustManagerFactory;
    KeyManagerFactory keyManagerFactory;
    String CRT_location = "/airkhv-ca.crt";
    String PFX_location = "/1234.pfx";
    String PFX_password = "1234";
    HttpsURLConnection  connection;
    URL urlIX;
    InputStream is;
    InputStream in;
    CertificateFactory cf;
    public String[] spliter_content;
    public InputStream MyHttpsConnection(String url) {
        try {

            CRT_location = Environment.getExternalStorageDirectory() + CRT_location;
            PFX_location = Environment.getExternalStorageDirectory() + PFX_location;
            trustStore = KeyStore.getInstance("bks");
            trustStore.load(null, null);
            cf = CertificateFactory.getInstance("X509");
            in = new BufferedInputStream(new FileInputStream(CRT_location));
            X509Certificate cert = (X509Certificate) cf.generateCertificate(in);
            alias = cert.getSubjectX500Principal().getName();
            trustStore.setCertificateEntry(alias, cert);
            keyStore = KeyStore.getInstance("pkcs12");
            keyStore.load(null, null);
            is = new FileInputStream(PFX_location);
            keyStore.load(is, PFX_password.toCharArray());
            trustManagerFactory = TrustManagerFactory.getInstance("X509");
            trustManagerFactory.init(trustStore);
            keyManagerFactory = KeyManagerFactory.getInstance("X509");
            keyManagerFactory.init(keyStore, null);
            mysslCertificate = new SslCertificate(cert);
            if (Build.VERSION.SDK_INT < 16) {
                context = SSLContext.getInstance("TLSv1");
            } else {
                context = SSLContext.getInstance("TLSv1.2");
            }
            context.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), new SecureRandom());
            CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
            urlIX = new URL(url);
            connection = (HttpsURLConnection) urlIX.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; WOW64; Trident/6.0) ");
            connection.setSSLSocketFactory(context.getSocketFactory());
            connection.setDoInput(true);
            spliter_content = connection.getContentType().split("; ");
            inter = new BufferedInputStream(connection.getInputStream());
        } catch (Exception e) {
            Log.d("BEER", e.toString() + " MyHttpsConnection ERROR!!!");
        }
        CRT_location = "/airkhv-ca.crt";
        PFX_location = "/1234.pfx";

        return inter;
    }
}
