package com.example.hospitalproject.presentation.users.mainDoctor;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hospitalproject.room.Staff;
import com.example.hospitalproject.room.StaffDao;
import com.example.hospitalproject.room.repository.StaffRepository;
import com.example.hospitalproject.room.database.HospitalDatabase;

import java.util.List;

public class MainDoctorViewModel extends AndroidViewModel {

    private StaffRepository repository;
    public LiveData<List<Staff>> readNurses;
    public LiveData<List<Staff>> readDoctors;
    public LiveData<Staff> readMostPaid;
    public LiveData<Staff> readLeastPaid;

    public MainDoctorViewModel(@NonNull Application application) {
        super(application);
        StaffDao staffDao = HospitalDatabase.getDatabase(application).staffDao();
        repository = new StaffRepository(staffDao);

        readDoctors = repository.readDoctors;
        readNurses = repository.readNurses;
        readMostPaid = repository.readMostPaid;
        readLeastPaid = repository.readLeastPaid;
    }

    public void deleteStaff(Staff staff){
        repository.deleteStaff(staff);
    }
}
