package com.example.roobab.juicebanner;

import retrofit.Callback;
import retrofit.http.GET;

public interface JuiceServer {

    @GET("/api/orders/recentOrders")
    public void getOrders(Callback<String[]> cb);
}
