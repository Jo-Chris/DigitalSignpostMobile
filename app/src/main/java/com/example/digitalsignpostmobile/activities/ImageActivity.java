package com.example.digitalsignpostmobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.digitalsignpostmobile.R;
import com.example.digitalsignpostmobile.classes.RequestHandler;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;

public class ImageActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView imageView;
    private Button buttonSave;
    private ConstraintLayout loaderView;
    private URI receivedImageUri;
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
        buttonSave = findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(this);
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
            Toast.makeText(this, "Starting", Toast.LENGTH_SHORT).show();

            Log.d(TAG, "Starting");

            new Thread(new Runnable() {

                RequestHandler background = new RequestHandler();
                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                public void run() {
                    Log.d(TAG, "Starting to compress...");

                    imageBm.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
                    String encodedImage= Base64.encodeToString(byteArrayOutputStream.toByteArray(),Base64.DEFAULT);
                    Log.d(TAG, encodedImage);
                    background.execute(encodedImage); //call to AsyncTask
                }
            }).start();

        }
    }
}
