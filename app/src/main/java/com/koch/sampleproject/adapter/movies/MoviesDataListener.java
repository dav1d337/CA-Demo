package com.koch.sampleproject.adapter.movies;

import com.koch.sampleproject.model.Change;
import com.koch.sampleproject.model.Movie;
import com.koch.sampleproject.model.MovieResponse;

import java.util.List;

public interface MoviesDataListener {
    void onChangesReceived(MovieResponse movieList);
    void onError(Error error);
}
