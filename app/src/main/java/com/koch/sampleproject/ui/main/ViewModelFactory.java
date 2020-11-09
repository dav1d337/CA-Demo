package com.koch.sampleproject.ui.main;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import javax.inject.Provider;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private final Provider<MainViewModel> mMyViewModelProvider;
    @Inject
    public ViewModelFactory(Provider<MainViewModel> myViewModelProvider) {
        mMyViewModelProvider= myViewModelProvider;
    }
    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        ViewModel viewModel;
        if (modelClass == MainViewModel.class) {
            viewModel = mMyViewModelProvider.get();
        }
        else {
            throw new RuntimeException("unsupported view model class: " + modelClass);
        }
        return (T) viewModel;
    }
}