package com.example.hospitalproject;

import android.app.ActionBar;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginFragment extends Fragment {

    EditText nameEt;
    EditText passwordEt;
    Button loginButton;
    TextView registerTxtButton;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_login, container, false);

        nameEt = view.findViewById(R.id.name_et);
        passwordEt = view.findViewById(R.id.password_et);
        loginButton = view.findViewById(R.id.loginButton);
        registerTxtButton = view.findViewById(R.id.register_txt_btn);

        loginButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                if (!TextUtils.isEmpty(nameEt.getText()) && !TextUtils.isEmpty(passwordEt.getText())){

                    if (nameEt.getText().toString().equals("Sultan Sarygulov") &&
                            passwordEt.getText().toString().equals("123456")){
                        Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_patientFragment);
                    } else if ((nameEt.getText().toString().equals("Lord Doctorus")) &&
                            passwordEt.getText().toString().equals("123456")){
                        Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_mainDoctorFragment);
                    } else {
                        Toast.makeText(requireContext(), "There is no such account!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(requireContext(), "Fill all fields!", Toast.LENGTH_LONG).show();
                }
            }
        });

        registerTxtButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });


        return view;
    }

}