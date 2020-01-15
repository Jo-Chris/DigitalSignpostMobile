package com.example.digitalsignpostmobile.model;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.io.Serializable;

@Entity(indices = {@Index("id")})
public class Sign implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "direction")
    private String direction;
    @ColumnInfo(name = "rowCount")
    private int rowCount;
    @ColumnInfo(name = "hasResOrg")
    private boolean hasResOrg;
    @ColumnInfo(name = "image")
    private String filepath; // convert to Bitmap, or ignore completely...

    private static final String TAG = "Sign";

    @Ignore
    public Sign(String title, String direction, int rowCount, boolean hasResOrg, String filepath) {
        this.title = title;
        this.direction = direction;
        this.rowCount = rowCount;
        this.hasResOrg = hasResOrg;
        this.filepath = filepath;
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

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String image) {
        this.filepath = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    @NonNull
    public String toString() {
        return "Sign{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", direction='" + direction + '\'' +
                ", rowCount=" + rowCount +
                ", hasResOrg=" + hasResOrg +
                ", filepath='" + filepath + '\'' +
                '}';
    }
}
