package com.example.digitalsignpostmobile.database.DAOs;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.digitalsignpostmobile.models.Sign;

import java.util.List;

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

    @Query("SELECT * FROM sign")
    List<Sign> getAll();

    /*
     returns all Signs from one given SignImage
     */
    @Query("SELECT * FROM sign WHERE itemId = :id")
    List<Sign> getId(int id);

}