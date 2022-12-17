package com.example.hospitalproject.room;

import androidx.room.Dao;
import androidx.room.Query;

@Dao
public interface PatientDao {

    @Query("SELECT COUNT(*) FROM patient_table")
    String getPatientNum();
}
