package com.example.digitalsignpostmobile.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(foreignKeys = @ForeignKey(
        entity = Sign.class,
        parentColumns = "id",
        childColumns = "signId",    // the one within the class!
        onDelete = ForeignKey.CASCADE),
        indices = {@Index("signId")})

public class SignData implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "target")
    private String target;
    @ColumnInfo(name = "duration")
    private String duration;
    @ColumnInfo(name = "pathNumber")
    private String pathNumber;
    @ColumnInfo(name = "signId")
    private int signId;

    public SignData(){ }

    @Ignore
    public SignData(String target, String duration, String pathNumber, int signId){
        this.target = target;
        this.duration = duration;
        this.pathNumber = pathNumber;
        this.signId = signId;
    }

    @Ignore
    public SignData(String target, String duration, String resOrg){
        this.target = target;
        this.duration = duration;
    }

    public static List<SignData> createSubSet(List<SignData> signDataList, int id){
        List<SignData> subSet = new ArrayList<>();

        for (SignData s : signDataList) {
            if (s.getSignId() == id) {
                subSet.add(s);
            }
        }
        return subSet;
    }



    public String getFormattedOutput(){
        return target + " " + duration + " " + pathNumber;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target){
        this.target = target;
    }

    public int getSignId() {
        return signId;
    }

    public void setSignId(int signId) {
        this.signId = signId;
    }

    public int getId() {
        return id;
    }

    public void setId(int uid) {
        this.id = uid;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPathNumber() {
        return pathNumber;
    }

    public void setPathNumber(String pathNumber) {
        this.pathNumber = pathNumber;
    }

}
