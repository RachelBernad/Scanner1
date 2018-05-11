package com.artemy.wifistuff.rachel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.artemy.wifistuff.ListActivity;
import com.artemy.wifistuff.MainMenuActivity;
import com.artemy.wifistuff.R;
import com.artemy.wifistuff.SettingActivity;

public class LaunchActivity extends AppCompatActivity implements View.OnClickListener {

    Button scan;
    Button list,sqlite,btnsetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        scan = (Button) findViewById(R.id.scanbtn);
        list = (Button) findViewById(R.id.listbtn);
        scan.setOnClickListener(this);
        list.setOnClickListener(this);

        sqlite = (Button) findViewById(R.id.sqlite);
        btnsetting = (Button) findViewById(R.id.setting);
        sqlite.setOnClickListener(this);
        btnsetting.setOnClickListener(this);

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
        else if(sqlite==v)
        {
            Intent intent=new Intent(this,MainMenuActivity.class);
            startActivity(intent);

        }
        if(btnsetting==v)
        {
            Intent intent=new Intent(this,SettingActivity.class);
            startActivity(intent);

        }

    }


}
