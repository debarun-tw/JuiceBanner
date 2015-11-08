package com.example.roobab.juicebanner;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class LastOrderAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Order> orders;

    public LastOrderAdapter(Context context, ArrayList<Order> orders) {
        this.context = context;
        this.orders = orders;
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Order getItem(int i) {
        return orders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View juiceItemView = view != null ? view : View.inflate(context, R.layout.juice_item, null);
        ((TextView) juiceItemView.findViewById(R.id.english_name)).setText(getItem(i).drinkName);
        return juiceItemView;
    }

    public void addAll(ArrayList<Order> newOrders) {
        orders.clear();
        orders.addAll(newOrders);
        notifyDataSetChanged();
    }
}
