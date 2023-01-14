package com.example.hospitalproject.room.repository;

import androidx.lifecycle.LiveData;

import com.example.hospitalproject.room.Diagnosis;
import com.example.hospitalproject.room.DiagnosisDao;
import com.example.hospitalproject.room.Patient;
import com.example.hospitalproject.room.PatientDao;

import java.util.List;

public class DiagnosisRepository {

    private DiagnosisDao diagnosisDao;

    public DiagnosisRepository(DiagnosisDao diagnosisDao){

        this.diagnosisDao = diagnosisDao;
    }

    public LiveData<List<Diagnosis>> getDiagnosisList(long id){
        return diagnosisDao.getDiagnosis(id);
    }

    public void addDiagnosis(Diagnosis diagnosis){ diagnosisDao.addDiagnosis(diagnosis);}
}
