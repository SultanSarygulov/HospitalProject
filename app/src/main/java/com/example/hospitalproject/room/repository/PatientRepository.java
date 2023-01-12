package com.example.hospitalproject.room.repository;

import androidx.lifecycle.LiveData;

import com.example.hospitalproject.room.Patient;
import com.example.hospitalproject.room.PatientDao;

import java.util.List;

public class PatientRepository {

    private PatientDao patientDao;
    public String patientNum;
    public LiveData<List<Patient>> readPatients;

    public PatientRepository(PatientDao patientDao){

        this.patientDao = patientDao;
        patientNum = patientDao.getPatientNum();
        readPatients = patientDao.getPatients();
    }

    public void deletePatient(Patient patient){ patientDao.deletePatient(patient);}

    public void addPatient(Patient patient){ patientDao.addPatient(patient);}
}
