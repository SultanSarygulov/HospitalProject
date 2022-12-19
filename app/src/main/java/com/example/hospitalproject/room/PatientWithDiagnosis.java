package com.example.hospitalproject.room;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class PatientWithDiagnosis {

    @Embedded
    public Patient patient;

    @Relation(
            parentColumn = "pid",
            entityColumn = "did"
    )
    public List<Diagnosis> diagnosises;

    public PatientWithDiagnosis(Patient patient, List<Diagnosis> diagnosises){
        this.patient = patient;
        this.diagnosises = diagnosises;
    }
}
