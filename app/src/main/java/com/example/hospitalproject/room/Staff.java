package com.example.hospitalproject.room;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

import kotlinx.parcelize.Parcelize;

@Parcelize
@Entity(tableName = "staff_table")
public class Staff implements Parcelable {
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

    @ColumnInfo(name = "password")
    public String sPassword;

    public Staff(Parcel in) {
        sid = in.readInt();
        sName = in.readString();
        sSurname = in.readString();
        sPosition = in.readString();
        sEmploymentDate = in.readString();
        sSalary = in.readInt();
        sPassword = in.readString();
    }

    public static final Creator<Staff> CREATOR = new Creator<Staff>() {
        @Override
        public Staff createFromParcel(Parcel in) {
            return new Staff(in);
        }

        @Override
        public Staff[] newArray(int size) {
            return new Staff[size];
        }
    };

    public Staff() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(sid);
        parcel.writeString(sName);
        parcel.writeString(sSurname);
        parcel.writeString(sPosition);
        parcel.writeString(sEmploymentDate);
        parcel.writeInt(sSalary);
        parcel.writeString(sPassword);
    }
}