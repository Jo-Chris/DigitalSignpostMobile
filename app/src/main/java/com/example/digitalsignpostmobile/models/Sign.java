package com.example.digitalsignpostmobile.models;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import java.io.Serializable;


@Entity(foreignKeys = @ForeignKey(
        entity = SignImage.class,
        parentColumns = "id",
        childColumns = "itemId",
        onDelete = ForeignKey.CASCADE),
        indices = {@Index("itemId")})

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
    @ColumnInfo(name = "resOrgAvailable")
    private boolean resOrgAvailable;
    @ColumnInfo(name = "image")
    private String filepath; // convert to Bitmap, or ignore completely...
    @ColumnInfo(name = "itemId")
    private int itemId;

    private static final String TAG = "Sign";

    @Ignore
    public Sign(String title, String direction, int rowCount, boolean hasResOrg, String filepath, int itemId) {
        this.title = title;
        this.direction = direction;
        this.rowCount = rowCount;
        this.resOrgAvailable = hasResOrg;
        this.filepath = filepath;
        this.itemId = itemId;
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

    public boolean isResOrgAvailable() {
        return resOrgAvailable;
    }

    public void setResOrgAvailable(boolean resOrgAvailable) {
        this.resOrgAvailable = resOrgAvailable;
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

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public static String getTAG() {
        return TAG;
    }

    @Override
    @NonNull
    public String toString() {
        return "Sign{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", direction='" + direction + '\'' +
                ", rowCount=" + rowCount +
                ", resOrgAvailable=" + resOrgAvailable +
                ", filepath='" + filepath + '\'' +
                '}';
    }
}
