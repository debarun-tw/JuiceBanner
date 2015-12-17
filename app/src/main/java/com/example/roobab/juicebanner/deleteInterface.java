package com.example.roobab.juicebanner;

import retrofit.Callback;
import retrofit.client.Response;

public interface deleteInterface{
    public void deleteOrder(String _id, Callback<Response> callback);
}
