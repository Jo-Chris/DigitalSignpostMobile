package com.example.digitalsignpostmobile.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.digitalsignpostmobile.model.Sign;

@Database(entities = {Sign.class}, version = 1)
public abstract class SignDatabase extends RoomDatabase {

    public abstract SignDAO signDao();

}

