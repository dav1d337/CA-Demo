package com.koch.sampleproject.adapter.movies;

public interface ServerMovieListProvider {
    void fetchTrendingMovies(MoviesReceivedCallback moviesReceivedCallback);
    void fetchUpcomingMovies(MoviesReceivedCallback moviesReceivedCallback);
}
