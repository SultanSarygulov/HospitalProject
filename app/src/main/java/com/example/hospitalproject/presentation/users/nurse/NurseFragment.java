package com.example.hospitalproject.presentation.users.nurse;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hospitalproject.presentation.adapters.PatientRecyclerAdapter;
import com.example.hospitalproject.R;
import com.example.hospitalproject.presentation.users.doctor.DoctorViewModel;
import com.example.hospitalproject.tools.listeners.DoctorListener;
import com.example.hospitalproject.room.Patient;
import com.example.hospitalproject.room.database.HospitalDatabase;

import java.util.List;

public class NurseFragment extends Fragment implements DoctorListener {

    HospitalDatabase db;
    DoctorViewModel doctorViewModel;
    PatientRecyclerAdapter patientAdapter;

    public NurseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nurse, container, false);

        db = HospitalDatabase.getDatabase(requireContext());

        doctorViewModel = ViewModelProviders.of(this).get(DoctorViewModel.class);

        RecyclerView patientRecyclerView = view.findViewById(R.id.patient_list_nurse);
        patientAdapter = new PatientRecyclerAdapter(requireContext(), this);
        patientRecyclerView.setAdapter(patientAdapter);
        getPatientsList();

        return view;
    }


    private void getPatientsList() {
        doctorViewModel.readPatients.observe(getViewLifecycleOwner(), new Observer<List<Patient>>(){

            @Override
            public void onChanged(List<Patient> patients) {
                patientAdapter.setList(patients);
            }

        });
    }

    @Override
    public void deletePatient(Patient patient) {
        Toast.makeText(requireContext(), "You have no rights to do that!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addDiagnosisToPatient(long id) {
        Toast.makeText(requireContext(), "You have no rights to do that!", Toast.LENGTH_SHORT).show();
    }
}