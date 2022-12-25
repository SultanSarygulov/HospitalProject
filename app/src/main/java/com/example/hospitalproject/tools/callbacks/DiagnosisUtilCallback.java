package com.example.hospitalproject.tools.callbacks;

import androidx.recyclerview.widget.DiffUtil;

import com.example.hospitalproject.room.Diagnosis;

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
        return mOldList.get(oldItemPosition).id == mNewList.get(newItemPosition).id;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition) == mNewList.get(newItemPosition);
    }
}