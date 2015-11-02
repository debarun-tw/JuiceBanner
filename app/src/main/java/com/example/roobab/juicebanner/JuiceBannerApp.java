package com.example.roobab.juicebanner;


import android.app.Application;

import retrofit.RestAdapter;

public class JuiceBannerApp extends Application {

    private static final String KANJUICE_SERVER_URL = "http://10.4.23.127:8083";
    private RestAdapter restAdapter;
    private JuiceServer juiceServer;

    @Override
    public void onCreate() {
        super.onCreate();

        setupRestAdapter();
    }

    private void setupRestAdapter() {
        restAdapter = new RestAdapter.Builder()
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
