package com.example.hospitalproject.presentation.users.mainDoctor;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hospitalproject.R;
import com.example.hospitalproject.tools.listeners.Listeners;
import com.example.hospitalproject.presentation.adapters.DoctorRecyclerAdapter;
import com.example.hospitalproject.presentation.adapters.NurseRecyclerAdapter;
import com.example.hospitalproject.room.database.HospitalDatabase;
import com.example.hospitalproject.room.Staff;

import java.util.List;
import java.util.Objects;
import java.util.Observable;
import java.util.function.Consumer;

import androidx.lifecycle.Observer;

public class MainDoctorFragment extends Fragment implements Listeners {

    Button addDoctor;
    Button addNurse;
    HospitalDatabase db;
    TextView mostPayedStaff;
    TextView leastPayedStaff;
    TextView patientNumTV;
    String mostPaidPersonTxt;
    String leastPaidPersonTxt;

    DoctorRecyclerAdapter doctorAdapter;
    NurseRecyclerAdapter nurseAdapter;

    MainDoctorViewModel mainDoctorViewModel;

    public MainDoctorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_doctor, container, false);

        db = HospitalDatabase.getDatabase(requireContext());


        addNurse = view.findViewById(R.id.addNurseButton);
        addDoctor = view.findViewById(R.id.addDoctorButton);

        mostPayedStaff = view.findViewById(R.id.name_most_earning_doctor);
        leastPayedStaff = view.findViewById(R.id.name_least_earning_doctor);

        patientNumTV = view.findViewById(R.id.patient_num);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set ViewModel
        mainDoctorViewModel = ViewModelProviders.of(this).get(MainDoctorViewModel.class);

        // Set RecyclerViews for Doctors and Nurses
        RecyclerView doctorRecycler = view.findViewById(R.id.doctor_list);
        doctorAdapter = new DoctorRecyclerAdapter(requireContext(), this);
        doctorRecycler.setAdapter(doctorAdapter);

        RecyclerView nurseRecyclerView = view.findViewById(R.id.nurse_list);
        nurseAdapter = new NurseRecyclerAdapter(requireContext(), this);
        nurseRecyclerView.setAdapter(nurseAdapter);

        // Set LiveData observer for Doctor RecyclerView
        mainDoctorViewModel.readDoctors.observe(getViewLifecycleOwner(), new Observer<List<Staff>>(){

            @Override
            public void onChanged(List<Staff> staffList) {
                doctorAdapter.setList(staffList);
            }
        });

        // Set LiveData observer for Nurse RecyclerView
        mainDoctorViewModel.readNurses.observe(getViewLifecycleOwner(), new Observer<List<Staff>>(){

            @Override
            public void onChanged(List<Staff> staffList) {
                nurseAdapter.setList(staffList);
            }
        });


        addNurse.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                addStaff(view, "Nurse");
            }
        });

        addDoctor.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                addStaff(view, "Doctor");
            }
        });

        nameMostPayed();

        nameLeastPayed();

        getNumberOfPatients();
    }

    @Override
    public void deleteStaff(Staff staff) {

        mainDoctorViewModel.deleteStaff(staff);
        Toast.makeText(requireContext(), staff.sPosition + " was deleted", Toast.LENGTH_SHORT).show();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addStaff(View view, String position) {

        NavDirections action = MainDoctorFragmentDirections.actionMainDoctorFragmentToAddStaffFragment(position);

        Navigation.findNavController(view).navigate(action);
    }

    private void nameMostPayed(){

        mainDoctorViewModel.readMostPaid.observe(getViewLifecycleOwner(), new Observer<Staff>(){

            @Override
            public void onChanged(Staff staff) {
                if (staff != null){
                    mostPaidPersonTxt = staff.sPosition + " " +
                            staff.sName + " " +
                            staff.sSurname + " - $" +
                            staff.sSalary;
                } else {
                    mostPaidPersonTxt = "The list is empty(";
                }
                mostPayedStaff.setText(mostPaidPersonTxt);
            }
        });
    }

    private void nameLeastPayed(){

        mainDoctorViewModel.readLeastPaid.observe(getViewLifecycleOwner(), new Observer<Staff>(){

            @Override
            public void onChanged(Staff staff) {
                if (staff != null){
                    leastPaidPersonTxt = staff.sPosition + " " +
                            staff.sName + " " +
                            staff.sSurname + " - $" +
                            staff.sSalary;
                } else {
                    leastPaidPersonTxt = "The list is empty(";
                }

                leastPayedStaff.setText(leastPaidPersonTxt);
            }
        });
    }

    private void getNumberOfPatients(){
        String patientNum = db.patientDao().getPatientNum();
        patientNumTV.setText(patientNum);
    }
}