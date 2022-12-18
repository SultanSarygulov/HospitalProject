package com.example.hospitalproject.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "diagnosis_table")
public class Diagnosis  {
    @PrimaryKey(autoGenerate = true)
    public int did;

    @ColumnInfo(name = "illness_id")
    public int illnessId;

    @ColumnInfo(name = "patient_id")
    public int patientId;

    @ColumnInfo(name = "assignment_date")
    public String assignmentDate;

    @ColumnInfo(name = "treatment_date")
    public String treatmentDate;

}
