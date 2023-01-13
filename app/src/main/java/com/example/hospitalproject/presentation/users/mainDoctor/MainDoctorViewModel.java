package com.example.hospitalproject.presentation.users.mainDoctor;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.hospitalproject.room.PatientDao;
import com.example.hospitalproject.room.Staff;
import com.example.hospitalproject.room.StaffDao;
import com.example.hospitalproject.room.repository.PatientRepository;
import com.example.hospitalproject.room.repository.StaffRepository;
import com.example.hospitalproject.room.database.HospitalDatabase;

import java.util.List;

public class MainDoctorViewModel extends AndroidViewModel {

    private StaffRepository staffRepository;
    private PatientRepository patientRepository;
    public LiveData<List<Staff>> nursesList;
    public LiveData<List<Staff>> doctorsList;
    public LiveData<Staff> readMostPaid;
    public LiveData<Staff> readLeastPaid;

    public String patientNum;

    public MainDoctorViewModel(@NonNull Application application) {
        super(application);

        // Staff repository
        StaffDao staffDao = HospitalDatabase.getDatabase(application).staffDao();
        staffRepository = new StaffRepository(staffDao);

        doctorsList = staffRepository.doctorsList;
        nursesList = staffRepository.nursesList;
        readMostPaid = staffRepository.readMostPaid;
        readLeastPaid = staffRepository.readLeastPaid;

        // Patient repository
        PatientDao patientDao = HospitalDatabase.getDatabase(application).patientDao();
        patientRepository = new PatientRepository(patientDao);

        patientNum = patientRepository.patientNum;
    }

    public void deleteStaff(Staff staff){
        staffRepository.deleteStaff(staff);
    }
}
