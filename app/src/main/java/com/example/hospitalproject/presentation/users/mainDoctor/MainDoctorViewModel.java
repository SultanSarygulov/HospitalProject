package com.example.hospitalproject.presentation.users.mainDoctor;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.hospitalproject.room.Staff;
import com.example.hospitalproject.room.StaffDao;
import com.example.hospitalproject.room.StaffRepository;
import com.example.hospitalproject.room.database.HospitalDatabase;

import java.util.List;

public class MainDoctorViewModel extends AndroidViewModel {
    public LiveData<List<Staff>> readDoctors;

    private StaffRepository repository;
    public LiveData<List<Staff>> readNurses;
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
}
