package com.example.hospitalproject.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPatient(Patient patient);

    @Delete
    void deletePatient(Patient patient);

    @Query("SELECT COUNT(*) FROM patient_table")
    String getPatientNum();

    @Query("SELECT * FROM patient_table")
    List<Patient> getPatients();
}
