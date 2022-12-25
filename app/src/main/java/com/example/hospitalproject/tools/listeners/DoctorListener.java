package com.example.hospitalproject.tools.listeners;

import com.example.hospitalproject.room.Patient;

public interface DoctorListener {

    void deletePatient(Patient patient);

    void addDiagnosisToPatient(long id);
}
