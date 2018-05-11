package com.artemy.wifistuff;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.artemy.wifistuff.rachel.WifiNetwork;

import java.util.List;

/**
 * Created by Win10 User on 22.04.2018.
 */

public class WifiAdapter extends ArrayAdapter<WifiNetwork> {

    Context context;
    List<WifiNetwork> objects;

    public WifiAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<WifiNetwork> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.objects = objects;
    }

    //@NonNull
    //@Override
    /*public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = ((Activity)context).getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.contact_layout,parent, false);
        TextView SSID = view.findViewById(R.id.tvWifiName);
        TextView password = view.findViewById(R.id.tvPass);
        TextView capabilities = view.findViewById(R.id.tvcapabilities);
        TextView BSSID = view.findViewById(R.id.tvbssid);
        WifiNetwork temp = objects.get(position);

        SSID.setText(String.valueOf(temp.getSSID()));
        password.setText(String.valueOf(temp.getPassword()));
        capabilities.setText(String.valueOf(temp.getCapabilities()));
        BSSID.setText(String.valueOf(temp.getBSSID()));
        return view;
    }
}*/


}

