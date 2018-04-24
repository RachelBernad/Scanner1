package com.artemy.wifistuff;

import android.app.Activity;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Headers;

/**
 * Created by Win10 User on 17.04.2018.
 */

public class ListActivity extends Activity {
    ListView lstView;
    Headers wifilst;



    void getNetworks() {
        // TODO: Implement this method to send token to your app server.
        SendMessage apiHandler = new SendMessage();
        JSONObject params = new JSONObject();
        try {
            apiHandler.post("https://droneserverv1.herokuapp.com/api/WIFI/get", params.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        wifilst = apiHandler.ReturnHeaders();
    }


}
