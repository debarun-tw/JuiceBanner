package com.example.roobab.juicebanner;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;

public interface JuiceServer {

    @GET("/api/orders/recentOrders")
    public void getOrders(Callback<ArrayList<Order>> cb);
}
