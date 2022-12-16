package com.example.hospitalproject.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "staff_table")
public class Staff {
    @PrimaryKey(autoGenerate = true)
    public int sid;

    @ColumnInfo(name = "name")
    public String sName;

    @ColumnInfo(name = "surname")
    public String sSurname;

    @ColumnInfo(name = "position")
    public String sPosition;

    @ColumnInfo(name = "employment_date")
    public String sEmploymentDate;

    @ColumnInfo(name = "salary")
    public int sSalary;
}