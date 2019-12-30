package com.derysudrajat.mycalass;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;
@Dao
public interface CrewDao {

    @Query("SELECT * FROM crew")
    List<Crew> getAllCrew();

    @Insert
    void insertCrew(Crew crew);

    @Delete
    void deleteCrew(Crew crew);

    @Update
    void updateCrew(Crew crew);
}
