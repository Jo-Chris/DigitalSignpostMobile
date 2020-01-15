package com.example.digitalsignpostmobile.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.digitalsignpostmobile.model.SignData;

@Dao
public interface SignDataDAO {

    @Insert
    void insert(SignData... signData);

    @Update
    void update(SignData... signData);

    @Delete
    void delete(SignData... signData);
}
