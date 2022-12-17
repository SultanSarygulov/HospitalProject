package com.example.hospitalproject.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "patient_table")
public class Patient {
    @PrimaryKey(autoGenerate = true)
    public int pid;

    @ColumnInfo(name = "name")
    public String pName;

    @ColumnInfo(name = "surname")
    public String pSurname;

    @ColumnInfo(name = "password")
    public String pPassword;
}
