package com.derysudrajat.mycalass;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Crew.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract CrewDao crewDao();
}
