package com.example.digitalsignpostmobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.digitalsignpostmobile.R;

public class ImageActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        System.out.println("Works");

        initUI();

        showImageCaptured(getIntentData());
    }

    private void initUI(){
        imageView = findViewById(R.id.imageView);
    }

    private Uri getIntentData() {
        return Uri.parse(getIntent().getStringExtra("IMAGE_URI"));
    }

    private void showImageCaptured(Uri receivedImageUri) {
        imageView.setImageURI(receivedImageUri);
    }
}
