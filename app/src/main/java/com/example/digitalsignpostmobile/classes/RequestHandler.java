package com.example.digitalsignpostmobile.classes;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.example.digitalsignpostmobile.activities.ImageActivity;
import com.example.digitalsignpostmobile.activities.MainActivity;
import com.example.digitalsignpostmobile.interfaces.AsyncResponse;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;

/**
 * Uploading the file to server
 */



public class RequestHandler extends AsyncTask<String, Integer, String> {
    private InputStream inputStream;
    private BufferedReader bufferedReader;
    private StringBuilder stringBuilder;
    private String signReponse;
    private final String urlServer = "http://10.0.2.2:5000/detect";
    private static final String TAG = "RequestHandler";
    public AsyncResponse delegate = null;

    public RequestHandler(){

    }

    public String send(String url){
        doInBackground(new String[]{url});

        return signReponse;
    }

    @Override
    protected String doInBackground(String[] encodedImage) {

        System.out.println(encodedImage[0]);

        String img = encodedImage[0];

        try {
            //connecting to server
            URL url = new URL(urlServer);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            //encode data for post method
            String dati = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(img, "UTF-8");
            bufferedWriter.write(dati);
            bufferedWriter.flush();
            bufferedWriter.close();
            os.close();

            Log.d(TAG, "Image has been sent to the Server successfully!");
            Log.d(TAG, "Waiting for result...");

            //read server answer
            inputStream = httpURLConnection.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader((inputStream)));
            stringBuilder = new StringBuilder();

            try{
                while ((signReponse = bufferedReader.readLine()) != null) {
                    stringBuilder.append(signReponse).append("\n");
                }
            }catch (ProtocolException e){
                System.out.println("We desperately need to resolve this Error!");
            }


            bufferedReader.close();
            inputStream.close();
            inputStream.close();
            httpURLConnection.disconnect();

            signReponse = stringBuilder.toString().trim();

            Log.d(TAG, signReponse);

            System.out.println(signReponse);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return signReponse;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        Log.d(TAG, String.valueOf(Arrays.asList(values)));
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        System.out.println(s);
        delegate.onGetSignData(s);
    }
}