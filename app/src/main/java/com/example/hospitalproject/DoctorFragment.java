package com.example.hospitalproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hospitalproject.listeners.DoctorListener;
import com.example.hospitalproject.room.Patient;
import com.example.hospitalproject.room.Staff;
import com.example.hospitalproject.room.database.HospitalDatabase;

import java.util.List;

public class DoctorFragment extends Fragment implements DoctorListener {

    TextView doctorTV;
    View view;
    private DoctorFragmentArgs args;
    private Staff currentDoctor;
    HospitalDatabase db;

    public DoctorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_doctor, container, false);

        db = HospitalDatabase.getDatabase(requireContext());

        doctorTV = view.findViewById(R.id.doctor);
        args = DoctorFragmentArgs.fromBundle(getArguments());
        currentDoctor = args.getCurrentDoctor();

        RecyclerView patientRecyclerView = view.findViewById(R.id.patient_list);
        PatientRecyclerAdapter adapter = new PatientRecyclerAdapter(requireContext(), this);
        patientRecyclerView.setAdapter(adapter);
        adapter.setList(getPatientsList());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String fullName = currentDoctor.sName + " " + currentDoctor.sSurname + " " + currentDoctor.sid;

        doctorTV.setText(fullName);
    }

    private List<Patient> getPatientsList() {
        List<Patient> patientsList = db.patientDao().getPatients();
        return  patientsList;
    }

    @Override
    public void deletePatient(Patient patient) {
        db.patientDao().deleteStaff(patient);
        Toast.makeText(requireContext(), "Patient deleted", Toast.LENGTH_LONG).show();
    }

    @Override
    public void addDiagnosisToPatient() {
        Toast.makeText(requireContext(), "Diagnosis added", Toast.LENGTH_LONG).show();
    }
}