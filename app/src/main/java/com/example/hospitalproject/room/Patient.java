package com.example.hospitalproject.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "patient_table")
public class Patient {
    @PrimaryKey(autoGenerate = true)
    public int pid;
    public String pName;
    public String pSurname;
    @ColumnInfo(name = "employment_date")
    public Date pEmploymentDate;
}
