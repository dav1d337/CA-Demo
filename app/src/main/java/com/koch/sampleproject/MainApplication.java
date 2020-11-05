package com.koch.sampleproject;

import android.app.Application;

import com.koch.sampleproject.di.DaggerMainComponent;
import com.koch.sampleproject.di.MainComponent;
import com.koch.sampleproject.di.MainModule;
import com.koch.sampleproject.di.NetworkModule;

public class MainApplication extends Application {
    private MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
    }

    private void initComponent() {
        mainComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .networkModule(new NetworkModule("https://git.eclipse.org/r/"))
                .build();

        mainComponent.inject(this);
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }
}
