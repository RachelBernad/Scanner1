package com.artemy.wifistuff;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by natal on 11/03/2018.
 */

public class AdapterWifi extends ArrayAdapter<Wifi>
{
    Activity activity;
    ArrayList<Wifi> all_wifies;
    LayoutInflater layoutInflater;

    public AdapterWifi(Activity activity,ArrayList<Wifi> all_wifies)
    {
        super(activity, 0, all_wifies);
        this.activity=activity;
        this.all_wifies=all_wifies;
        this.layoutInflater=(LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {

        Wifi  wifiItem = getItem(position);

        View row = layoutInflater.inflate(R.layout.item_wifi,null);

        TextView text_view_wifi_name = (TextView)row.findViewById(R.id.text_view_wifi_name);
        TextView text_view_wifi_pass = (TextView)row.findViewById(R.id.text_view_wifi_pass);
        TextView text_view_wifi_pwtype = (TextView)row.findViewById(R.id.text_view_wifi_pwtype);
        TextView text_view_bssid = (TextView)row.findViewById(R.id.text_view_bssid);
        TextView text_view_Mongo_id = (TextView)row.findViewById(R.id.text_view_Mongo_id);

        text_view_wifi_name.setText(wifiItem.getWifi_name());
        text_view_wifi_pass.setText(wifiItem.getWifi_pass());
        text_view_wifi_pwtype.setText(wifiItem.getWifi_pwtype());
        text_view_bssid.setText(wifiItem.getBssid());
        text_view_Mongo_id.setText(wifiItem.getMongo_id());

        return row;
    }


}
