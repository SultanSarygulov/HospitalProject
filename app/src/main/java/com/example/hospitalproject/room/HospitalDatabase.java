package com.example.hospitalproject.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Staff.class}, version = 1)
public abstract class HospitalDatabase extends RoomDatabase {

    public abstract StaffDao staffDao();

    private static HospitalDatabase INSTANCE;

    public static HospitalDatabase getDatabase(Context context){

        if (INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), HospitalDatabase.class, "hospital_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
