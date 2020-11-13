package com.koch.sampleproject.adapter.movies;

import com.koch.sampleproject.model.MovieResponse;

public interface MoviesReceivedCallback {
    void trendingMoviesReceived(MovieResponse movies);
    void upcomingMoviesReceived(MovieResponse movies);
    void errorReceived(Error e);
}
