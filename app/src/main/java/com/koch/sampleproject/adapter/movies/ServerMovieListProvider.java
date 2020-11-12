package com.koch.sampleproject.adapter.movies;

import com.koch.sampleproject.adapter.changes.ChangesReceivedCallback;

public interface ServerMovieListProvider {
    void fetchTrendingMovies(TrendingMoviesReceivedCallback trendingMoviesReceivedCallback);
}
