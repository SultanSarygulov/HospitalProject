package com.example.hospitalproject.room;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PatientWithDiagnosis {

    @Embedded
    public Patient patient;

    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public List<Diagnosis> diagnosises;

    public PatientWithDiagnosis(Patient patient, List<Diagnosis> diagnosises){
        this.patient = patient;
        this.diagnosises = diagnosises;
    }
}
