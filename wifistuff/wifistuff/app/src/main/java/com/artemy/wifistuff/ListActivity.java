package com.artemy.wifistuff;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ListView;

import com.artemy.wifistuff.rachel.WifiNetwork;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import okhttp3.Headers;

/**
 * Created by Win10 User on 17.04.2018.
 */

public class ListActivity extends Activity {
    private ListView lstView;
    private String liststr;
    private ArrayList<WifiNetwork> lst;
    private WifiAdapter wifiAdapter;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list);

            lstView = (ListView) findViewById(R.id.listNet);


            wifiAdapter =new WifiAdapter(this,0,0,lst);
            lstView.setAdapter(wifiAdapter);
        }



//    void getNetworks() {
//        String ret = "";
//
//        try {
//            InputStream inputStream = Context.openFileInput("config.txt");
//
//            if ( inputStream != null ) {
//                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//                String receiveString = "";
//                StringBuilder stringBuilder = new StringBuilder();
//
//                while ( (receiveString = bufferedReader.readLine()) != null ) {
//                    stringBuilder.append(receiveString);
//                }
//
//                inputStream.close();
//                ret = stringBuilder.toString();
//            }
//        }
//        catch (FileNotFoundException e) {
//            Log.d("login activity", "File not found: " + e.toString());
//        } catch (IOException e) {
//            Log.d("login activity", "Can not read file: " + e.toString());
//        }
//
//
//
//    }


}
