package com.example.hospitalproject;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

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
import java.util.Locale;

public class AddStaffFragment extends Fragment {

    TextView enterNameEt;
    TextView enterSurnameEt;
    TextView enterSalaryEt;
    TextView enterPasswordEt;
    HospitalDatabase db;
    AddStaffFragmentArgs args;
    Button addNewStaffButton;

    public AddStaffFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_staff, container, false);

        db = HospitalDatabase.getDatabase(requireContext());

        args = AddStaffFragmentArgs.fromBundle(getArguments());

        enterNameEt = view.findViewById(R.id.enterNameEt);
        enterSurnameEt = view.findViewById(R.id.enterSurnameEt);
        enterPasswordEt = view.findViewById(R.id.enterPasswordEt);
        enterSalaryEt = view.findViewById(R.id.enterSalaryEt);

        addNewStaffButton = view.findViewById(R.id.addStaffButton);


        return view;
    }

    @Override
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);






        addNewStaffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Staff newStaff = new Staff();
                newStaff.sName = enterNameEt.getText().toString();
                newStaff.sSurname = enterSurnameEt.getText().toString();
//        newStaff.sSalary = Integer.parseInt(enterSalaryEt.getText().toString());
                newStaff.sPassword = enterPasswordEt.getText().toString();
                newStaff.sPosition = args.getPosition();

                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDateTime now = LocalDateTime.now();
                newStaff.sEmploymentDate = dtf.format(now);

                Toast.makeText(requireContext(), "New " + newStaff.sPosition.toLowerCase(Locale.ROOT) + " added", Toast.LENGTH_LONG).show();
                db.staffDao().addStaff(newStaff);
                Navigation.findNavController(view).navigateUp();
            }
        });
    }
}