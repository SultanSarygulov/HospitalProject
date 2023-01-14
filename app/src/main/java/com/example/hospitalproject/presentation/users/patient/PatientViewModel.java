package com.example.hospitalproject.presentation.users.patient;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hospitalproject.room.Diagnosis;
import com.example.hospitalproject.room.DiagnosisDao;
import com.example.hospitalproject.room.Patient;
import com.example.hospitalproject.room.PatientDao;
import com.example.hospitalproject.room.database.HospitalDatabase;
import com.example.hospitalproject.room.repository.DiagnosisRepository;
import com.example.hospitalproject.room.repository.PatientRepository;

import java.util.List;

public class PatientViewModel extends AndroidViewModel {

    PatientRepository patientRepository;
    DiagnosisRepository diagnosisRepository;
    LiveData<List<Diagnosis>> diagnosisList;

    public PatientViewModel(@NonNull Application application) {
        super(application);

        PatientDao patientDao = HospitalDatabase.getDatabase(application).patientDao();
        patientRepository = new PatientRepository(patientDao);

        DiagnosisDao diagnosisDao = HospitalDatabase.getDatabase(application).diagnosisDao();
        diagnosisRepository = new DiagnosisRepository(diagnosisDao);

    }

    public void updatePatientInfo(Patient patient){
        patientRepository.updatePatientInfo(patient);
    }

    public LiveData<List<Diagnosis>> getDiagnosis(long id){
        return diagnosisRepository.getDiagnosisList(id);
    }


}
