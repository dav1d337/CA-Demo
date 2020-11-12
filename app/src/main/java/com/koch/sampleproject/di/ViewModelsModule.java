package com.koch.sampleproject.di;

import androidx.lifecycle.ViewModel;

import com.koch.sampleproject.ui.main.MainViewModel;
import com.koch.sampleproject.ui.movies.MoviesViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel.class)
    public abstract ViewModel bindsMainViewModel(MainViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(MoviesViewModel.class)
    public abstract ViewModel bindsMoviesViewModel(MoviesViewModel viewModel);
}