package com.artemy.wifistuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListAllWifiesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener{

    ListView list_view_all_wifies;
    ArrayList<Wifi> all_wifies = new ArrayList<Wifi>();
    AdapterWifi adapterWifi;
    WifiHelper wifiHelper;
    EditText wifi_name;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all_wifies);
        list_view_all_wifies = (ListView) findViewById(R.id.list_view_all_wifies);
        wifi_name = (EditText)findViewById(R.id.edit_text_wifi_name);

        wifiHelper = new WifiHelper(this);
        all_wifies = wifiHelper.getAllWifies();
        adapterWifi = new AdapterWifi(this, all_wifies);
        list_view_all_wifies.setAdapter(adapterWifi);
        list_view_all_wifies.setOnItemClickListener(this);
        list_view_all_wifies.setOnItemLongClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l)
    {
        wifiHelper.deleteWifi(all_wifies.get(position));
        all_wifies.remove(position);
        adapterWifi.notifyDataSetChanged();

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l)
    {
        Intent intent = new Intent(this,EditWifiActivity.class);
        intent.putExtra("wifi",all_wifies.get(position));
        startActivityForResult(intent,1);
        return false;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode==RESULT_OK) {
            all_wifies.set(position, (Wifi) data.getSerializableExtra("wifi"));
            adapterWifi.notifyDataSetChanged();
        }
    }


    public void Search_wifi_by_name(View view)
    {
        String wifi_name = this.wifi_name.getText().toString();
        if(wifiHelper.isExist(wifi_name))
        {
            Toast.makeText(this, "Wifi named : " + wifi_name + " exists.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Wifi named : " + wifi_name + " does not exist.", Toast.LENGTH_SHORT).show();

        }
    }

    public void  finish__list_all_wifies_activity(View view)
    {
        finish();
    }


}
