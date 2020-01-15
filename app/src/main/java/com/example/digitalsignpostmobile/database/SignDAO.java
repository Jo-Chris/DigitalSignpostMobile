package com.example.digitalsignpostmobile.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.digitalsignpostmobile.model.Sign;

import java.util.List;

import static android.icu.text.MessagePattern.ArgType.SELECT;

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
    List<Sign> loadAllUsers();

}