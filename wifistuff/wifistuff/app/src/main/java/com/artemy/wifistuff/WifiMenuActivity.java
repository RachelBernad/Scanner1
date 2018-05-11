package com.artemy.wifistuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class WifiMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_menu);
    }

    public void exit_wifi_menu(View view)
    {
        finish();
    }

    public void show_all_wifies(View view)
    {
        Intent intent = new Intent(this,ListAllWifiesActivity.class);
        startActivity(intent);
    }

    public void add_item(View view)
    {
        Intent intent = new Intent(this,AddWifiActivity.class);
        startActivity(intent);
    }

}
