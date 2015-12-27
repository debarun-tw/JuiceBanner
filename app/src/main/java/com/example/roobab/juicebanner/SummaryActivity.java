package com.example.roobab.juicebanner;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class SummaryActivity extends MenuActivity {
    private TableLayout tableView;
    TableRow tr;
    private TextView label;
    private LinearLayout Ll;
    private LinearLayout.LayoutParams params;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.summary);

        setUpViews();
        fetchMenu();
    }


    private void setUpViews() {
        tableView = (TableLayout) findViewById(R.id.summary_table);
    }


    private void fetchMenu() {
        getServer().getSummary(new Callback<ArrayList<Summary>>() {
            @Override
            public void success(final ArrayList<Summary> summaries, Response response) {
                SummaryActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        onSummaryReady(summaries);
                    }
                });
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    private JuiceBannerApp getApp() {
        return (JuiceBannerApp) getApplication();
    }

    private JuiceServer getServer() {
        return getApp().getJuiceServer();
    }

    @SuppressWarnings({"rawtypes"})
    public void onSummaryReady(ArrayList<Summary> summaries) {
        addHeader();
        int totalCoffeeTeaCount = 0;
        for (Summary summary : summaries) {
            if(!summary.getName().equals("CTL")) {
                createRow(summary.getName(), summary.getCount());
                addRowToTable();
            }
            else {
                totalCoffeeTeaCount = Integer.parseInt(summary.getCount());
            }
        }
        createRow("Coffee/Tea", "" + totalCoffeeTeaCount);
        addRowToTable();
    }

    private void createRow(String name, String quantity) {
        tr = new TableRow(this);
        label = new TextView(this);
        label.setTextSize(25);
        label.setText(name);
        label.setTextColor(Color.parseColor("#FFFFFF"));

        label.setLayoutParams(new TableRow.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        label.setPadding(15, 15, 175, 15);
        setBackgroundColor(name, label);

        LinearLayout Ll = new LinearLayout(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 5, 5, 5);

        Ll.addView(label, params);
        tr.addView(Ll);
        TextView place = new TextView(this);
        place.setText(quantity);
        place.setTextColor(Color.parseColor("#FFFFFF"));
        place.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        setBackgroundColor(name, place);
        place.setTextSize(25);
        place.setPadding(15, 15, 175, 15);
        Ll = new LinearLayout(this);
        params = new LinearLayout.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 5, 5, 5);

        Ll.addView(place, params);
        tr.addView(Ll);
    }

    private void setBackgroundColor(String name, TextView view) {
        if (name.equals("Total Juice") || name.equals("Coffee/Tea")) {
            view.setBackgroundColor(Color.parseColor("#00897B"));
        } else {
            view.setBackgroundColor(Color.GRAY);
        }
    }

    private void addRowToTable() {
        tableView.addView(tr, new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));
    }

    void addHeader() {

        tr = new TableRow(this);

        TextView label = new TextView(this);
        label.setText(R.string.label_juice_name);
        label.setTextSize(25);
        label.setTextColor(Color.parseColor("#FFFFFF"));
        label.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        label.setPadding(15, 15, 100, 15);
        label.setGravity(Gravity.CENTER_VERTICAL);
        label.setBackgroundColor(Color.parseColor("#2196F3"));
        Ll = new LinearLayout(this);
        params = new LinearLayout.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 5, 5, 5);
        Ll.addView(label, params);
        tr.addView((View) Ll);

        TextView place = new TextView(this);
        place.setTextSize(25);
        place.setTextColor(Color.parseColor("#FFFFFF"));
        place.setText(R.string.label_qunatity);
        place.setLayoutParams(new TableLayout.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT));
        place.setPadding(14, 15, 100, 15);
        place.setBackgroundColor(Color.parseColor("#2196F3"));
        Ll = new LinearLayout(this);
        params = new LinearLayout.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 5, 5, 5);
        Ll.addView(place, params);
        tr.addView((View) Ll);

        addRowToTable();
    }
}
