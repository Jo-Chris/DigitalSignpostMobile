package com.example.digitalsignpostmobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageView;

import com.example.digitalsignpostmobile.R;

public class ImageActivity extends AppCompatActivity {

    private ImageView imageView;
    private ConstraintLayout loaderView;

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
    }
}
