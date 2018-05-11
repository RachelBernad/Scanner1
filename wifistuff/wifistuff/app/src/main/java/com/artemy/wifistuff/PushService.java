package com.artemy.wifistuff;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class PushService extends Service {
    public PushService() {
    }

    @Override

    public int onStartCommand(Intent intent, int flags, int startId) {
        startForeground(1,getNotification());
        return super.onStartCommand(intent, flags, startId);
    }

    @Override

    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }


    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Notification getNotification()
    {
        int icon = R.drawable.power_and_bettary;
        long when = System.currentTimeMillis();
        String title = "Power connected";
        String ticker = "Power connected";
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
        Notification notification = builder.setSmallIcon(icon).setTicker(ticker).setWhen(when)
                .setAutoCancel(true).setContentTitle(title).setOngoing(false).
                     setDefaults(Notification.DEFAULT_SOUND).build();
        return notification;
    }
}
