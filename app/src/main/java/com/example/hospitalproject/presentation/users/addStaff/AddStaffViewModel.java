package com.example.hospitalproject.presentation.users.addStaff;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.hospitalproject.room.Staff;
import com.example.hospitalproject.room.StaffDao;
import com.example.hospitalproject.room.database.HospitalDatabase;
import com.example.hospitalproject.room.repository.StaffRepository;

public class AddStaffViewModel extends AndroidViewModel {

    StaffRepository staffRepository;

    public AddStaffViewModel(@NonNull Application application) {
        super(application);

        StaffDao staffDao = HospitalDatabase.getDatabase(application).staffDao();
        staffRepository = new StaffRepository(staffDao);
    }

    public void addStaff(Staff staff){
        staffRepository.addStaff(staff);
    }
}
