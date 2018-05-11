package com.artemy.wifistuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class UserMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
    }

    public void exit_user_menu(View view)
    {
        finish();
    }

    public void show_all_users(View view)
    {
        Intent intent = new Intent(this,ListAllUsersActivity.class);
        startActivity(intent);
    }

    public void add_user(View view)
    {
        Intent intent = new Intent(this,AddUserActivity.class);
        startActivity(intent);
    }


}
