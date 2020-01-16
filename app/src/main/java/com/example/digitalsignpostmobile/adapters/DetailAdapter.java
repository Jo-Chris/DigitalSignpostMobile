package com.example.digitalsignpostmobile.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.digitalsignpostmobile.R;
import com.example.digitalsignpostmobile.database.SignDatabase;
import com.example.digitalsignpostmobile.models.Sign;
import com.example.digitalsignpostmobile.models.SignData;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MainViewHolder> {
    private static final String TAG = "DetailAdapter";
    private List<Sign> dataset;
    private List<SignData> datasetSignData;
    private int datasetLength = 0;


    public DetailAdapter(List<Sign> dataset, List<SignData> datasetSignData) {
        this.dataset = dataset;
        this.datasetSignData = datasetSignData;
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private TextView imageTitle;
        private TextView direction;
        private TextView rowNumber;
        private TextView responsibleOrganisation;
        private RecyclerView rowRecyclerView;

        MainViewHolder(@NonNull LinearLayout itemView) {
            super(itemView);

            //increase length, to detect current inner recyclerPosition
            datasetLength++;

            //init the recyclerview in here
            rowRecyclerView = itemView.findViewById(R.id.rowRecyclerView);
            rowRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));

            // only add the data belonging to that specific sign
            rowRecyclerView.setAdapter(new RowAdapter(SignData.createSubSet(datasetSignData, datasetLength)));

            // initialize the UI widgets
            linearLayout = itemView.findViewById(R.id.linearLayoutRV);
            imageTitle = itemView.findViewById(R.id.signTitle);
            direction = itemView.findViewById(R.id.directionValue);
            rowNumber = itemView.findViewById(R.id.rowNumberValue);
            responsibleOrganisation = itemView.findViewById(R.id.resOrgValue);
        }
    }

    @Override
    public DetailAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        LinearLayout v = (LinearLayout) layoutInflater.inflate(R.layout.detal_activity_single_sign, parent, false);

        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        final Sign sign = dataset.get(position);

        holder.imageTitle.setText(sign.getTitle());
        holder.direction.setText(sign.getDirection());
        holder.rowNumber.setText(String.valueOf(sign.getRowCount()));
        holder.responsibleOrganisation.setText(String.valueOf(sign.isResOrgAvailable()));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

}

