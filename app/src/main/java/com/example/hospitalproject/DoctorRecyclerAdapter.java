package com.example.hospitalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;
import java.util.zip.Inflater;

public class DoctorRecyclerAdapter extends RecyclerView.Adapter<DoctorRecyclerAdapter.DoctorViewHolder> {

    private final LayoutInflater inflater;
    private List<String> doctors = Arrays.asList("Авиценна", "Мышка", "Адам", "Демон", "Вырвиглазный", "Хентайврач");

    DoctorRecyclerAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    @Override
    public void onBindViewHolder(@NonNull DoctorRecyclerAdapter.DoctorViewHolder holder, int position) {
        holder.name.setText(doctors.get(position));
    }

    @NonNull
    @Override
    public DoctorRecyclerAdapter.DoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.doctor_list_item, parent, false);
        return new DoctorViewHolder(view);
    }


    public static class DoctorViewHolder extends RecyclerView.ViewHolder{
        final TextView name;
        DoctorViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.doctor_name);
        }
    }
}
