package com.example.hospitalproject.tools;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.hospitalproject.presentation.users.mainDoctor.MainDoctorViewModel;
import com.example.hospitalproject.room.repository.StaffRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private StaffRepository repository;
    private Application application;

    public ViewModelFactory(StaffRepository repository, Application application) {
        this.repository = repository;
        this.application = application;
    }

    @NonNull
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MainDoctorViewModel.class)) {
            return (T) new MainDoctorViewModel(application);
        } else {
            throw new IllegalArgumentException("ViewModel Not Found");
        }
    }
}



