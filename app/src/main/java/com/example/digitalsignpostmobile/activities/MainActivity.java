package com.example.digitalsignpostmobile.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.digitalsignpostmobile.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button openImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        addListeners();

    }


    private void initUI(){
        openImage = findViewById(R.id.openCamera);
    }

    private void addListeners(){
        openImage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.openCamera: {
                startActivity(new Intent(this, CameraActivity.class));
            }
        }
    }
}
