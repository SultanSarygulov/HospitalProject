package com.example.hospitalproject.presentation.users.doctor;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hospitalproject.room.Patient;
import com.example.hospitalproject.room.PatientDao;
import com.example.hospitalproject.room.database.HospitalDatabase;
import com.example.hospitalproject.room.repository.PatientRepository;

import java.util.List;

public class DoctorViewModel extends AndroidViewModel {

    private PatientRepository repository;

    public LiveData<List<Patient>> readPatients;
    public String patientNum;

    public DoctorViewModel(@NonNull Application application) {
        super(application);

        PatientDao patientDao = HospitalDatabase.getDatabase(application).patientDao();
        repository = new PatientRepository(patientDao);

        readPatients = repository.readPatients;
        patientNum = repository.patientNum;

    }

    public void deletePatient(Patient patient){repository.deletePatient(patient);}

    public void addPatient(Patient patient){ repository.addPatient(patient);}
}
