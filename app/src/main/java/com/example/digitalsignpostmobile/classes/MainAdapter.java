package com.example.digitalsignpostmobile.classes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.digitalsignpostmobile.R;
import com.example.digitalsignpostmobile.model.Image;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private ArrayList<Image> mDataset;

    public MainAdapter(ArrayList<Image> listData) {
        mDataset = listData;
    }

    public static class MainViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayout;
        public TextView imageTitle;
        public TextView imageCoords;
        public TextView numberOfSigns;
        public Button button;

        public MainViewHolder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            imageTitle = itemView.findViewById(R.id.imageTitle);
            imageCoords = itemView.findViewById(R.id.imageCoords);
            numberOfSigns = itemView.findViewById(R.id.numberOfSigns);
            button = itemView.findViewById(R.id.button);
        }
    }

    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.recyclerview_main_item, parent, false);
        MainViewHolder vh = new MainViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {
        final Image mListData = mDataset.get(position);
        holder.imageTitle.setText(mDataset.get(position).getTitle());
        holder.imageCoords.setText(mDataset.get(position).getCoords());
        holder.numberOfSigns.setText(String.valueOf(mDataset.get(position).getNumberOfSigns()));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Click on item: " + mListData.getTitle(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
