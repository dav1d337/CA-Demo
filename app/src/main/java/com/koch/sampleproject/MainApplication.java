package com.koch.sampleproject;

import android.app.Application;

import com.koch.sampleproject.di.ContentModule;
import com.koch.sampleproject.di.DaggerMainComponent;
import com.koch.sampleproject.di.MainComponent;
import com.koch.sampleproject.di.MainModule;
import com.koch.sampleproject.di.NetworkModule;
import com.koch.sampleproject.di.ViewModelFactoryModule;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


public class MainApplication extends DaggerApplication {
//    private MainComponent mainComponent;
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        initComponent();
//    }
//
//    private void initComponent() {
//        mainComponent = DaggerMainComponent.builder()
//                .mainModule(new MainModule(this))
//                .networkModule(new NetworkModule())
//               // .contentModule(new ContentModule())
//                .build();
//
//        mainComponent.inject(this);
//    }
//
//    public MainComponent getMainComponent() {
//        return mainComponent;
//    }

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerMainComponent.builder().application(this).build();
    }
}
