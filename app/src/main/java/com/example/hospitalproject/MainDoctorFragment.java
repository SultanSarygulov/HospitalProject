package com.example.hospitalproject;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hospitalproject.room.HospitalDatabase;
import com.example.hospitalproject.room.Staff;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class MainDoctorFragment extends Fragment {

    Button addDoctor;

    public MainDoctorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_doctor, container, false);

        addDoctor = view.findViewById(R.id.addDoctorButton);

        RecyclerView nurseRecyclerView = view.findViewById(R.id.nurse_list);
        NurseRecyclerAdapter nurseAdapter = new NurseRecyclerAdapter(requireContext());
        nurseRecyclerView.setAdapter(nurseAdapter);

        RecyclerView doctorRecycler = view.findViewById(R.id.doctor_list);
        DoctorRecyclerAdapter doctorAdapter = new DoctorRecyclerAdapter(requireContext());
        doctorAdapter.setList(getDoctorsList());
        doctorRecycler.setAdapter(doctorAdapter);


        return view;
    }

    private List<Staff> getDoctorsList() {
        HospitalDatabase db = HospitalDatabase.getDatabase(requireContext());
        List<Staff> doctorsList = db.staffDao().getDoctors();

        return doctorsList;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        addDoctor.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                addDoctor();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addDoctor() {
        HospitalDatabase db = HospitalDatabase.getDatabase(requireContext());

        Staff newDoctor = new Staff();
        newDoctor.sName = "Fariza";
        newDoctor.sSurname = "Norman";
        newDoctor.sPosition = "Doctor";
        newDoctor.sSalary = 1000;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        newDoctor.sEmploymentDate = dtf.format(now);

        db.staffDao().addStaff(newDoctor);
    }
}