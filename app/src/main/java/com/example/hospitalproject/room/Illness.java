package com.example.hospitalproject.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "illness_table")
public class Illness {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "iid")
    public int iid;

    @ColumnInfo(name = "name")
    public String illnessName;
}
