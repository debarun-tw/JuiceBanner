package com.example.roobab.juicebanner;


import android.app.Application;

import retrofit.RestAdapter;

public class JuiceBannerApp extends Application {

    //private static final String KANJUICE_SERVER_URL = "http://192.168.0.102:8083";
    private static final String KANJUICE_SERVER_URL = "http://10.132.126.126:8083";
    private RestAdapter restAdapter;
    private JuiceServer juiceServer;

    @Override
    public void onCreate() {
        super.onCreate();

        setupRestAdapter();
    }

    private void setupRestAdapter() {
        restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(KANJUICE_SERVER_URL)
                .build();
    }

    public JuiceServer getJuiceServer() {
        if (juiceServer == null) {
            juiceServer = restAdapter.create(JuiceServer.class);
        }
        return juiceServer;
    }

}
