package com.example.hospitalproject.presentation.users;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hospitalproject.presentation.adapters.DiagnosisAdapter;
import com.example.hospitalproject.R;
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
    TextView dateBirth;
    TextView heightTxt;
    TextView weightTxt;
    TextView bloodType;

    Button updatePatientButton;
    Dialog dialog;


    //Dialog views
    EditText updateNameEt;
    EditText updateSurnameEt;
    EditText updateDateEt;
    EditText updateHeightEt;
    EditText updateWeightEt;
    EditText updateBloodGroupEt;

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
        updatePatientButton = view.findViewById(R.id.updatePatientButton);
        dateBirth = view.findViewById(R.id.birth_date_txt);
        heightTxt = view.findViewById(R.id.height_txt);
        weightTxt = view.findViewById(R.id.weight_txt);
        bloodType = view.findViewById(R.id.blood_group_txt);

        dialog = new Dialog(requireContext());

        args = PatientFragmentArgs.fromBundle(getArguments());
        currentPatient = args.getCurrentPatient();

        db = HospitalDatabase.getDatabase(requireContext());

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        nameTxt.setText(currentPatient.pName);
        surnameTxt.setText(currentPatient.pSurname);
        dateBirth.setText(currentPatient.birthDate);
        heightTxt.setText("Рост: " + currentPatient.height + " см");
        weightTxt.setText("Вес: " + currentPatient.weight+ " кг");
        bloodType.setText("Группа крови: " +currentPatient.bloodType);
        
        updatePatientButton.setOnClickListener(clickView ->{
            showDialog();
        });

        RecyclerView diagnosisRecyclerView = view.findViewById(R.id.diagnosis_list);
        DiagnosisAdapter diagnosisAdapter = new DiagnosisAdapter(requireContext());
        diagnosisRecyclerView.setAdapter(diagnosisAdapter);
        diagnosisAdapter.setList(getDiagnosisList());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void showDialog() {
        dialog.setContentView(R.layout.update_info_dialog);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(true);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

        updateNameEt = dialog.findViewById(R.id.update_name_et);
        updateSurnameEt = dialog.findViewById(R.id.update_surname_et);
        updateDateEt = dialog.findViewById(R.id.update_birth_date_et);
        updateHeightEt = dialog.findViewById(R.id.update_height_et);
        updateWeightEt = dialog.findViewById(R.id.update_weight_et);
        updateBloodGroupEt = dialog.findViewById(R.id.update_blood_group_et);

        updateNameEt.setHint(currentPatient.pName);
        updateSurnameEt.setHint(currentPatient.pSurname);


        Button confirmUpdateButton = dialog.findViewById(R.id.confirmUpdateButton);

        confirmUpdateButton.setOnClickListener(clickView->{
            updatePatient();
        });

        dialog.show();

    }

    private void updatePatient() {
        Patient currentPatient = args.getCurrentPatient();

        if (!TextUtils.isEmpty(updateNameEt.getText().toString().trim())){
            currentPatient.pName = updateNameEt.getText().toString().trim();
        }

        if (!TextUtils.isEmpty(updateSurnameEt.getText().toString().trim())){
            currentPatient.pSurname = updateSurnameEt.getText().toString().trim();
        }

        currentPatient.birthDate = updateDateEt.getText().toString();
        currentPatient.height = Integer.parseInt(updateHeightEt.getText().toString());
        currentPatient.weight = Integer.parseInt(updateWeightEt.getText().toString());
        currentPatient.bloodType = updateBloodGroupEt.getText().toString();

        db.patientDao().updatePatientInfo(currentPatient);

        dialog.dismiss();

        updateInfo();
    }

    private void updateInfo() {
        nameTxt.setText(currentPatient.pName);
        surnameTxt.setText(currentPatient.pSurname);
        dateBirth.setText(currentPatient.birthDate);
        heightTxt.setText("Рост: " + currentPatient.height + " см");
        weightTxt.setText("Вес: " + currentPatient.weight+ " кг");
        bloodType.setText("Группа крови: " +currentPatient.bloodType);
    }

    private List<Diagnosis> getDiagnosisList() {
        List<Diagnosis> diagnosisList = db.diagnosisDao().getDiagnosis(currentPatient.id);

        Log.d("Nigger", "Diagnosis " + diagnosisList.size());

        return diagnosisList;
    }
}