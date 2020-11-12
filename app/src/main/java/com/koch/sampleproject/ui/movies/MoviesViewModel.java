package com.koch.sampleproject.ui.movies;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.koch.sampleproject.adapter.changes.GetTestApiResultsUseCase;
import com.koch.sampleproject.adapter.movies.GetTrendingMoviesUseCase;
import com.koch.sampleproject.adapter.movies.MoviesDataListener;
import com.koch.sampleproject.model.Change;
import com.koch.sampleproject.model.Movie;
import com.koch.sampleproject.model.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class MoviesViewModel extends ViewModel implements MoviesDataListener {

    public MutableLiveData<List<Movie>> movies = new MutableLiveData<>(new ArrayList<>());

    private GetTrendingMoviesUseCase getTrendingMoviesUseCase;

    @Inject
    public MoviesViewModel(GetTrendingMoviesUseCase getTrendingMoviesUseCase) {
        this.getTrendingMoviesUseCase = getTrendingMoviesUseCase;
    }

    public void init() {
        getTrendingMoviesUseCase.registerListener(this);
    }

    public void callMoviesApi() {
        getTrendingMoviesUseCase.execute();
    }

    @Override
    public void onChangesReceived(MovieResponse movieList) {
        movies.postValue(movieList.getResults());
    }

    public void unregisterListener() {
        getTrendingMoviesUseCase.unregisterListener(this);
    }
}
