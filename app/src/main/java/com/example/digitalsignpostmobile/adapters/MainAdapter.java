package com.example.digitalsignpostmobile.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digitalsignpostmobile.R;
import com.example.digitalsignpostmobile.activities.DetailActivity;
import com.example.digitalsignpostmobile.classes.BitmapSerializer;
import com.example.digitalsignpostmobile.models.SignImage;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private static final String TAG = "MainAdapter";
    private ArrayList<SignImage> mDataset;

    public static Context context;

    public MainAdapter(ArrayList<SignImage> listData) {
        mDataset = listData;
    }

    static class MainViewHolder extends RecyclerView.ViewHolder {
        private LinearLayout linearLayout;
        private TextView imageTitle;
        private TextView numberOfSigns;
        private TextView numberOfSignsLabel;
        private RecyclerView recyclerViewMain;
        private ImageView signImageBitmap;


        MainViewHolder(@NonNull LinearLayout itemView) {
            super(itemView);

            linearLayout = itemView.findViewById(R.id.linearLayout);
            imageTitle = itemView.findViewById(R.id.imageTitle);
            numberOfSigns = itemView.findViewById(R.id.numberOfSigns);
            numberOfSignsLabel = itemView.findViewById(R.id.numberOfSignsLabel);
            recyclerViewMain = itemView.findViewById(R.id.recyclerViewMain);
            signImageBitmap = itemView.findViewById(R.id.signImageMain);

        }

    }

    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        LinearLayout v = (LinearLayout) layoutInflater.inflate(R.layout.recyclerview_main_item, parent, false);

        MainAdapter.context = parent.getContext();

        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) {

        Log.wtf(TAG, "There are " + mDataset.size() + "items in the list");

        final SignImage mListData = mDataset.get(position);

        holder.imageTitle.setText(mListData.getTitle());
        holder.numberOfSigns.setText(String.valueOf(mListData.getNumberOfSigns()));

        if (mListData.getNumberOfSigns() == 1) {
            holder.numberOfSignsLabel.setText(R.string.label_sign);
        } else {
            holder.numberOfSignsLabel.setText(R.string.label_signs);
        }

        holder.signImageBitmap.setImageBitmap(BitmapSerializer.loadImageFromStorage(MainAdapter.context, "image_"+position+".jpg"));

        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Context c = view.getContext();
                c.startActivity(new Intent(c.getApplicationContext(), DetailActivity.class).putExtra("id", mListData.getId()));
            }
        });
    }


    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
