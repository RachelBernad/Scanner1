package com.artemy.wifistuff;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddWifiActivity extends AppCompatActivity {

    EditText wifi_name,wifi_pass,wifi_pwtype,bssid,mongo_id;
    WifiHelper wifiHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_wifi);
        wifiHelper = new WifiHelper(this);
        wifi_name = (EditText)this.findViewById(R.id.edit_text_name_of_the_wifi);
        wifi_pass = (EditText)this.findViewById(R.id.edit_text_wifi_pass);
        wifi_pwtype = (EditText)this.findViewById(R.id.edit_text_wifi_pwtype);
        bssid = (EditText)this.findViewById(R.id.edit_text_wifi_bssid);
        mongo_id = (EditText)this.findViewById(R.id.edit_text_mongo_id);

    }

    public void addWifi(View view)
    {
        String name,pass,pwtype,bssid,mongo_id;
        name = wifi_name.getText().toString();
        pass = wifi_pass.getText().toString();
        pwtype = wifi_pwtype.getText().toString();
        bssid = this.bssid.getText().toString();
        mongo_id = this.mongo_id.getText().toString();
        if(name.length()*pass.length()*pwtype.length()*bssid.length()*mongo_id.length()==0)
        {
            Toast.makeText(this, "You have unfilled fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Wifi wifi = new Wifi(name,pass,pwtype,bssid,mongo_id);
        wifiHelper.insertWifi(wifi);
    }

    public void clear_feilds(View view)
    {
        wifi_name.setText("");
        wifi_pass.setText("");
        wifi_pwtype.setText("");
        bssid.setText("");
        mongo_id.setText("");
    }

    public void  finish__add_wifi_activity(View view)
    {
        finish();
    }

}
