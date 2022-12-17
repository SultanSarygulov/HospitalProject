package com.example.hospitalproject;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.security.ConfirmationCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hospitalproject.room.HospitalDatabase;
import com.example.hospitalproject.room.Staff;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class MainDoctorFragment extends Fragment {

    Button addDoctor;
    Button addNurse;
    HospitalDatabase db;
    TextView mostPayedStaff;
    TextView leastPayedStaff;
    TextView patientNumTV;

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

        RecyclerView nurseRecyclerView = view.findViewById(R.id.nurse_list);
        NurseRecyclerAdapter nurseAdapter = new NurseRecyclerAdapter(requireContext());
        nurseAdapter.setList(getNursesList());
        nurseRecyclerView.setAdapter(nurseAdapter);

        RecyclerView doctorRecycler = view.findViewById(R.id.doctor_list);
        DoctorRecyclerAdapter doctorAdapter = new DoctorRecyclerAdapter(requireContext());
        doctorAdapter.setList(getDoctorsList());
        doctorRecycler.setAdapter(doctorAdapter);


        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

    private List<Staff> getNursesList() {
        List<Staff> nursesList = db.staffDao().getNurses();

        return nursesList;
    }

    private List<Staff> getDoctorsList() {
        List<Staff> doctorsList = db.staffDao().getDoctors();

        return doctorsList;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void addStaff(View view, String position) {

        NavDirections action = MainDoctorFragmentDirections.actionMainDoctorFragmentToAddStaffFragment(position);

        Navigation.findNavController(view).navigate(action);
    }

    private void nameMostPayed(){
        Staff mostPaidPerson = db.staffDao().getMostPaid();
        String mostPaidPersonTxt = "The list is empty(";

        if (mostPaidPerson != null){

            mostPaidPersonTxt = mostPaidPerson.sPosition + " " +
                    mostPaidPerson.sName + " " +
                    mostPaidPerson.sSurname + " - $" +
                    mostPaidPerson.sSalary;
        }

        mostPayedStaff.setText(mostPaidPersonTxt);
    }

    private void nameLeastPayed(){
        Staff leastPaidPerson = db.staffDao().getLeastPaid();
        String leastPaidPersonTxt = "The list is empty(";

        if (leastPaidPerson != null) {
            leastPaidPersonTxt = leastPaidPerson.sPosition + " " +
                    leastPaidPerson.sName + " " +
                    leastPaidPerson.sSurname + " - $" +
                    leastPaidPerson.sSalary;

        }

        leastPayedStaff.setText(leastPaidPersonTxt);
    }

    private void getNumberOfPatients(){
        String patientNum = db.patientDao().getPatientNum();
        patientNumTV.setText(patientNum);
    }
}