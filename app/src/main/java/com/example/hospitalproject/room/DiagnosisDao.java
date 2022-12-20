package com.example.hospitalproject.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DiagnosisDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addDiagnosis(Diagnosis diagnosis);

    @Query("SELECT * FROM diagnosis_table WHERE patient_id = :id")
    List<Diagnosis> getDiagnosis(long id);
}
