package com.example.digitalsignpostmobile.database.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.digitalsignpostmobile.models.Sign;
import com.example.digitalsignpostmobile.models.SignData;

import java.util.List;

@Dao
public interface SignDataDAO {

    @Insert
    void insert(SignData... signData);

    @Update
    void update(SignData... signData);

    @Delete
    void delete(SignData... signData);

    @Query("SELECT * FROM signdata WHERE signId = :id")
    List<SignData> getId(int id);
}
