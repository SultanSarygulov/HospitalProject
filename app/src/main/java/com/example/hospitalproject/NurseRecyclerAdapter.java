package com.example.hospitalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospitalproject.room.Staff;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NurseRecyclerAdapter extends RecyclerView.Adapter<NurseRecyclerAdapter.NurseViewHolder> {

    private final LayoutInflater inflater;
    private List<Staff> nurses;

    NurseRecyclerAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
        this.nurses = new ArrayList<>();
    }

    public void setList(List<Staff> newList){
        StaffUtilCallback diffCallback = new StaffUtilCallback(nurses, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        nurses.clear();
        nurses.addAll(newList);
        diffResult.dispatchUpdatesTo(this);

    }

    public static class NurseViewHolder extends RecyclerView.ViewHolder{
        final TextView name;
        final TextView surname;
        final TextView employmentDate;
        final FloatingActionButton deleteDoctorButton;

        NurseViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.nurse_name);
            surname = view.findViewById(R.id.nurse_surname);
            employmentDate= view.findViewById(R.id.nurse_come_date);
            deleteDoctorButton = view.findViewById(R.id.delete_nurse_button);
        }

        public void bind(Staff nurse){

            name.setText(nurse.sName);
            surname.setText(nurse.sSurname);
            employmentDate.setText(nurse.sEmploymentDate);
        }
    }

    @NonNull
    @Override
    public NurseRecyclerAdapter.NurseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nurse_list_item, parent, false);
        return new NurseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NurseRecyclerAdapter.NurseViewHolder holder, int position) {
        holder.bind(nurses.get(position));

    }

    @Override
    public int getItemCount() {
        return nurses.size();
    }


}
