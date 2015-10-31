package com.example.roobab.juicebanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ListViewActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list);

        final String[][] values = {new String[]{}};

        getServer().getOrders(new Callback<String[]>() {
            @Override
            public void success(String[] strings, Response response) {
                System.out.println("********************sjkdhfkjsdh***" + response);
                values[0] = strings;
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("********************" + error);
            }
        });

//        String[] values = new String[] { "Android List View",
//                "Adapter implementation",
//                "Simple List View In Android",
//                "Create List View Android",
//                "Android Example",
//                "List View Source Code",
//                "List View Array Adapter",
//                "Android Example List View",
//                "ksjfhsjhdk",
//                "skjfks",
//                "sfdkjshkdjh",
//                "kshfkjshkdjfs",
//                "skjfhksjhfkshfl",
//                "shdkjfhskjf",
//                "skdfjhskjdfks",
//                "skjdhfksjhfks",
//                "njhgdkjhgkdfn"
//        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values[0]);

        listView.setAdapter(adapter);
    }

    private JuiceBannerApp getApp() {
        return (JuiceBannerApp) getApplication();
    }

    private JuiceServer getServer() {
        return getApp().getJuiceServer();
    }

}
