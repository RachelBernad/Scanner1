package com.artemy.wifistuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }
    public void WifiManager(View view)
    {
        Intent intent = new Intent(this,WifiMenuActivity.class);
        startActivity(intent);
    }

    public void UserManager(View view)
    {
        Intent intent = new Intent(this,UserMenuActivity.class);
        startActivity(intent);
    }


}
