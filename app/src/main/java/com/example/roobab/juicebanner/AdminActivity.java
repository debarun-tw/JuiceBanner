package com.example.roobab.juicebanner;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class AdminActivity extends MenuActivity implements CompoundButton.OnCheckedChangeListener {

    private static final String TAG = "Admin";
    private ListAvailAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin);

        setupViews();
        fetchMenu();
    }

    private JuiceBannerApp getApp() {
        return (JuiceBannerApp) getApplication();
    }

    private JuiceServer getServer() {
        return getApp().getJuiceServer();
    }


    private void setupViews() {
        ListView list = (ListView) findViewById(R.id.list);
        adapter = new ListAvailAdapter(this, new ArrayList<Juice>());
        list.setAdapter(adapter);
    }

    private void setJuiceAvailability(final Juice juice) {
        Log.d(TAG, "setJuiceAvailability: " + juice.asJson());
        getServer().updateJuice(new TypedJsonString(juice.asJson()), new Callback<Response>() {

            @Override
            public void success(Response response, Response response2) {
                Log.d(TAG, "Updated juice availibility");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "failed to update  juice availibility");
                juice.available = !juice.available;
            }
        });
    }

    private void fetchMenu() {
        getServer().getJuices(new Callback<List<Juice>>() {
            @Override
            public void success(final List<Juice> juices, Response response) {
                AdminActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.addAll(juices);
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Failed to fetch menu list : " + error);


            }
        });
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Juice juice = (Juice) buttonView.getTag();
        if (juice != null) {
            juice.available = !juice.available;
            setJuiceAvailability(juice);
        }
    }

    public static class ListAvailAdapter extends BaseAdapter {

        private AdminActivity adminActivity;
        private final List<Juice> juices;
        private LayoutInflater inflater;

        public ListAvailAdapter(AdminActivity adminActivity, List<Juice> juices) {
            this.adminActivity = adminActivity;
            this.juices = juices;
            inflater = (LayoutInflater) adminActivity.getSystemService(LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return juices.size();
        }

        @Override
        public Object getItem(int position) {
            return juices.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = inflater.inflate(R.layout.juice_avail_item, parent, false);
            bind(view, (Juice) getItem(position));
            return view;
        }

        private void bind(View view, final Juice juice) {
            TextView titleView = (TextView) view.findViewById(R.id.title);
            titleView.setText(juice.name);

            TextView kanTitleView = (TextView) view.findViewById(R.id.title_kan);
            kanTitleView.setText(JuiceDecorator.matchKannadaName(juice.name));

            CheckBox availabilityView = (CheckBox) view.findViewById(R.id.availability);
            availabilityView.setChecked(juice.available);
            availabilityView.setOnCheckedChangeListener(adminActivity);
            availabilityView.setTag(juice);

            view.setTag(juice);
        }

        public void addAll(List<Juice> juices) {
            this.juices.addAll(juices);
            notifyDataSetChanged();
        }
    }
}
