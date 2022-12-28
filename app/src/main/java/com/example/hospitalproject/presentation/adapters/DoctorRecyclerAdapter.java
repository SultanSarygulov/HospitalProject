package com.example.hospitalproject.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospitalproject.R;
import com.example.hospitalproject.tools.callbacks.StaffUtilCallback;
import com.example.hospitalproject.tools.listeners.Listeners;
import com.example.hospitalproject.room.Staff;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DoctorRecyclerAdapter extends RecyclerView.Adapter<DoctorRecyclerAdapter.DoctorViewHolder> {

    private final LayoutInflater inflater;
    private List<Staff> doctors;
    private final Listeners listener;

    public DoctorRecyclerAdapter(Context context, Listeners listener){
        this.inflater = LayoutInflater.from(context);
        this.doctors = new ArrayList<>();
        this.listener = listener;
    }

    public void setList(List<Staff> newList){
        StaffUtilCallback diffCallback = new StaffUtilCallback(doctors, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        doctors.clear();
        doctors.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }


    public static class DoctorViewHolder extends RecyclerView.ViewHolder{
        final TextView name;
        final TextView surname;
        final TextView employmentDate;
        final FloatingActionButton deleteDoctorButton;
        final Listeners listener;
        DoctorViewHolder(View view, Listeners listener){
            super(view);
            name = view.findViewById(R.id.doctor_name);
            surname = view.findViewById(R.id.doctor_surname);
            employmentDate= view.findViewById(R.id.doctor_come_date);
            deleteDoctorButton = view.findViewById(R.id.delete_doctor_button);
            this.listener = listener;
        }

        public void bind(Staff doctor){

            name.setText(doctor.sName);
            surname.setText(doctor.sSurname);
            employmentDate.setText(doctor.sEmploymentDate);
            deleteDoctorButton.setOnClickListener(view ->{
                listener.deleteStaff(doctor);
            });

        }
    }

    @NonNull
    @Override
    public DoctorRecyclerAdapter.DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.doctor_list_item, parent, false);
        return new DoctorViewHolder(view, listener);
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
