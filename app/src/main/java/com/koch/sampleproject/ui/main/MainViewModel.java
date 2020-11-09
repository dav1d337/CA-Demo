package com.koch.sampleproject.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.koch.sampleproject.domain.GetTestApiResultsUseCase;
import com.koch.sampleproject.domain.RetrofitListener;
import com.koch.sampleproject.model.Change;
import com.koch.sampleproject.network.SimpleIdlingResource;

import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel implements RetrofitListener {

    public MutableLiveData<String> text = new MutableLiveData<>("Binded Text from VW");

    public MutableLiveData<List<Change>> changes = new MutableLiveData<>();

    private GetTestApiResultsUseCase getTestApiResultsUseCase;

    @Inject
    public MainViewModel(GetTestApiResultsUseCase getTestApiResultsUseCase) {
        this.getTestApiResultsUseCase = getTestApiResultsUseCase;
    }

    public void init() {
        getTestApiResultsUseCase.registerListener(this);
    }

    public void callTestApi(SimpleIdlingResource idlingResource) {
        getTestApiResultsUseCase.execute(idlingResource);
    }

    @Override
    public void onChangesReceived(List<Change> changesList) {
        changes.setValue(changesList);
        text.setValue("New binded Text");  // for ui test exercise
    }
}