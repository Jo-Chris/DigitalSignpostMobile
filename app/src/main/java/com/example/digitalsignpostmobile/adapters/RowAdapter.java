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

public class RowAdapter extends RecyclerView.Adapter<RowAdapter.MainViewHolder> {
    private static final String TAG = "DetailAdapter";
    private List<SignData> dataset; // the signimage contains n-Signs with n-SignRows

    public RowAdapter(List<SignData> dataset) {
        this.dataset = dataset;
    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private TextView target;
        private TextView duration;

        MainViewHolder(@NonNull LinearLayout itemView) {
            super(itemView);

            // initialize the UI widgets
            linearLayout = itemView.findViewById(R.id.linearLayoutRV);
            target = itemView.findViewById(R.id.targetValue);
            duration = itemView.findViewById(R.id.durationValue);
        }
    }

    @Override
    public RowAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        LinearLayout v = (LinearLayout) layoutInflater.inflate(R.layout.detail_view_row_data, parent, false);

        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        final SignData signData = dataset.get(position);

        // holder.imageTitle.setText(sign.getTitle());
        holder.target.setText(signData.getTarget());
        holder.duration.setText(signData.getDuration());

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

}


