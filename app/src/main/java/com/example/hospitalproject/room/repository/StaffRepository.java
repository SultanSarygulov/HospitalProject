package com.example.hospitalproject.room.repository;

import androidx.lifecycle.LiveData;

import com.example.hospitalproject.room.Staff;
import com.example.hospitalproject.room.StaffDao;

import java.util.List;

public class StaffRepository {

    public StaffDao staffDao;
    public LiveData<List<Staff>> readDoctors;
    public LiveData<List<Staff>> readNurses;
    public LiveData<Staff> readMostPaid;
    public LiveData<Staff> readLeastPaid;

    public StaffRepository(StaffDao staffDao){
        this.staffDao = staffDao;
        readDoctors = staffDao.getDoctors();
        readNurses = staffDao.getNurses();
        readMostPaid = staffDao.getMostPaid();
        readLeastPaid = staffDao.getLeastPaid();
    }

    public void deleteStaff(Staff staff){
        staffDao.deleteStaff(staff);
    }
}
