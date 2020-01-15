package com.example.digitalsignpostmobile.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Delete;
import androidx.room.Room;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.digitalsignpostmobile.R;
import com.example.digitalsignpostmobile.classes.Camera;
import com.example.digitalsignpostmobile.classes.annotations.DeleteInProduction;
import com.example.digitalsignpostmobile.database.DAO.SignDAO;
import com.example.digitalsignpostmobile.database.DAO.SignDataDAO;
import com.example.digitalsignpostmobile.database.DAO.SignImageDAO;
import com.example.digitalsignpostmobile.database.SignDatabase;
import com.example.digitalsignpostmobile.model.SignImage;
import com.example.digitalsignpostmobile.classes.MainAdapter;
import com.example.digitalsignpostmobile.model.Sign;
import com.example.digitalsignpostmobile.model.SignData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

        // only add dummy data, if empty
        if (signImageDAO.getAll().size() == 0){

            signImageDAO.insert(new SignImage("Zur Wanderhütte", 3, 43.2312312, 84.1231231));
            signImageDAO.insert(new SignImage("Großglockner", 1, 43.2312312, 84.1231231));
            signImageDAO.insert(new SignImage("Zirler Berg", 2, 43.2312312, 84.1231231));
            signImageDAO.insert(new SignImage("Watzmann", 4, 43.2312312, 84.1231231));

            signDAO.insert(new Sign("Berghuette", "Right", 3, true, "test/test/test", 1));
            signDAO.insert(new Sign("Wanderhuette", "Right", 3, true, "test/test/test", 3));
            signDAO.insert(new Sign("Dosenbier", "Right", 3, true, "test/test/test", 2));
            signDAO.insert(new Sign("Stalahütte", "Right", 3, true, "test/test/test", 3));
            signDAO.insert(new Sign("Mount Sebastian", "Right", 3, true, "test/test/test", 3));
            signDAO.insert(new Sign("Wanderwildweg", "Right", 3, true, "test/test/test", 1));

            signDataDAO.insert(new SignData("Wanderweg 1", "20min", "816", "Stadtgemeinde Kufstein", 1));
            signDataDAO.insert(new SignData("Wanderweg 2", "45min", "236", "Stadtgemeinde Kufstein", 1));
            signDataDAO.insert(new SignData("Wanderweg 3", "70min", "316", "Stadtgemeinde Kufstein", 1));
            signDataDAO.insert(new SignData("Wanderweg 4", "50min", "316", "Stadtgemeinde Kufstein", 2));
            signDataDAO.insert(new SignData("Wanderweg 5", "55min", "316", "Stadtgemeinde Kufstein", 2));
            signDataDAO.insert(new SignData("Wanderweg 6", "45min", "316", "Stadtgemeinde Kufstein", 2));
            signDataDAO.insert(new SignData("Wanderweg 7", "1 min", "316", "Stadtgemeinde Kufstein", 3));
            signDataDAO.insert(new SignData("Wanderweg 8", "12min", "316", "Stadtgemeinde Kufstein", 3));
        }

        mDataset.addAll(signImageDAO.getAll());


        Toast.makeText(this, "Length ist " + signImageDAO.getAll().size(), Toast.LENGTH_SHORT).show();

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