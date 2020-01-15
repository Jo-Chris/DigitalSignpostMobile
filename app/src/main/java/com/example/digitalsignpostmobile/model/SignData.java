package com.example.digitalsignpostmobile.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(foreignKeys = @ForeignKey(entity = Sign.class,
        parentColumns = "id",
        childColumns = "userId",
        onDelete = ForeignKey.CASCADE))

public class SignData implements Serializable {

    @PrimaryKey
    private int uid;
    @ColumnInfo(name = "target")
    private String target;
    @ColumnInfo(name = "duration")
    private String duration;
    @ColumnInfo(name = "pathNumber")
    private String pathNumber;
    @ColumnInfo(name = "resOrg")
    private String resOrg;
    @ColumnInfo(name = "signId")
    private int signId;

    public SignData(){

    }

    public SignData(String target, String duration, String pathNumber, String resOrg, int signId){
        this.target = target;
        this.duration = duration;
        this.pathNumber = pathNumber;
        this.resOrg = resOrg;
    }

    public SignData(String target, String duration, String resOrg){
        this.target = target;
        this.duration = duration;
        this.resOrg = resOrg;
    }

    public String getFormattedOutput(){
        return target + " " + duration + " " + pathNumber;
    }

    public String getTarget() {
        return target;
    }


}
