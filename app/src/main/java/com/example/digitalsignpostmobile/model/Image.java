package com.example.digitalsignpostmobile.model;

public class Image {

    private String title;
    private int numberOfSigns;
    private double latitude;
    private double longitude;

    public Image(String title, int numberOfSigns, double latitude, double longitude) {
        this.title = title;
        this.numberOfSigns = numberOfSigns;
        this.latitude = latitude;
        this.longitude = longitude;
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
