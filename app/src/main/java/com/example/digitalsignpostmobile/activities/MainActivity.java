package com.example.digitalsignpostmobile.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Delete;
import androidx.room.Room;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Toast;

import com.example.digitalsignpostmobile.R;
import com.example.digitalsignpostmobile.classes.Camera;
import com.example.digitalsignpostmobile.database.SignDAO;
import com.example.digitalsignpostmobile.database.SignDatabase;
import com.example.digitalsignpostmobile.model.Image;
import com.example.digitalsignpostmobile.classes.MainAdapter;
import com.example.digitalsignpostmobile.model.Sign;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final int PERMISSION_CODE = 100;
    private final int IMAGE_CAPTURE_CODE = 1001;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Image> mDataset = new ArrayList<>();
    private FloatingActionButton openCamera;
    private Uri image_uri;

    private final String TAG = "MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatabase();
        createTestData();
        initUI();
        addListeners();

    }

    @Delete
    private void createTestData(){
        Random r = new Random();

        for (int i = 0; i < 20; i++) {
            Image image = new Image("Bild " + (i + 1), r.nextInt(10), 47.503410, 12.571640);
            mDataset.add(image);
        }
    }

    private void initDatabase(){
        SignDatabase db = Room.databaseBuilder(getApplicationContext(), SignDatabase.class, "signpost-db").build();
    }

    private void initUI(){
        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Übersicht");
        }

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new MainAdapter(mDataset);
        recyclerView.setAdapter(mAdapter);

        openCamera = findViewById(R.id.openCamera);
    }


    private void addListeners(){
        openCamera.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.openCamera){
            if (Camera.checkCamera(this, PERMISSION_CODE)) {
                this.image_uri = Camera.openCamera(this, IMAGE_CAPTURE_CODE);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_CODE) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    this.image_uri = Camera.openCamera(this, IMAGE_CAPTURE_CODE);
                } else {
                    Toast.makeText(this, "Permission denied!", Toast.LENGTH_SHORT).show();
                }
            }
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            startActivity(new Intent(this, ImageActivity.class).putExtra("IMAGE_URI", image_uri.toString()));
        }
    }
}
