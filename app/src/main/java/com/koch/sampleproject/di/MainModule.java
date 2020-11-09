package com.koch.sampleproject.di;

import android.content.Context;

import com.google.gson.Gson;
import com.koch.sampleproject.MainApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private final MainApplication mainApplication;

    public MainModule(MainApplication mainApplication) {
        this.mainApplication = mainApplication;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return mainApplication;
    }
}
