package com.koch.sampleproject.adapter.movies;

import com.koch.sampleproject.model.MovieResponse;

public interface TrendingMoviesReceivedCallback {
    void trendingMoviesReceived(MovieResponse movies);
}
