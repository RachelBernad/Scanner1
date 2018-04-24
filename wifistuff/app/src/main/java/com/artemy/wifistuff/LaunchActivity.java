package com.artemy.wifistuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LaunchActivity extends AppCompatActivity implements View.OnClickListener {

    Button scan;
    Button list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        scan = (Button) findViewById(R.id.scanbtn);
        list = (Button) findViewById(R.id.listbtn);
        scan.setOnClickListener(this);
        list.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(scan==v)
        {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);

        }
        if(list==v)
        {
            Intent intent=new Intent(this,ListActivity.class);
            startActivity(intent);

        }

    }


}
