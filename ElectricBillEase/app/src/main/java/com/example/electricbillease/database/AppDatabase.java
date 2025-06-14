package com.example.electricbillease.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {BillRecord.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract BillDao billDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "bill_db")
                    .allowMainThreadQueries() // For simple apps only
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
