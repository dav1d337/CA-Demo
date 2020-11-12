package com.koch.sampleproject.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.koch.sampleproject.adapter.changes.GetTestApiResultsUseCase;
import com.koch.sampleproject.adapter.changes.ChangeDataListener;
import com.koch.sampleproject.model.Change;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MainViewModel extends ViewModel implements ChangeDataListener {

    public MutableLiveData<String> text = new MutableLiveData<>("Binded Text from VW");

    public MutableLiveData<List<Change>> changes = new MutableLiveData<>(new ArrayList<>());

    private GetTestApiResultsUseCase getTestApiResultsUseCase;

    @Inject
    public MainViewModel(GetTestApiResultsUseCase getTestApiResultsUseCase) {
        this.getTestApiResultsUseCase = getTestApiResultsUseCase;
    }

    public void init() {
        getTestApiResultsUseCase.registerListener(this);
    }

    public void callTestApi() {
        getTestApiResultsUseCase.execute();
    }

    @Override
    public void onChangesReceived(List<Change> changesList) {
        changes.setValue(changesList);
        text.setValue("New binded Text");  // for ui test exercise
    }

    public void unregisterListener() {
        getTestApiResultsUseCase.unregisterListener(this);
    }
}