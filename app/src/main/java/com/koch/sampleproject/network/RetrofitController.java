package com.koch.sampleproject.network;


import android.util.ArraySet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.koch.sampleproject.adapter.changes.ChangeDataListener;
import com.koch.sampleproject.model.Change;
import com.koch.sampleproject.model.TestApi;

import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//todo: can be deleted
public class RetrofitController implements Callback<List<Change>> {

    // static final String BASE_URL = "http://api.themoviedb.org/3/";
    static final String BASE_URL = "https://git.eclipse.org/r/";

    private Set<ChangeDataListener> changeDataListeners = new ArraySet<>();

    private SimpleIdlingResource simpleIdlingResource;

    public void registerListener(ChangeDataListener changeDataListener) {
        changeDataListeners.add(changeDataListener);
    }

    public void notifyListener(List<Change> changesList) {
        changeDataListeners.forEach(listener -> listener.onChangesReceived(changesList));
    }

    public void start(SimpleIdlingResource idlingResource) {

        ////
//        Change change = new Change("das ist ein test");
//        List<Change> changes = new ArrayList<>();
//        changes.add(change);
//        notifyListener(changes);
        /////

        simpleIdlingResource = idlingResource;

        if (simpleIdlingResource != null) {
            simpleIdlingResource.setIdleState(false);
        }

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                //.addConverterFactory(JacksonConverterFactory.create())
                .build();

      //  MovieApi movieApi = retrofit.create(MovieApi.class);
        TestApi testAPI = retrofit.create(TestApi.class);

      //  Call<MovieResponse> call = movieApi.getTrendingMovies();
        Call<List<Change>> call = testAPI.loadChanges("status:open");
        call.enqueue(this);

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
