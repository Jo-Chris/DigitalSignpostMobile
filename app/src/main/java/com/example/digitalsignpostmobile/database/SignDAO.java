package com.example.digitalsignpostmobile.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.example.digitalsignpostmobile.model.Sign;

@Dao
public interface SignDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertSign(Sign... users);

    @Insert
    void insert(Sign... signs);

    @Update
    void update(Sign... signs);

    @Delete
    void delete(Sign... signs);


}
