package com.example.hospitalproject;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hospitalproject.listeners.DoctorListener;
import com.example.hospitalproject.room.Diagnosis;
import com.example.hospitalproject.room.Patient;
import com.example.hospitalproject.room.Staff;
import com.example.hospitalproject.room.database.HospitalDatabase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DoctorFragment extends Fragment implements DoctorListener {

    TextView doctorTV;
    View view;
    private DoctorFragmentArgs args;
    private Staff currentDoctor;
    HospitalDatabase db;
    Dialog dialog;

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
        dialog = new Dialog(requireContext());

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
        db.patientDao().deletePatient(patient);
        Toast.makeText(requireContext(), "Patient deleted", Toast.LENGTH_LONG).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void addDiagnosisToPatient(int id) {
        initDialog(id);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initDialog(int id) {

        dialog.setContentView(R.layout.add_diagnosis_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        EditText enterIllnessEt = dialog.findViewById(R.id.enterIllnessEt);
        Button confirmAddingButton = dialog.findViewById(R.id.confirmAddingButton);

        confirmAddingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Nigger", "Button clicked");
                Diagnosis newDiagnosis = new Diagnosis();

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDateTime now = LocalDateTime.now();

                newDiagnosis.patientId = id;
                newDiagnosis.illness = enterIllnessEt.getText().toString().trim();
                newDiagnosis.assignmentDate = dtf.format(now);

                db.diagnosisDao().addDiagnosis(newDiagnosis);

                Toast.makeText(requireContext(), "Diagnosis added", Toast.LENGTH_LONG).show();

                dialog.dismiss();
            }
        });


        dialog.show();
    }
}