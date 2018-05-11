package com.artemy.wifistuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class EditWifiActivity extends AppCompatActivity  {

    EditText edit_text_wifi_name, edit_text_wifi_pass, edit_text_wifi_pwtype, edit_text_wifi_bssid, edit_text_mongo_id;
    Wifi wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_wifi);

        Intent intent = getIntent();
        wifi = (Wifi) intent.getSerializableExtra("wifi");

        edit_text_wifi_name = (EditText) this.findViewById(R.id.edit_text_name_of_the_wifi);
        edit_text_wifi_name.setText(wifi.getWifi_name());

        edit_text_wifi_pass = (EditText) this.findViewById(R.id.edit_text_wifi_pass);
        edit_text_wifi_pass.setText(wifi.getWifi_pass());

        edit_text_wifi_pwtype = (EditText) this.findViewById(R.id.edit_text_wifi_pwtype);
        edit_text_wifi_pwtype.setText(wifi.getWifi_pwtype());

        edit_text_wifi_bssid = (EditText) this.findViewById(R.id.edit_text_wifi_bssid);
        edit_text_wifi_bssid.setText(wifi.getBssid());

        edit_text_mongo_id = (EditText) this.findViewById(R.id.edit_text_mongo_id);
        edit_text_mongo_id.setText(wifi.getMongo_id());

    }


    public void submitClick(View view)
    {
        if (edit_text_wifi_name.getText().length() * edit_text_wifi_pass.getText().length() * edit_text_wifi_pwtype.getText().length() * edit_text_wifi_bssid.length() * edit_text_mongo_id.length() == 0) {
            Toast.makeText(this, "You have unfilled fields", Toast.LENGTH_SHORT).show();
            return;
        }

        wifi.setWifi_name(edit_text_wifi_name.getText().toString());
        wifi.setWifi_pass(edit_text_wifi_pass.getText().toString());
        wifi.setWifi_pwtype(edit_text_wifi_pwtype.getText().toString());
        wifi.setBssid(edit_text_wifi_bssid.getText().toString());
        wifi.setMongo_id(edit_text_mongo_id.getText().toString());
        WifiHelper wifiHelper = new WifiHelper(this);

        wifiHelper.updateWifi(wifi);
        Intent intent = new Intent();
        intent.putExtra("wifi",wifi);
        setResult(RESULT_OK, intent);
        finish();


    }

    public  void finish_edit_wifi_activity(View view)
    {
        finish();
    }



}
