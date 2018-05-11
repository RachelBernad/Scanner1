package com.artemy.wifistuff.rachel;

/**
 * Created by Win10 User on 19.02.2018.
 */

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.widget.Toast;


import com.artemy.wifistuff.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static android.content.ContentValues.TAG;


public class SendMessage {

    FileOutputStream outputStream;
    private ResponseBody resBody;
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
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                //e.printStackTrace();
                Log.d(TAG, "onFailure: it failed");
            }

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                } else
                    Log.d(TAG, "onResponse: succeed");
                Headers responseheaders = response.headers();
                resBody = response.body();
                for (int i = 0; i < responseheaders.size(); i++) {
                    Log.d(TAG, responseheaders.name(i) + ": " + responseheaders.value(i));
                }


            }
        });

    }
}

//    public void createfile() {
//        try {
//            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(Context.openFileOutput("gson.txt", Context.MODE_PRIVATE));
//            outputStreamWriter.write(resBody.string());
//            outputStreamWriter.close();
//        }
//        catch (IOException e) {
//            Log.e("Exception", "File write failed: " + e.toString());
//        }
//
//    }
