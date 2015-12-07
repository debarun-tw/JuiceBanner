package com.example.roobab.juicebanner;

import com.google.gson.Gson;

public class Juice {
    public String name;
    public boolean available;
    public int imageId;
    public int kanId;

    public String asJson() {
        return new Gson().toJson(this);
    }
}
