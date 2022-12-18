package com.example.hospitalproject.room;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IllnessDao {

    @Query("SELECT name FROM illness_table")
    List<String> getIllnesses();
}
