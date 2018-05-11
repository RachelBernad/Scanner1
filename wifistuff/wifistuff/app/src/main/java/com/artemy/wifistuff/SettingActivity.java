package com.artemy.wifistuff;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SettingActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    SharedPreferences sp;
    Switch sw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        sp=getSharedPreferences("details",0);
        sw = (Switch)findViewById(R.id.sw);
        sw.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("on",b);
            editor.commit();
    }
}
