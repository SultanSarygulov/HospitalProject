package com.example.hospitalproject.presentation.users.doctor;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hospitalproject.presentation.adapters.PatientRecyclerAdapter;
import com.example.hospitalproject.R;
import com.example.hospitalproject.presentation.users.mainDoctor.MainDoctorViewModel;
import com.example.hospitalproject.tools.listeners.DoctorListener;
import com.example.hospitalproject.room.Diagnosis;
import com.example.hospitalproject.room.Patient;
import com.example.hospitalproject.room.Staff;
import com.example.hospitalproject.room.database.HospitalDatabase;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DoctorFragment extends Fragment implements DoctorListener {

    TextView doctorTV;
    View view;
    private DoctorFragmentArgs args;
    private Staff currentDoctor;
    HospitalDatabase db;
    Dialog dialog;
    PatientRecyclerAdapter patientAdapter;

    DoctorViewModel doctorViewModel;

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





        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        doctorViewModel = ViewModelProviders.of(this).get(DoctorViewModel.class);

        String fullName = currentDoctor.sName + " " + currentDoctor.sSurname;

        doctorTV.setText(fullName);

        RecyclerView patientRecyclerView = view.findViewById(R.id.patient_list);
        patientAdapter = new PatientRecyclerAdapter(requireContext(), this);
        patientRecyclerView.setAdapter(patientAdapter);

        getPatientsList();
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
        doctorViewModel.deletePatient(patient);
        Toast.makeText(view.getContext(), "Patient was deleted", Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void addDiagnosisToPatient(long id) {
        initDialog(id);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void initDialog(long id) {

        dialog = new Dialog(requireContext());

        dialog.setContentView(R.layout.add_diagnosis_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        EditText enterIllnessEt = dialog.findViewById(R.id.enterIllnessEt);
        Button confirmAddingButton = dialog.findViewById(R.id.confirmAddingButton);

        confirmAddingButton.setOnClickListener(view ->{
            Log.d("Nigger", "Button clicked");
            Diagnosis newDiagnosis = new Diagnosis();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            LocalDateTime now = LocalDateTime.now();

            newDiagnosis.patientId = id;
            newDiagnosis.illness = enterIllnessEt.getText().toString().trim();
            newDiagnosis.assignmentDate = dtf.format(now);

            db.diagnosisDao().addDiagnosis(newDiagnosis);

            Toast.makeText(requireContext(), "Diagnosis added", Toast.LENGTH_SHORT).show();

            dialog.dismiss();
        });

        dialog.show();
    }
}