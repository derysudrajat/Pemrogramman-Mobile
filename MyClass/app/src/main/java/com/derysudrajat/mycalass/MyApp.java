package com.derysudrajat.mycalass;

import android.annotation.SuppressLint;
import android.app.Application;

import androidx.room.Room;

@SuppressLint("Registered")
public class MyApp extends Application {

    public static final String DATABASE_NAME = "crew";

    public static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
    }
}
