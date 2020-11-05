package com.koch.sampleproject.di;

import android.content.Context;

import com.koch.sampleproject.MainApplication;
import com.koch.sampleproject.domain.GetTestApiResultsUseCase;
import com.koch.sampleproject.ui.main.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {MainModule.class, NetworkModule.class})
public interface MainComponent {

  void inject(MainActivity activity);
  void inject(MainApplication application);
  void inject(GetTestApiResultsUseCase getTestApiResultsUseCase);

  Context context();
}
