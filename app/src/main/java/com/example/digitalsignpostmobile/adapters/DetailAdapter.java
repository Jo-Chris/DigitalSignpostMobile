package com.example.digitalsignpostmobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.digitalsignpostmobile.R;
import com.example.digitalsignpostmobile.database.DAOs.SignDAO;
import com.example.digitalsignpostmobile.database.SignDatabase;
import com.example.digitalsignpostmobile.models.Sign;
import com.example.digitalsignpostmobile.models.SignData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.MainViewHolder> {
    private static final String TAG = "DetailAdapter";
    private List<Sign> dataset;
    private List<SignData> datasetSignData;
    private int datasetLength = 0;
    private SignDAO signDAO;

    public DetailAdapter(List<Sign> dataset, List<SignData> datasetSignData) {
        this.dataset = dataset;
        this.datasetSignData = datasetSignData;
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout linearLayout;
        private TextView imageTitle;
        private TextView direction;
        private TextView rowNumber;
        private TextView responsibleOrganisation;
        private RecyclerView rowRecyclerView;
        private Button removeRow;

        MainViewHolder(@NonNull RelativeLayout itemView) {
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
            removeRow = itemView.findViewById(R.id.deleteRowBtn);
        }
    }

    @NotNull
    @Override
    public DetailAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RelativeLayout v = (RelativeLayout) layoutInflater.inflate(R.layout.detail_activity_single_sign, parent, false);

        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        final Sign sign = dataset.get(position);

        holder.imageTitle.setText(sign.getTitle());
        holder.direction.setText(sign.getDirection());
        holder.rowNumber.setText(String.valueOf(sign.getRowCount()));
        holder.responsibleOrganisation.setText(String.valueOf(sign.isResOrgAvailable()));

        holder.removeRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Click on item: ", Toast.LENGTH_LONG).show();
                dataset.remove(sign);

                // remove, bad smell
                SignDatabase.getInstance(view.getContext()).signDao().delete(sign);

                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

}

