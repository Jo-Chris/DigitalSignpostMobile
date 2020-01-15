package com.example.digitalsignpostmobile.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.digitalsignpostmobile.R;
import com.example.digitalsignpostmobile.adapters.DetailAdapter;
import com.example.digitalsignpostmobile.database.SignDatabase;
import com.example.digitalsignpostmobile.models.Sign;
import com.example.digitalsignpostmobile.models.SignData;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private LinearLayout linearLayout, linearLayoutRow;
    private List<Sign> dataset = new ArrayList<>();
    private List<SignData> datasetSignData = new ArrayList<>();
    private DetailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        // get the deliverd Image id
        setDataset();

        initRecyclerView();

    }

    public DetailActivity() {}

    private void setDataset(){
        /*
         * short break:
         *
         * We get a specific Image, retrieve the Signs from that and get all the SignData that is
         * associated with that Sign. We then send these to Lists to the DetailAdapter
         * who creates the Recyclerview (with the Signs) holding the nested Recyclerview (with the SignData)
         */

        int id = getIntent().getIntExtra("id", 0);

        dataset = SignDatabase.getInstance(this).signDao().getId(id);
        datasetSignData = SignDatabase.getInstance(this).signDataDAO().getId(id);
    }

    private void initRecyclerView(){

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Details");
        }

        RecyclerView recycler = findViewById(R.id.detailRecyclerView);
        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(new DetailAdapter(dataset));

    }
}
