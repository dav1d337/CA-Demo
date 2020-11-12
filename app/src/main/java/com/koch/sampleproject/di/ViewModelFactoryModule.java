package com.koch.sampleproject.di;

import androidx.lifecycle.ViewModelProvider;

import com.koch.sampleproject.ui.utils.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelProviderFactory viewModelProviderFactory);

//    Both are same (above is same as below using @Provides annotation).
//    @Provides
//    static ViewModelProvider.Factory bindViewModelFactory2(ViewModelProviderFactory factory) {
//        return factory;
//    }

}