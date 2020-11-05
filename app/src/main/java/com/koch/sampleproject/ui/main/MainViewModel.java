package com.koch.sampleproject.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.koch.sampleproject.domain.GetTestApiResultsUseCase;
import com.koch.sampleproject.domain.RetrofitControllerListener;
import com.koch.sampleproject.model.Change;

import java.util.List;

public class MainViewModel extends ViewModel implements RetrofitControllerListener {

    private MutableLiveData<String> _text = new MutableLiveData<>("Binded Text from VW");
    public LiveData<String> text = _text;

    public MutableLiveData<String> changes = new MutableLiveData<>("Hallo");
  //  public LiveData<String> changes = _changes;

    public MainViewModel() {

    }

    public void init() {

    }

    public void callTestApi() {
        GetTestApiResultsUseCase useCase = new GetTestApiResultsUseCase(this);
        useCase.execute();
    }

    @Override
    public void onChangesReceived(List<Change> changesList) {
        changes.setValue(changesList.get(0).getSubject());
    }
}