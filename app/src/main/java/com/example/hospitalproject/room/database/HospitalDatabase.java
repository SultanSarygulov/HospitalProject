package com.example.hospitalproject.room.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.hospitalproject.room.Assignment;
import com.example.hospitalproject.room.AssignmentDao;
import com.example.hospitalproject.room.Diagnosis;
import com.example.hospitalproject.room.DiagnosisDao;
import com.example.hospitalproject.room.Patient;
import com.example.hospitalproject.room.PatientDao;
import com.example.hospitalproject.room.Staff;
import com.example.hospitalproject.room.StaffDao;

@Database(entities = {Staff.class, Patient.class, Diagnosis.class, Assignment.class}, version = 5)
public abstract class HospitalDatabase extends RoomDatabase {

    public abstract StaffDao staffDao();
    public abstract PatientDao patientDao();
    public abstract DiagnosisDao diagnosisDao();
    public abstract AssignmentDao assignmentDao();

    private static HospitalDatabase INSTANCE;

    public static HospitalDatabase getDatabase(Context context){

        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), HospitalDatabase.class, "hospital_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
}
