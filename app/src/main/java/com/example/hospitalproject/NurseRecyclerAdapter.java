package com.example.hospitalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NurseRecyclerAdapter extends RecyclerView.Adapter<NurseRecyclerAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private List<String> nurses = Arrays.asList("Joane", "Jeniffer", "Anna", "Mohammed");

    NurseRecyclerAdapter(Context context){
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getItemCount() {
        return nurses.size();
    }

    @NonNull
    @Override
    public NurseRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.nurse_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NurseRecyclerAdapter.ViewHolder holder, int position) {
        holder.name.setText(nurses.get(position));

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final TextView name;
        ViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.nurse_name);
        }
    }
}
