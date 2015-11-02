package com.example.roobab.juicebanner;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ListViewActivity extends AppCompatActivity {

    private static final int MSG_POLL = 101;
    private ListView listView;
    private View noNetworkView;

    Handler H = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case MSG_POLL:
                    periodicOrderGetter();
                    sendEmptyMessageDelayed(MSG_POLL, 5000);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setUpViews();

        H.sendEmptyMessage(MSG_POLL);

    }

    private void periodicOrderGetter() {
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
