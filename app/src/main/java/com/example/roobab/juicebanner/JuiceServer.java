package com.example.roobab.juicebanner;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

public interface JuiceServer {

    @GET("/api/orders/recent")
    public void getOrders(Callback<String[]> cb);
}
