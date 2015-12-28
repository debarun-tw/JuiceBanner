package com.example.roobab.juicebanner;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class MenuActivity extends Activity {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_total_count:
                Intent intent = new Intent(this, SummaryActivity.class);
                startActivity(intent);
                return true;

            case R.id.availability:
                Intent availabilityIntent = new Intent(this, AdminActivity.class);
                startActivity(availabilityIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
