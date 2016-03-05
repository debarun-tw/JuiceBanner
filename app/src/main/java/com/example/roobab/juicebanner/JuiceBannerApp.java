package com.example.roobab.juicebanner;


import android.app.Application;
import android.util.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executors;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.SecretKeySpec;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

public class JuiceBannerApp extends Application {

    //private static final String KANJUICE_SERVER_URL = "http://192.168.0.102:8083";
    private static final String KANJUICE_SERVER_URL = "http://10.132.127.212:8083";
    private RestAdapter restAdapter;
    private JuiceServer juiceServer;
    private static final String ENCRYPTION_KEY = "abcd1234";

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            setupRestAdapter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupRestAdapter() throws NoSuchAlgorithmException,
                                           UnsupportedEncodingException,
                                           NoSuchPaddingException, InvalidKeyException,
                                           ShortBufferException, BadPaddingException,
                IllegalBlockSizeException {

        String basicAuth = "admin:123abc123";
        byte[] input = basicAuth.getBytes("utf-8");

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] theDigest = md.digest(ENCRYPTION_KEY.getBytes("UTF-8"));
        SecretKeySpec skc = new SecretKeySpec(theDigest, "AES/ECB/PKCS5Padding");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, skc);

        byte[] cipherText = new byte[cipher.getOutputSize(input.length)];
        int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
        ctLength += cipher.doFinal(cipherText, ctLength);

        final String basic = Base64.encodeToString(cipherText, Base64.DEFAULT);

        restAdapter = new RestAdapter.Builder()
                .setEndpoint(KANJUICE_SERVER_URL)
                .setExecutors(Executors.newFixedThreadPool(5), Executors.newFixedThreadPool(3))
                .setLogLevel(RestAdapter.LogLevel.NONE)
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestFacade request) {
                        request.addHeader("Authorization", basic);
                    }
                })
                .build();
    }

    public JuiceServer getJuiceServer() {
        if (juiceServer == null) {
            juiceServer = restAdapter.create(JuiceServer.class);
        }
        return juiceServer;
    }

}
