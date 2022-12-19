package com.example.hospitalproject.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

@Dao
public interface DiagnosisDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addDiagnosis(Diagnosis diagnosis);
}
