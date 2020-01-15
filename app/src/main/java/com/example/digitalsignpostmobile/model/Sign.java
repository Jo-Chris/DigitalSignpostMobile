package com.example.digitalsignpostmobile.model;

import android.graphics.Bitmap;
import android.util.Log;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Sign implements Serializable {

    @PrimaryKey
    private int uid;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "direction")
    private String direction;
    @ColumnInfo(name = "rowCount")
    private int rowCount;
    @ColumnInfo(name = "rowCount")
    private boolean hasResOrg;
    @ColumnInfo(name = "rowCount")
    private Bitmap image;

    private List<SignData> signDataList;
    private static final String TAG = "Sign";

    public Sign(String title, String direction, int rowCount, boolean hasResOrg, Bitmap image) {
        this.title = title;
        this.direction = direction;
        this.rowCount = rowCount;
        this.hasResOrg = hasResOrg;
        this.image = image;
        this.signDataList = new ArrayList<>();
    }

    public void addSignData(SignData data){
        if (signDataList.size() != rowCount){
            this.signDataList.add(data);
        }else{
            Log.i(TAG, "SignDataList is already full, no more rows to add");
        }
    }

    public Sign(){

    }

    public int getRowSize(){
        return rowCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public boolean isHasResOrg() {
        return hasResOrg;
    }

    public void setHasResOrg(boolean hasResOrg) {
        this.hasResOrg = hasResOrg;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public List<SignData> getSignDataList() {
        return signDataList;
    }

    public void setSignDataList(List<SignData> signDataList) {
        this.signDataList = signDataList;
    }
}
