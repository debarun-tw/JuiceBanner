package com.example.roobab.juicebanner;


import android.content.Context;
import android.graphics.Color;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class LastOrderAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<OrderItem> orders;
    private com.example.roobab.juicebanner.deleteInterface deleteInterface;

    public LastOrderAdapter(Context context, ArrayList<OrderItem> orders, deleteInterface deleteInterface) {
        this.context = context;
        this.orders = orders;
        this.deleteInterface = deleteInterface;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final View juiceItemView = view != null ? view : View.inflate(context, R.layout.juice_item, null);
        ((TextView) juiceItemView.findViewById(R.id.english_name)).setText(getItem(i).drinkName + " - " + getItem(i).quantity);
        ((TextView) juiceItemView.findViewById(R.id.employee_name)).setText(getItem(i).employeeName);
        ((ImageView) juiceItemView.findViewById(R.id.juice_image)).setImageResource(getItem(i).imageResId);
        if(getItem(i).isSugarless)
            juiceItemView.findViewById(R.id.tile).setBackgroundColor(Color.rgb(173,255,47));
        else if(getItem(i).isFruit)
            juiceItemView.findViewById(R.id.tile).setBackgroundColor(Color.parseColor("#448AFF"));
        else
            juiceItemView.findViewById(R.id.tile).setBackgroundColor(Color.WHITE);

        juiceItemView.findViewById(R.id.delete_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteInterface.deleteOrder(getItem(i)._id, new Callback<Response>() {
                    @Override
                    public void success(Response response, Response response2) {
                        if (response.getStatus() == 200) {
                            Looper.prepare();
                            Toast.makeText(context, "Successfully deleted the order", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Looper.prepare();
                            Toast.makeText(context, "Some internal error occured. Please try again", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void failure(RetrofitError error) {
                        Log.d("ERROR",error.toString());
                    }
                });
            }
        });

        return juiceItemView;
    }

    public void addAll(ArrayList<OrderItem> newOrders) {
        orders.clear();
        orders.addAll(newOrders);
        notifyDataSetChanged();
    }
}
