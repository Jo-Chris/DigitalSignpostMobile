package com.example.digitalsignpostmobile.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class SignImage {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "number_of_signs")
    private int numberOfSigns;
    @ColumnInfo(name = "latitude")
    private double latitude;
    @ColumnInfo(name = "longitude")
    private double longitude;


    public SignImage(){

    }

    @Ignore
    public SignImage(String title, int numberOfSigns, double latitude, double longitude) {
        this.title = title;
        this.numberOfSigns = numberOfSigns;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfSigns() {
        return numberOfSigns;
    }

    public void setNumberOfSigns(int numberOfSigns) {
        this.numberOfSigns = numberOfSigns;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCoords() {
        return "Lat: " + this.latitude + " / Lng: " + this.longitude;
    }

}
