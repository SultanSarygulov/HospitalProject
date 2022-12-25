package com.example.hospitalproject.presentation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospitalproject.R;
import com.example.hospitalproject.tools.callbacks.PatientUtilCallback;
import com.example.hospitalproject.tools.listeners.DoctorListener;
import com.example.hospitalproject.room.Patient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class PatientRecyclerAdapter extends RecyclerView.Adapter<PatientRecyclerAdapter.PatientViewHolder>{

    private final LayoutInflater inflater;
    private List<Patient> patients;
    private final DoctorListener listener;

    public PatientRecyclerAdapter(Context context, DoctorListener listener){
        this.inflater = LayoutInflater.from(context);
        this.patients = new ArrayList<>();
        this.listener = listener;
    }

    public void setList(List<Patient> newList){
        PatientUtilCallback diffCallback = new PatientUtilCallback(patients, newList);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        patients.clear();
        patients.addAll(newList);
        diffResult.dispatchUpdatesTo(this);
    }

    public static class PatientViewHolder extends RecyclerView.ViewHolder{
        final TextView name;
        final TextView surname;
        final FloatingActionButton deletePatientButton;
        final FloatingActionButton addDiagnosisButton;
        final DoctorListener listener;
        PatientViewHolder(View view, DoctorListener listener){
            super(view);
            name = view.findViewById(R.id.patient_name);
            surname = view.findViewById(R.id.patient_surname);
            deletePatientButton = view.findViewById(R.id.delete_patient_button);
            addDiagnosisButton = view.findViewById(R.id.add_diagnosis_button);

            this.listener = listener;
        }

        public void bind(Patient patient){

            name.setText(patient.pName);
            surname.setText(patient.pSurname);
            deletePatientButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.deletePatient(patient);
                }
            });

            addDiagnosisButton.setOnClickListener(view -> {
                listener.addDiagnosisToPatient(patient.id);
            });

        }
    }

    @NonNull
    @Override
    public PatientRecyclerAdapter.PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.patient_list_item, parent, false);
        return new PatientViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientRecyclerAdapter.PatientViewHolder holder, int position) {
        holder.bind(patients.get(position));
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

}
