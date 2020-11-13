package com.koch.sampleproject.ui.movies;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.koch.sampleproject.adapter.movies.GetTrendingMoviesUseCase;
import com.koch.sampleproject.adapter.movies.MoviesDataListener;
import com.koch.sampleproject.model.Movie;
import com.koch.sampleproject.model.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MoviesViewModel extends ViewModel implements MoviesDataListener {

    public MutableLiveData<List<Movie>> movies = new MutableLiveData<>(new ArrayList<>());
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();

    private GetTrendingMoviesUseCase getTrendingMoviesUseCase;

    @Inject
    public MoviesViewModel(GetTrendingMoviesUseCase getTrendingMoviesUseCase) {
        this.getTrendingMoviesUseCase = getTrendingMoviesUseCase;
    }

    public void init() {
        getTrendingMoviesUseCase.registerListener(this);
    }

    public void callApiForTrendingMovies() {
        getTrendingMoviesUseCase.getTrending();
    }

    public void callApiForUpcomingMovies() {
        getTrendingMoviesUseCase.getUpcoming();
    }

    @Override
    public void onChangesReceived(MovieResponse movieList) {
        movies.postValue(movieList.getResults());
    }

    @Override
    public void onError(Error error) {
        errorMessage.postValue(error.getMessage());
    }

    public void unregisterListener() {
        getTrendingMoviesUseCase.unregisterListener(this);
    }

    @Override
    protected void onCleared() {
        Log.d("MoviesViewModel", "MoviesVM cleared");
    }
}
