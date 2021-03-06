package com.example.roobab.juicebanner;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface JuiceServer {

    @GET("/api/orders/recentOrders")
    public void getOrders(Callback<ArrayList<Order>> cb);

    @GET("/api/orders/summary")
    public void getSummary(Callback<ArrayList<Summary>> cb);

    @POST("/api/beverages/updateWithUpsert")
    void updateJuice(@Body TypedJsonString body, Callback<Response> cb);

    @GET("/api/beverages/juices")
    public void getJuices(Callback<List<Juice>> cb);

    @DELETE("/api/orders/{id}")
    public void deleteOrder(@Path("id") String _id, Callback<Response> cb);
}
