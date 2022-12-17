package com.example.hospitalproject;

import android.content.Context;
import android.service.controls.actions.FloatAction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospitalproject.room.Staff;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

public class DoctorRecyclerAdapter extends RecyclerView.Adapter<DoctorRecyclerAdapter.DoctorViewHolder> {

    private final LayoutInflater inflater;
    private List<Staff> doctors;

    DoctorRecyclerAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
        this.doctors = new ArrayList<>();
    }

    public void setList(List<Staff> newList){
        StaffUtilCallback diffCallback = new StaffUtilCallback(doctors, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        doctors.clear();
        doctors.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
//        this.doctors = newList;
//        notifyDataSetChanged();
    }


    public static class DoctorViewHolder extends RecyclerView.ViewHolder{
        final TextView name;
        final TextView surname;
        final TextView employmentDate;
        final FloatingActionButton deleteDoctorButton;
        DoctorViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.doctor_name);
            surname = view.findViewById(R.id.doctor_surname);
            employmentDate= view.findViewById(R.id.doctor_come_date);
            deleteDoctorButton = view.findViewById(R.id.delete_doctor_button);
        }

        public void bind(Staff doctor){

            name.setText(doctor.sName);
            surname.setText(doctor.sSurname);
            employmentDate.setText(doctor.sEmploymentDate);

        }
    }

    @NonNull
    @Override
    public DoctorRecyclerAdapter.DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.doctor_list_item, parent, false);
        return new DoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorRecyclerAdapter.DoctorViewHolder holder, int position) {
        holder.bind(doctors.get(position));
    }


    @Override
    public int getItemCount() {
        return doctors.size();
    }

}
