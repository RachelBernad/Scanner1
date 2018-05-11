package com.artemy.wifistuff.rachel;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.artemy.wifistuff.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener
{
    WifiManager wifi;
    ListView lv;
    Button buttonScan;
    int size = 0;
    List<ScanResult> results;

    String ITEM_KEY = "key";
    ArrayList<HashMap<String, String>> arraylist = new ArrayList<HashMap<String, String>>();
    SimpleAdapter adapter;

    WifiConfiguration conf;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonScan = (Button) findViewById(R.id.buttonScan);
        buttonScan.setOnClickListener(this);
        lv = (ListView)findViewById(R.id.list);

        //#############################################################################

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, infoActivity.class);

                ScanResult temp = results.get(i);
                intent.putExtra("name", temp.SSID);
                intent.putExtra("bssid", temp.BSSID);
                intent.putExtra("capabilities", temp.capabilities);

                startActivity(intent);
            }
        });

        //#############################################################################

        wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        this.adapter = new SimpleAdapter(MainActivity.this, arraylist, R.layout.row, new String[] { ITEM_KEY }, new int[] { R.id.list_value });
        lv.setAdapter(this.adapter);

        registerReceiver(new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context c, Intent intent)
            {
                results = wifi.getScanResults();
                size = results.size();

                if (size > 0) {
                    for (ScanResult result : results) {
                        HashMap<String, String> item = new HashMap<String, String>();
                        item.put(ITEM_KEY, result.SSID);
                        arraylist.add(item);
                    }
                    MainActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            MainActivity.this.adapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        }, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));

    }

    public void onClick(View view)
    {
        arraylist.clear();
        wifi.startScan();
        Toast.makeText(this, "Scanning", Toast.LENGTH_SHORT).show();
    }

    //region  usb

    public static void USBConnected(Context context) {

        NotificationCompat.Builder conNot = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.star_on)
                .setContentTitle("USB connected")
                .setContentText("USB has been connected");
        NotificationManagerCompat NMC1 = NotificationManagerCompat.from(context);
        NMC1.notify(1, conNot.build());
    }

    public static void USBDisconnected(Context context) {

        NotificationCompat.Builder disNot = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.star_on)
                .setContentTitle("USB disconnected")
                .setContentText("USB has been disconnected");
        NotificationManagerCompat NMC2 = NotificationManagerCompat.from(context);
        NMC2.notify(2, disNot.build());
    }

    //endregion

    //region power

    public static void POWER_on(Context context) {

        NotificationCompat.Builder conNot = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.star_on)
                .setContentTitle("Power on")
                .setContentText("Power on");
        NotificationManagerCompat NMC1 = NotificationManagerCompat.from(context);
        NMC1.notify(3, conNot.build());
    }

    public static void POWER_off(Context context) {

        NotificationCompat.Builder disNot = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.star_on)
                .setContentTitle("Power off")
                .setContentText("Power off");
        NotificationManagerCompat NMC2 = NotificationManagerCompat.from(context);
        NMC2.notify(4, disNot.build());
    }

    //endregion

    //region wifi

    public static void Wifichanged(Context context) {

        NotificationCompat.Builder conNot = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.star_on)
                .setContentTitle("Wifi changed")
                .setContentText("Wifi changed");
        NotificationManagerCompat NMC1 = NotificationManagerCompat.from(context);
        NMC1.notify(1, conNot.build());
    }

    //endregion

    //region location

    public static void Location_changed(Context context) {

        NotificationCompat.Builder conNot = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.star_on)
                .setContentTitle("Location changed")
                .setContentText("Location changed");
        NotificationManagerCompat NMC1 = NotificationManagerCompat.from(context);
        NMC1.notify(1, conNot.build());
    }

    //endregion

    //region flight mode

    public static void FlightModeChanged(Context context) {

        NotificationCompat.Builder conNot = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.star_on)
                .setContentTitle("Flight Mode Changed")
                .setContentText("Flight Mode Changed");
        NotificationManagerCompat NMC1 = NotificationManagerCompat.from(context);
        NMC1.notify(1, conNot.build());
    }

    //endregion

    //region volume

    public static void VolumeChanged(Context context) {

        NotificationCompat.Builder conNot = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.star_on)
                .setContentTitle("Volume changed")
                .setContentText("Volume changed");
        NotificationManagerCompat NMC1 = NotificationManagerCompat.from(context);
        NMC1.notify(1, conNot.build());
    }

    //endregion

    //region bluetooth

    public static void BluetoothChanged(Context context) {

        NotificationCompat.Builder conNot = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.star_on)
                .setContentTitle("Bluetooth changed")
                .setContentText("Bluetooth changed");
        NotificationManagerCompat NMC1 = NotificationManagerCompat.from(context);
        NMC1.notify(1, conNot.build());
    }

    //endregion

    //region reboot

    public static void Reboot(Context context) {

        NotificationCompat.Builder conNot = new NotificationCompat.Builder(context)
                .setSmallIcon(android.R.drawable.star_on)
                .setContentTitle("Reboot")
                .setContentText("Reboot");
        NotificationManagerCompat NMC1 = NotificationManagerCompat.from(context);
        NMC1.notify(1, conNot.build());
    }

    //endregion

}
