package com.koch.sampleproject.di;

import android.app.Application;
import android.content.Context;

import com.koch.sampleproject.MainApplication;
import com.koch.sampleproject.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;

// vgl. https://github.com/animeshroydev/DaggerPractice

@Singleton
@Component(modules = {AndroidInjectionModule.class, ActivityBuilderModule.class, NetworkModule.class, ContentModule.class, ViewModelFactoryModule.class, ViewModelsModule.class, RoomModule.class})
public interface MainComponent extends AndroidInjector<MainApplication> {

  void inject(MainApplication application);

  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder application(Application application);

    MainComponent build();
  }
}
