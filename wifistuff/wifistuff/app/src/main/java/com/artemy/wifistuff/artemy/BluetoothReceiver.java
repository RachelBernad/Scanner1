package com.artemy.wifistuff.artemy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.artemy.wifistuff.rachel.MainActivity;

public class BluetoothReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if (action.equals("android.bluetooth.adapter.action.STATE_CHANGED")) {

            MainActivity.BluetoothChanged(context);
        }
    }
}