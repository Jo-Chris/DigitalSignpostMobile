package com.example.digitalsignpostmobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.digitalsignpostmobile.R;
import com.example.digitalsignpostmobile.classes.BitmapSerializer;
import com.example.digitalsignpostmobile.classes.DialogHandler;
import com.example.digitalsignpostmobile.classes.RequestHandler;
import com.example.digitalsignpostmobile.classes.SignMapper;
import com.example.digitalsignpostmobile.database.SignDatabase;
import com.example.digitalsignpostmobile.interfaces.AsyncResponse;

import org.json.JSONException;

import java.io.ByteArrayOutputStream;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener, AsyncResponse {

    private ImageView imageView;
    private Button buttonSave;
    private ConstraintLayout loaderView;
    private CircularProgressButton circButton;
    private Bitmap imageBm;
    private final String TAG = "ImageActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        initUI();
        showImageCaptured(getIntentData());

    }

    private void initUI(){
        imageView = findViewById(R.id.imageView);
        loaderView = findViewById(R.id.loaderView);
        circButton = findViewById(R.id.buttonSave);
        circButton.setOnClickListener(this);
    }



    private Uri getIntentData() {
        return Uri.parse(getIntent().getStringExtra("IMAGE_URI"));
    }

    private void showImageCaptured(Uri receivedImageUri) {
        loaderView.setVisibility(View.VISIBLE);

        new CountDownTimer(5000, 1000){

            @Override
            public void onTick(long l) {}

            public  void onFinish(){
                loaderView.setVisibility(View.GONE);
            }

        }.start();

        imageView.setImageURI(receivedImageUri);
        imageBm = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.buttonSave){
            circButton.startAnimation();

            final RequestHandler asyncTask = new RequestHandler();
            asyncTask.delegate = this;


            final int length = SignDatabase.getInstance(v.getContext()).signImageDAO().getAll().size();

            new Thread(new Runnable() {
                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                public void run() {
                    Log.d(TAG, "Starting to compress...");

                    imageBm.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);

                    Log.d(TAG, BitmapSerializer.saveToInternalStorage(getApplicationContext(), imageBm, "image_" + length + ".jpg"));

                    String encodedImage= Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);
                    Log.d(TAG, encodedImage);
                    asyncTask.execute(encodedImage); //call to AsyncTask
                }
            }).start();

        }
    }

    @Override
    public void onGetSignData(String data) {
        circButton.doneLoadingAnimation(Color.parseColor("#A78966"), BitmapFactory.decodeResource(getResources(), R.drawable.ic_done_white_48dp));

        SignMapper signMapper = new SignMapper(getApplicationContext());

        try {
            signMapper.prepareJSONData(data);
            signMapper.getAndSaveSigns();

            DialogHandler.showSuccessDialogAndStartActivity(this, "Es wurden " + signMapper.getAmountOfDetectedSigns() + " Wanderschilder gefunden!");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

