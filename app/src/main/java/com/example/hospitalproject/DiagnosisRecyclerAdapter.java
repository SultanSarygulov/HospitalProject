package com.example.hospitalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.hospitalproject.room.Diagnosis;

import java.util.ArrayList;
import java.util.List;

public class DiagnosisRecyclerAdapter extends RecyclerView.Adapter<DiagnosisRecyclerAdapter.DiagnosisViewHolder> {

    private final LayoutInflater inflater;
    private List<Diagnosis> diagnosises;

    DiagnosisRecyclerAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
        this.diagnosises = new ArrayList<>();
    }

    public void setList(List<Diagnosis> newList){
        this.diagnosises = newList;
        notifyDataSetChanged();
    }

    public static class DiagnosisViewHolder extends RecyclerView.ViewHolder{
        final TextView illnessName;
        final TextView date;
        DiagnosisViewHolder(View view){
            super(view);
            illnessName = view.findViewById(R.id.illness_name);
            date = view.findViewById(R.id.illness_date);
        }

        public void bind(Diagnosis diagnosis){
            illnessName.setText(diagnosis.illness);
            date.setText(diagnosis.assignmentDate);
        }
    }

    @NonNull
    @Override
    public DiagnosisRecyclerAdapter.DiagnosisViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.diagnosis_list_item, parent, false);
        return new DiagnosisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiagnosisRecyclerAdapter.DiagnosisViewHolder holder, int position) {
        holder.bind(diagnosises.get(position));
    }

    @Override
    public int getItemCount() {
        return diagnosises.size();
    }
}
