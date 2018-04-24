package com.artemy.wifistuff;

/**
 * Created by Win10 User on 19.02.2018.
 */

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;


import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static android.content.ContentValues.TAG;


public class SendMessage {

    private Headers header;
    private static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    private OkHttpClient client = new OkHttpClient();

    void post(final String url, String string) throws IOException {
        RequestBody body = RequestBody.create(JSON, string);
        final Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback()
        {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                //e.printStackTrace();
                Log.d(TAG, "onFailure: it failed");
            }
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(@NonNull Call call,@NonNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                else
                    Log.d(TAG, "onResponse: succeed");
                Headers responseheaders = response.headers();
                header = responseheaders;
                for (int i = 0; i < responseheaders.size(); i++) {
                    Log.d(TAG, responseheaders.name(i) + ": " + responseheaders.value(i));
                }


            }
        });

    }

    public Headers ReturnHeaders(){
        return header;
    }



}