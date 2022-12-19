package com.example.hospitalproject.home;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hospitalproject.R;
import com.example.hospitalproject.room.Patient;
import com.example.hospitalproject.room.database.HospitalDatabase;

public class RegisterFragment extends Fragment {

    TextView loginTxtButton;
    Button registerButton;
    EditText registerNameEt;
    EditText registerPasswordEt;
    HospitalDatabase db;
    View view;

    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_register, container, false);

        loginTxtButton = view.findViewById(R.id.login);
        registerButton = view.findViewById(R.id.registerButton);
        registerNameEt = view.findViewById(R.id.register_name_et);
        registerPasswordEt = view.findViewById(R.id.register_password_et);

        db = HospitalDatabase.getDatabase(requireContext());

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loginTxtButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment);
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String fullname = registerNameEt.getText().toString().trim();
                String password = registerPasswordEt.getText().toString().trim();

                if (!TextUtils.isEmpty(fullname) && !TextUtils.isEmpty(password)){
                    Patient newPatient = new Patient();
                    newPatient.pName = fullname.split(" ")[0];
                    newPatient.pSurname = fullname.split(" ")[1];
                    newPatient.pPassword = password;

                    db.patientDao().addPatient(newPatient);
                }
            }
        });
    }
}