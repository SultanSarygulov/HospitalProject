package com.example.hospitalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainDoctorFragment extends Fragment {

    public MainDoctorFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main_doctor, container, false);
        // Инициализация списка
        RecyclerView nurseRecyclerView = view.findViewById(R.id.nurse_list);
        // Создаем адаптер
        NurseRecyclerAdapter adapter = new NurseRecyclerAdapter(requireContext());
        nurseRecyclerView.setAdapter(adapter);


        return view;
    }
}