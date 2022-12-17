package com.example.hospitalproject.room;

import android.content.Context;

import androidx.room.AutoMigration;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Staff.class, Patient.class}, version = 2)
public abstract class HospitalDatabase extends RoomDatabase {

    public abstract StaffDao staffDao();
    public abstract PatientDao patientDao();

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
