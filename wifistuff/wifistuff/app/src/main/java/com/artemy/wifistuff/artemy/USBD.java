package com.artemy.wifistuff.artemy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.artemy.wifistuff.rachel.MainActivity;

public class USBD extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if (action.equals("android.intent.action.ACTION_POWER_DISCONNECTED")) {
            MainActivity.USBDisconnected(context);
            MainActivity.POWER_off(context);
        }
    }
}
