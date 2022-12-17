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

import com.example.hospitalproject.room.Staff;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NurseRecyclerAdapter
        extends RecyclerView.Adapter<NurseRecyclerAdapter.NurseViewHolder> {

    private final LayoutInflater inflater;
    private List<Staff> nurses;
    private final Listeners listener;

    NurseRecyclerAdapter(Context context, Listeners listener){
        this.inflater = LayoutInflater.from(context);
        this.nurses = new ArrayList<>();
        this.listener = listener;
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
        private final Listeners listener;

        NurseViewHolder(View view, Listeners listener){
            super(view);
            name = view.findViewById(R.id.nurse_name);
            surname = view.findViewById(R.id.nurse_surname);
            employmentDate= view.findViewById(R.id.nurse_come_date);
            deleteDoctorButton = view.findViewById(R.id.delete_nurse_button);
            this.listener = listener;
        }

        public void bind(Staff nurse){

            name.setText(nurse.sName);
            surname.setText(nurse.sSurname);
            employmentDate.setText(nurse.sEmploymentDate);

            deleteDoctorButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.deleteStaff(nurse);
                    Navigation.findNavController(view).navigate(R.id.action_mainDoctorFragment_to_deleteLoadingFragment);
                    Toast.makeText(view.getContext(), nurse.sPosition + " was deleted", Toast.LENGTH_LONG).show();
                }
            });
        }
    }

    @NonNull
    @Override
    public NurseRecyclerAdapter.NurseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nurse_list_item, parent, false);
        return new NurseViewHolder(view, listener);
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
