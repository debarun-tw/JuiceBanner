package com.example.roobab.juicebanner;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class ListViewActivity extends Activity {

    private static final int MSG_POLL = 101;
    private static final int MSG_REFRESH = 102;
    private static final int MSG_ERROR = 103;
    public static final int POLL_DELAY_MILLIS = 5000;
    private static final String TAG = "JuiceBanner";

    private ListView listView;
    private View noNetworkView;
    public deleteInterface deleteInterface= new deleteInterface() {
        @Override
        public void deleteOrder(String _id, Callback<Response> callback) {
            getServer().deleteOrder(_id,callback);
        }
    };

    Handler H = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch(msg.what) {
                case MSG_POLL:
                    periodicOrderGetter();
                    sendEmptyMessageDelayed(MSG_POLL, POLL_DELAY_MILLIS);
                    break;

                case MSG_REFRESH:
                    ArrayList<OrderItem> orderItems = decorateOrders((ArrayList<Order>) msg.obj);
                    adapter.addAll(orderItems);
                    break;

                case MSG_ERROR:
                    noNetworkView.setVisibility(View.VISIBLE);
                    break;
            }
        }
    };
    private LastOrderAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setUpViews();

        H.sendEmptyMessage(MSG_POLL);

    }

    private void periodicOrderGetter() {
        getServer().getOrders(new Callback<ArrayList<Order>>() {
            @Override
            public void success(ArrayList<Order> orders, Response response) {
                H.obtainMessage(MSG_REFRESH, orders).sendToTarget();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "error : " + error);
                H.sendEmptyMessage(MSG_ERROR);
            }
        });
    }

    private ArrayList<OrderItem> decorateOrders(ArrayList<Order> orders) {
        ArrayList<OrderItem> decoratedOrders = new ArrayList<>();
        for(Order order : orders) {
            decoratedOrders.add(new OrderItem(order.drinkName, order.employeeName, order.quantity,
                    OrderDecorator.matchImage(order.drinkName), order.isSugarless, order._id));
        }
        return decoratedOrders;
    }

    private void setUpViews() {
        listView = (ListView) findViewById(R.id.list_of_orders);
        adapter = new LastOrderAdapter(this, new ArrayList<OrderItem>(),deleteInterface );
        listView.setAdapter(adapter);

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
