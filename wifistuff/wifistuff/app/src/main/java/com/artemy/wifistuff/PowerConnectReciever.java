package com.artemy.wifistuff;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PowerConnectReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_POWER_CONNECTED))
        {
            Intent intent1 = new Intent(context, PushService.class);
            context.startService(intent1);
        }
        else
        {
            Intent intent1 = new Intent(context, PushService.class);
            context.stopService(intent1);
        }
    }
}
