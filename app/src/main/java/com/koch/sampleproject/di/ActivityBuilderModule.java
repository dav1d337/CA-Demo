package com.koch.sampleproject.di;

import com.koch.sampleproject.ui.main.MainActivity;
import com.koch.sampleproject.ui.movies.MoviesActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract MoviesActivity contributeMovieActivity();
}