package com.example.hospitalproject.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PatientDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addPatient(Patient patient);

    @Delete
    void deletePatient(Patient patient);

    @Update
    void updatePatientInfo(Patient patient);

    @Query("SELECT COUNT(*) FROM patient_table")
    String getPatientNum();

    @Query("SELECT * FROM patient_table")
    List<Patient> getPatients();
}
