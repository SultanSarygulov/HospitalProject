package com.example.hospitalproject.presentation.users.addStaff;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hospitalproject.R;
import com.example.hospitalproject.room.Staff;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class AddStaffFragment extends Fragment {

    TextView enterNameEt;
    TextView enterSurnameEt;
    TextView enterSalaryEt;
    TextView enterPasswordEt;
    AddStaffFragmentArgs args;
    Button addNewStaffButton;

    AddStaffViewModel addStaffViewModel;

    public AddStaffFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_staff, container, false);


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

        addStaffViewModel = ViewModelProviders.of(this).get(AddStaffViewModel.class);

        addNewStaffButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create new staff member
                Staff newStaff = new Staff();
                newStaff.sName = enterNameEt.getText().toString().trim();
                newStaff.sSurname = enterSurnameEt.getText().toString().trim();
                newStaff.sSalary = Integer.parseInt(enterSalaryEt.getText().toString().trim());
                newStaff.sPassword = enterPasswordEt.getText().toString().trim();
                newStaff.sPosition = args.getPosition();

                // Get time and date
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                LocalDateTime now = LocalDateTime.now();
                newStaff.sEmploymentDate = dtf.format(now);

                Toast.makeText(requireContext(), "New " + newStaff.sPosition.toLowerCase(Locale.ROOT) + " added", Toast.LENGTH_SHORT).show();
                addStaffViewModel.addStaff(newStaff);
                Navigation.findNavController(view).navigateUp();
            }
        });
    }
}