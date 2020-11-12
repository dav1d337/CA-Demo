package com.koch.sampleproject.adapter.changes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;

import com.koch.sampleproject.adapter.changes.ChangesReceivedCallback;
import com.koch.sampleproject.adapter.changes.ServerChangeListProvider;
import com.koch.sampleproject.model.Change;
import com.koch.sampleproject.model.TestApi;
import com.koch.sampleproject.network.SimpleIdlingResource;

import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestApiController implements ServerChangeListProvider {

    @Nullable
    private SimpleIdlingResource simpleIdlingResource;

    TestApi testApi;

    @Inject
    public TestApiController(TestApi testApi) {
        this.testApi = testApi;
    }

    @Override
    public void fetchChanges(ChangesReceivedCallback useCaseCallback) {
        Call<List<Change>> call = testApi.loadChanges("status:open");
        if (simpleIdlingResource != null) {
            simpleIdlingResource.setIdleState(false);
        }
        call.enqueue(new Callback<List<Change>>() {
            @Override
            public void onResponse(Call<List<Change>> call, Response<List<Change>> response) {
                List<Change> changesList = response.body();
                changesList.forEach(change -> System.out.println("Subject:" + change.getSubject()));
                useCaseCallback.changesReceived(changesList);
                if (simpleIdlingResource != null) {
                    simpleIdlingResource.setIdleState(true);
                }
            }

            @Override
            public void onFailure(Call<List<Change>> call, Throwable t) {
                System.out.println("Failure");
                t.printStackTrace();
            }
        });
    }

    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public SimpleIdlingResource getIdlingResource() {
        if (simpleIdlingResource == null) {
            simpleIdlingResource = new SimpleIdlingResource();
        }
        return simpleIdlingResource;
    }
}
