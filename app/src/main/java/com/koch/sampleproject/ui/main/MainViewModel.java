package com.koch.sampleproject.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.test.espresso.IdlingResource;

import com.koch.sampleproject.domain.GetTestApiResultsUseCase;
import com.koch.sampleproject.domain.RetrofitControllerListener;
import com.koch.sampleproject.model.Change;
import com.koch.sampleproject.network.SimpleIdlingResource;

import java.util.List;

public class MainViewModel extends ViewModel implements RetrofitControllerListener {

    public MutableLiveData<String> text = new MutableLiveData<>("Binded Text from VW");

    public MutableLiveData<List<Change>> changes = new MutableLiveData<>();

    public void init() { }

    public void callTestApi(SimpleIdlingResource idlingResource) {
        GetTestApiResultsUseCase useCase = new GetTestApiResultsUseCase(this);
        useCase.execute(idlingResource);
    }

    @Override
    public void onChangesReceived(List<Change> changesList) {
        changes.setValue(changesList);
        text.setValue("New binded Text");  // for ui test exercise
    }
}