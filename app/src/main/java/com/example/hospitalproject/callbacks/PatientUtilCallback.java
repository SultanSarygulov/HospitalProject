package com.example.hospitalproject.callbacks;

import androidx.recyclerview.widget.DiffUtil;

import com.example.hospitalproject.room.Patient;

import java.util.List;


public class PatientUtilCallback extends DiffUtil.Callback {

    private final List<Patient> mOldList;
    private final List<Patient> mNewList;

    public PatientUtilCallback(List<Patient> oldList, List<Patient> newList){
        this.mOldList = oldList;
        this.mNewList = newList;
    }


    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).pid == mNewList.get(oldItemPosition).pid;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition) == mNewList.get(oldItemPosition);
    }
}

