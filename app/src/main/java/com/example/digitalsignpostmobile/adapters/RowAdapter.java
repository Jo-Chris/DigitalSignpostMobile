package com.example.digitalsignpostmobile.adapters;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.digitalsignpostmobile.R;
import com.example.digitalsignpostmobile.database.SignDatabase;
import com.example.digitalsignpostmobile.models.Sign;
import com.example.digitalsignpostmobile.models.SignData;
import com.google.android.material.textfield.TextInputLayout;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class RowAdapter extends RecyclerView.Adapter<RowAdapter.MainViewHolder> implements View.OnClickListener {
    private static final String TAG = "DetailAdapter";
    private List<SignData> dataset; // the signimage contains n-Signs with n-SignRows

    public RowAdapter(List<SignData> dataset) {
        this.dataset = dataset;
    }

    @Override
    public void onClick(View v) {

    }

    class MainViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private EditText target;
        private EditText duration;
        private TextView  rowNumber;
        private Button removeRow;

        MainViewHolder(@NonNull LinearLayout itemView) {
            super(itemView);

            // initialize the UI widgets
            linearLayout = itemView.findViewById(R.id.linearLayoutRV);
            target = itemView.findViewById(R.id.targetValue);
            duration = itemView.findViewById(R.id.durationValue);
            rowNumber = itemView.findViewById(R.id.rowNumber);
            removeRow = itemView.findViewById(R.id.deleteRowBtn);
        }
    }

    @NotNull
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
        holder.rowNumber.setText(holder.target.getContext().getResources().getString(R.string.points_string, position += 1)); // create resource string if bored

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }



}


