package com.example.hospitalproject.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = "diagnosis_table", foreignKeys = {@ForeignKey
        (entity = Patient.class,
                parentColumns = "id",
                childColumns = "patient_id",
                onDelete = ForeignKey.CASCADE
        )})
public class Diagnosis  {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "illness")
    public String illness;

    @ColumnInfo(name = "patient_id", index = true)
    public long patientId;

    @ColumnInfo(name = "assignment_date")
    public String assignmentDate;

    @ColumnInfo(name = "treatment_date")
    public String treatmentDate;


}
