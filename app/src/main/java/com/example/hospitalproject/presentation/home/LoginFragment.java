package com.example.hospitalproject.presentation.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hospitalproject.R;
import com.example.hospitalproject.room.Patient;
import com.example.hospitalproject.room.database.HospitalDatabase;
import com.example.hospitalproject.room.Staff;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class LoginFragment extends Fragment {

    EditText nameEt;
    EditText passwordEt;
    Button loginButton;
    TextView registerTxtButton;
    HospitalDatabase db;
    View view;
    NavDirections action;

    LoginViewModel loginViewModel;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_login, container, false);

        db = HospitalDatabase.getDatabase(requireContext());

        nameEt = view.findViewById(R.id.name_et);
        passwordEt = view.findViewById(R.id.password_et);
        loginButton = view.findViewById(R.id.loginButton);
        registerTxtButton = view.findViewById(R.id.register_txt_btn);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        loginButton.setOnClickListener(clickView -> {

            String name = nameEt.getText().toString().trim();
            String password = passwordEt.getText().toString().trim();

            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)){

//                checkForStaff();
//
//                checkForPatient();

                if (name.equals("Main") && password.equals("1")){

                    //Navigate to Main Doctor page
                    Navigation.findNavController(clickView).navigate(R.id.action_loginFragment_to_mainDoctorFragment);
                } else if (checkForPatient()) {

                    //Navigate to Patient page
                    Navigation.findNavController(clickView).navigate(action);
                } else if (checkForStaff()){

                    //Navigate to Doctor or Nurse page
                    Navigation.findNavController(view).navigate(action);
                }
                else {

                    Toast.makeText(requireContext(), "There is no such account!", Toast.LENGTH_SHORT).show();
                }
            } else {

                Toast.makeText(requireContext(), "Fill all fields!", Toast.LENGTH_SHORT).show();
            }
        });

        registerTxtButton.setOnClickListener(clickView -> {
                Navigation.findNavController(clickView).navigate(R.id.action_loginFragment_to_registerFragment);
        });


        return view;
    }

    private boolean checkForPatient() {

        List<Patient> patientList = new ArrayList<Patient>();

        loginViewModel.patientsList.observe(getViewLifecycleOwner(), new Observer<List<Patient>>(){

            @Override
            public void onChanged(List<Patient> patients) {
                patientList.addAll(patients);
            }

        });


        String nameInput = nameEt.getText().toString().trim();
        String passwordInput = passwordEt.getText().toString().trim();

        for (Patient patient:patientList){
            if ((patient.pName + " " + patient.pSurname).equals(nameInput) &&
                    (patient.pPassword).equals(passwordInput)){

                action = LoginFragmentDirections.actionLoginFragmentToPatientFragment(patient);

                return true;
            }
        }
        return false;
    }

    private boolean checkForStaff() {

        List<Staff> staffList = db.staffDao().getAll();
        action = LoginFragmentDirections.actionLoginFragmentToRegisterFragment();


        String nameInput = nameEt.getText().toString().trim();
        String passwordInput = passwordEt.getText().toString().trim();

        for (Staff staff:staffList){

            if ( (staff.sName + " " + staff.sSurname).equals(nameInput) &&
                 (staff.sPassword).equals(passwordInput)){

                if (Objects.equals(staff.sPosition, "Doctor")){
                    action = LoginFragmentDirections.actionLoginFragmentToDoctorFragment(staff);
                } else if (Objects.equals(staff.sPosition, "Nurse")){
                    action = LoginFragmentDirections.actionLoginFragmentToNurseFragment(staff);
                }

                return true;
            }
        }

        return false;
    };

}