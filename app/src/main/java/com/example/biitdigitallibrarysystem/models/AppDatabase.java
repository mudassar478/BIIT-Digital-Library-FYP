package com.example.biitdigitallibrarysystem.models;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao getUserDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null || !INSTANCE.isOpen()) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "favourite_files.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
