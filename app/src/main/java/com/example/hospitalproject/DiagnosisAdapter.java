package com.example.hospitalproject;

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

import com.example.hospitalproject.callbacks.DiagnosisUtilCallback;
import com.example.hospitalproject.callbacks.StaffUtilCallback;
import com.example.hospitalproject.listeners.Listeners;
import com.example.hospitalproject.room.Diagnosis;
import com.example.hospitalproject.room.Staff;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DiagnosisAdapter extends RecyclerView.Adapter<DiagnosisAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private List<Diagnosis> diagnosisesList;

    DiagnosisAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
        this.diagnosisesList = new ArrayList<>();
    }

    public void setList(List<Diagnosis> newList){
        DiagnosisUtilCallback diffCallback = new DiagnosisUtilCallback(diagnosisesList, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        diagnosisesList.clear();
        diagnosisesList.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView illnessName;
        final TextView illnessDate;
        ViewHolder(View view){
            super(view);
            illnessName = view.findViewById(R.id.illness_name);
            illnessDate = view.findViewById(R.id.illness_date);
        }

        public void bind(Diagnosis diagnosis){

            illnessName.setText(diagnosis.illness);
            illnessDate.setText(diagnosis.assignmentDate);
        }
    }

    @NonNull
    @Override
    public DiagnosisAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.diagnosis_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiagnosisAdapter.ViewHolder holder, int position) {
        holder.bind(diagnosisesList.get(position));
    }


    @Override
    public int getItemCount() {
        return diagnosisesList.size();
    }

}

