package com.koch.sampleproject.domain;

import android.util.ArraySet;

import com.koch.sampleproject.model.Change;
import com.koch.sampleproject.model.TestApi;
import com.koch.sampleproject.network.SimpleIdlingResource;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetTestApiResultsUseCase implements Callback<List<Change>> {

    // private final RetrofitController retrofitController = new RetrofitController(); // TODO: injection
    private SimpleIdlingResource simpleIdlingResource;
    private Set<RetrofitListener> responseListeners = new ArraySet<>();

    private TestApi testApi;

    @Inject
    public GetTestApiResultsUseCase(TestApi testApi) {
        this.testApi = testApi;
    }

    public void execute(SimpleIdlingResource idlingResource) {
        this.simpleIdlingResource = idlingResource;
        if (simpleIdlingResource != null) {
            simpleIdlingResource.setIdleState(false);
        }

        Call<List<Change>> call = testApi.loadChanges("status:open");
        call.enqueue(this);
    }

    public void registerListener(RetrofitListener retrofitListener) {
        responseListeners.add(retrofitListener);
    }

    public void notifyListener(List<Change> changesList) {
        responseListeners.forEach(listener -> listener.onChangesReceived(changesList));
    }

    @Override
    public void onResponse(Call<List<Change>> call, Response<List<Change>> response) {
        if(response.isSuccessful()) {
            List<Change> changesList = response.body();
            changesList.forEach(change -> System.out.println("Subject:" + change.getSubject()));
            notifyListener(changesList);
            if (simpleIdlingResource != null) {
                simpleIdlingResource.setIdleState(true);
            }
        } else {
            System.out.println("Error on Response");
            System.out.println(response.errorBody());
        }
    }

    @Override
    public void onFailure(Call<List<Change>> call, Throwable t) {
        System.out.println("Failure");
        t.printStackTrace();
    }
}
