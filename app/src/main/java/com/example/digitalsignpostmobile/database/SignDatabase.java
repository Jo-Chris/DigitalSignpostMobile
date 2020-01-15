package com.example.digitalsignpostmobile.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.digitalsignpostmobile.model.Sign;
import com.example.digitalsignpostmobile.model.SignData;

@Database(entities = {Sign.class, SignData.class}, version = 1)
public abstract class SignDatabase extends RoomDatabase {

    private static SignDatabase INSTANCE = null;

    public static SignDatabase getInstance(Context context){
        return INSTANCE == null ? Room.databaseBuilder(context, SignDatabase.class, "signpost-db")
    }

    public abstract SignDAO signDao();
    public abstract SignDataDAO signDataDAO();

}

