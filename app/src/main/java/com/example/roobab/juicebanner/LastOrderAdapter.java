package com.example.roobab.juicebanner;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class LastOrderAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<OrderItem> orders;

    public LastOrderAdapter(Context context, ArrayList<OrderItem> orders) {
        this.context = context;
        this.orders = orders;
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public OrderItem getItem(int i) {
        return orders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View juiceItemView = view != null ? view : View.inflate(context, R.layout.juice_item, null);
        ((TextView) juiceItemView.findViewById(R.id.english_name)).setText(getItem(i).drinkName + " - " + getItem(i).quantity);
        ((TextView) juiceItemView.findViewById(R.id.employee_name)).setText(getItem(i).employeeName);
        ((ImageView) juiceItemView.findViewById(R.id.juice_image)).setImageResource(getItem(i).imageResId);
        return juiceItemView;
    }

    public void addAll(ArrayList<OrderItem> newOrders) {
        orders.clear();
        orders.addAll(newOrders);
        notifyDataSetChanged();
    }
}
