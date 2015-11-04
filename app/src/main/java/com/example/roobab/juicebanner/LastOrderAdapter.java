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
    private ArrayList<String> juices;

    public LastOrderAdapter(Context context, ArrayList<String> strings) {
        this.context = context;
        juices = strings;
    }

    @Override
    public int getCount() {
        return juices.size();
    }

    @Override
    public Object getItem(int i) {
        return juices.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View juiceItemView = view != null ? view : View.inflate(context, R.layout.juice_item, null);
        ((TextView) juiceItemView.findViewById(R.id.english_name)).setText((String) getItem(i));
        return juiceItemView;
    }

    public void addAll(String[] newJuices) {
        juices.clear();
        juices.addAll(Arrays.asList(newJuices));
        notifyDataSetChanged();
    }
}
