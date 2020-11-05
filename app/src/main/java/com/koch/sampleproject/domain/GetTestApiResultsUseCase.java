package com.koch.sampleproject.domain;

import com.koch.sampleproject.MainApplication;
import com.koch.sampleproject.di.DaggerMainComponent;
import com.koch.sampleproject.model.TestApi;
import com.koch.sampleproject.network.RetrofitController;
import com.koch.sampleproject.ui.main.MainViewModel;

import javax.inject.Inject;

import retrofit2.Retrofit;


public class GetTestApiResultsUseCase {

    private final RetrofitController retrofitController = new RetrofitController(); // TODO: injection
    private MainViewModel viewModel;
 //   @Inject
 //   Retrofit retrofit;

    public GetTestApiResultsUseCase(MainViewModel viewModel) {
       // ((MainApplication) getApplication()).getMainComponent().inject(this);
        this.viewModel = viewModel; // das ist nicht clean , besser Interface
        registerViewModelAsListener();
    }

    public void registerViewModelAsListener() {
        retrofitController.registerListener(this.viewModel);
    }

    public void execute() {
        retrofitController.start();
    }
}
