package com.example.hospitalproject.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PatientDao {

    @Delete
    void deleteStaff(Patient patient);

    @Query("SELECT COUNT(*) FROM patient_table")
    String getPatientNum();

    @Query("SELECT * FROM patient_table")
    List<Patient> getPatients();
}
