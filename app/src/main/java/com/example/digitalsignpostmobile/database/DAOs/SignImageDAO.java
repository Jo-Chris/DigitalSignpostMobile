package com.example.digitalsignpostmobile.database.DAOs;

import com.example.digitalsignpostmobile.models.SignImage;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface SignImageDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertImage(SignImage... images);

    @Insert
    void insert(SignImage... images);

    @Update
    void update(SignImage... images);

    @Delete
    void delete(SignImage... images);

    @Query("SELECT * FROM signimage")
    List<SignImage> getAll();

}
