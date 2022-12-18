package com.example.hospitalproject.room;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "patient_table")
public class Patient implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    public int pid;

    @ColumnInfo(name = "name")
    public String pName;

    @ColumnInfo(name = "surname")
    public String pSurname;

    @ColumnInfo(name = "password")
    public String pPassword;

    public Patient(){

    }

    protected Patient(Parcel in) {
        pid = in.readInt();
        pName = in.readString();
        pSurname = in.readString();
        pPassword = in.readString();
    }

    public static final Creator<Patient> CREATOR = new Creator<Patient>() {
        @Override
        public Patient createFromParcel(Parcel in) {
            return new Patient(in);
        }

        @Override
        public Patient[] newArray(int size) {
            return new Patient[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(pid);
        parcel.writeString(pName);
        parcel.writeString(pSurname);
        parcel.writeString(pPassword);
    }
}
