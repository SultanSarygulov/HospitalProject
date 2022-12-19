package com.example.hospitalproject.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "assignment_table")
public class Assignment {

    @PrimaryKey(autoGenerate = true)
    public long aid;

    @ColumnInfo(name = "nurse_id")
    public int nurseId;

    @ColumnInfo(name = "assignment_text")
    public String assignmentText;
}
