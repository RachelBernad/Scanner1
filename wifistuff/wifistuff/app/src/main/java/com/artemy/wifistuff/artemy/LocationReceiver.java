package com.artemy.wifistuff.artemy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.artemy.wifistuff.rachel.MainActivity;

public class LocationReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if (action.equals("android.location.PROVIDERS_CHANGED")) {

            MainActivity.Location_changed(context);
        }
    }
}
