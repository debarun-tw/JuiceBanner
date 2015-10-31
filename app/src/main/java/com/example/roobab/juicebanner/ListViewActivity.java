package com.example.roobab.juicebanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ListViewActivity extends AppCompatActivity {

    private ListView listView;
    private View noNetworkView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setUpViews();

        getServer().getOrders(new Callback<String[]>() {
            @Override
            public void success(final String[] strings, Response response) {
                ListViewActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ListViewActivity.this,
                                android.R.layout.simple_list_item_1, android.R.id.text1, strings);
                        listView.setAdapter(adapter);
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                ListViewActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        noNetworkView.setVisibility(View.VISIBLE);
                    }
                });
            }
        });
    }

    private void setUpViews() {
        listView = (ListView) findViewById(R.id.list);
        noNetworkView = findViewById(R.id.error);
        noNetworkView.setVisibility(View.INVISIBLE);
    }

    private JuiceBannerApp getApp() {
        return (JuiceBannerApp) getApplication();
    }

    private JuiceServer getServer() {
        return getApp().getJuiceServer();
    }

}
