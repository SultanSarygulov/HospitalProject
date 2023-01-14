package com.example.hospitalproject.presentation.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.hospitalproject.room.Patient;
import com.example.hospitalproject.room.PatientDao;
import com.example.hospitalproject.room.database.HospitalDatabase;
import com.example.hospitalproject.room.repository.PatientRepository;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {

    private PatientRepository patientRepository;
    public LiveData<List<Patient>> patientsList;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        PatientDao patientDao = HospitalDatabase.getDatabase(application).patientDao();
        patientRepository = new PatientRepository(patientDao);

        patientsList = patientRepository.readPatients;
    }
}
