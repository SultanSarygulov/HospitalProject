package com.example.hospitalproject.presentation.users;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.hospitalproject.presentation.adapters.PatientRecyclerAdapter;
import com.example.hospitalproject.R;
import com.example.hospitalproject.tools.listeners.DoctorListener;
import com.example.hospitalproject.room.Patient;
import com.example.hospitalproject.room.database.HospitalDatabase;

import java.util.List;

public class NurseFragment extends Fragment implements DoctorListener {

    HospitalDatabase db;

    public NurseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nurse, container, false);

        db = HospitalDatabase.getDatabase(requireContext());

        RecyclerView patientRecyclerView = view.findViewById(R.id.patient_list_nurse);
        PatientRecyclerAdapter adapter = new PatientRecyclerAdapter(requireContext(), this);
        patientRecyclerView.setAdapter(adapter);
        adapter.setList(getPatientsList());

        return view;
    }


    private List<Patient> getPatientsList() {
        List<Patient> patientsList = db.patientDao().getPatients();
        return patientsList;
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