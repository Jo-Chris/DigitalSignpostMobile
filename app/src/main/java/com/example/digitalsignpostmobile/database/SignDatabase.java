package com.example.digitalsignpostmobile.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.digitalsignpostmobile.model.Sign;
import com.example.digitalsignpostmobile.model.SignData;

@Database(entities = {Sign.class, SignData.class}, version = 1, exportSchema = false)
public abstract class SignDatabase extends RoomDatabase {

    private static SignDatabase INSTANCE = null;

    public static synchronized SignDatabase getInstance(Context context){                               // remove this @ production!
        return INSTANCE == null ? Room.databaseBuilder(context, SignDatabase.class, "signpost-db").allowMainThreadQueries().build() : INSTANCE;
    }

    public abstract SignDAO signDao();

    public abstract SignDataDAO signDataDAO();

}

