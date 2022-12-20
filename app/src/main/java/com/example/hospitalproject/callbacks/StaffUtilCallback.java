package com.example.hospitalproject.callbacks;

import androidx.recyclerview.widget.DiffUtil;

import com.example.hospitalproject.room.Staff;

import java.util.List;


public class StaffUtilCallback extends DiffUtil.Callback {

    private final List<Staff> mOldList;
    private final List<Staff> mNewList;

    public StaffUtilCallback(List<Staff> oldList, List<Staff> newList){
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
        return mOldList.get(oldItemPosition).sid == mNewList.get(newItemPosition).sid;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition) == mNewList.get(newItemPosition);
    }
}
