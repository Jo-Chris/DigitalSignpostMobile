package com.example.digitalsignpostmobile.classes;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Uploading the file to server
 */
public class RequestHandler extends AsyncTask<String, Integer, String> {
    private static final String TAG = "RequestHandler";
    private String signReponse;

    public String send(String url){
        return doInBackground(new String[]{url});
    }

    @Override
    protected String doInBackground(String[] encodedImage) {
        final String urlServer = "http://10.0.2.2:5000/detect";

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
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((inputStream)));
            StringBuilder stringBuilder = new StringBuilder();

            while ((signReponse = bufferedReader.readLine()) != null) {
                stringBuilder.append(signReponse).append("\n");
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
}