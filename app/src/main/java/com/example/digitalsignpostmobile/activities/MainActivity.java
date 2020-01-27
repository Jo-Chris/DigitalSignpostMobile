package com.example.digitalsignpostmobile.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Delete;
import androidx.room.Room;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.digitalsignpostmobile.R;
import com.example.digitalsignpostmobile.classes.Camera;
import com.example.digitalsignpostmobile.classes.annotations.DeleteInProduction;
import com.example.digitalsignpostmobile.database.DAOs.SignDAO;
import com.example.digitalsignpostmobile.database.DAOs.SignDataDAO;
import com.example.digitalsignpostmobile.database.DAOs.SignImageDAO;
import com.example.digitalsignpostmobile.database.SignDatabase;
import com.example.digitalsignpostmobile.models.SignImage;
import com.example.digitalsignpostmobile.adapters.MainAdapter;
import com.example.digitalsignpostmobile.models.Sign;
import com.example.digitalsignpostmobile.models.SignData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private final int PERMISSION_CODE = 100;
    private final int IMAGE_CAPTURE_CODE = 1001;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<SignImage> mDataset = new ArrayList<>();
    private FloatingActionButton openCamera;
    private SignDatabase signDatabase;
    private Uri image_uri;
    private SignDAO signDAO;
    private SignDataDAO signDataDAO;
    private SignImageDAO signImageDAO;

    private final String TAG = "MAIN";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatabase();
        // only dummy methods
        createTestDummyData();
        // retrieve the dummy data
        getDBData();
        initUI();
        addListeners();
    }

    @DeleteInProduction
    private void createTestDummyData(){
        signDAO = SignDatabase.getInstance(this).signDao();
        signDataDAO = SignDatabase.getInstance(this).signDataDAO();
        signImageDAO = SignDatabase.getInstance(this).signImageDAO();

        mDataset.addAll(signImageDAO.getAll());

    }

    @Delete
    private void getDBData() {
        List<Sign> signDao = signDAO.getAll();

        for (Sign s: signDao){
            System.out.println(s.toString());
        }
    }


    private void initDatabase(){
        signDatabase = Room.databaseBuilder(getApplicationContext(), SignDatabase.class, "signpost-db").build();
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

        mAdapter.notifyDataSetChanged();
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