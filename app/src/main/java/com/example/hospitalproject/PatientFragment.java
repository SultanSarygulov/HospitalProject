package com.example.hospitalproject;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hospitalproject.room.Diagnosis;
import com.example.hospitalproject.room.Patient;
import com.example.hospitalproject.room.database.HospitalDatabase;

import java.util.List;

public class PatientFragment extends Fragment {

    View view;
    HospitalDatabase db;
    private PatientFragmentArgs args;
    Patient currentPatient;
    TextView nameTxt;
    TextView surnameTxt;

    public PatientFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_patient, container, false);

        nameTxt = view.findViewById(R.id.name_txt);
        surnameTxt = view.findViewById(R.id.surname_txt);

        args = PatientFragmentArgs.fromBundle(getArguments());
        currentPatient = args.getCurrentPatient();

        nameTxt.setText(currentPatient.pName);
        surnameTxt.setText(currentPatient.pSurname);

        db = HospitalDatabase.getDatabase(requireContext());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView diagnosisRecyclerView = view.findViewById(R.id.diagnosis_list);
        DiagnosisAdapter diagnosisAdapter = new DiagnosisAdapter(requireContext());
        diagnosisRecyclerView.setAdapter(diagnosisAdapter);
        diagnosisAdapter.setList(getDiagnosisList());
    }

    private List<Diagnosis> getDiagnosisList() {
        List<Diagnosis> diagnosisList = db.diagnosisDao().getDiagnosis(currentPatient.pid);

        Log.d("Nigger", "Diagnosis " + diagnosisList.size());

        return diagnosisList;
    }
}