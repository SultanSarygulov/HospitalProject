package com.example.hospitalproject.room;

import androidx.lifecycle.LiveData;

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
}
