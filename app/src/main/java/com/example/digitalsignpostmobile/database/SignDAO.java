package com.example.digitalsignpostmobile.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.digitalsignpostmobile.model.Sign;

import java.util.List;

@Dao
public interface SignDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSign(Sign... users);

    @Query("SELECT * FROM sign")
    List<Sign> getAll();


}
