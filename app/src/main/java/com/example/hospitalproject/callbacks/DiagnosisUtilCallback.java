package com.example.hospitalproject.callbacks;

import androidx.recyclerview.widget.DiffUtil;

import com.example.hospitalproject.room.Diagnosis;
import com.example.hospitalproject.room.Patient;

import java.util.List;

public class DiagnosisUtilCallback extends DiffUtil.Callback {

    private final List<Diagnosis> mOldList;
    private final List<Diagnosis> mNewList;

    public DiagnosisUtilCallback(List<Diagnosis> oldList, List<Diagnosis> newList){
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
        return mOldList.get(oldItemPosition).did == mNewList.get(newItemPosition).did;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition) == mNewList.get(newItemPosition);
    }
}