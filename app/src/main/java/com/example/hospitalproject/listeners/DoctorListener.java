package com.example.hospitalproject.listeners;

import com.example.hospitalproject.room.Patient;

public interface DoctorListener {

    void deletePatient(Patient patient);

    void addDiagnosisToPatient();
}
