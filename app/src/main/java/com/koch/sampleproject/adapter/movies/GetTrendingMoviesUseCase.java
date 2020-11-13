package com.koch.sampleproject.adapter.movies;

import com.koch.sampleproject.model.MovieResponse;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.inject.Inject;

public class GetTrendingMoviesUseCase implements MoviesReceivedCallback {

    private Set<MoviesDataListener> responseListeners = new CopyOnWriteArraySet<>();

    private ServerMovieListProvider serverMovieListProvider;

    @Inject
    public GetTrendingMoviesUseCase(ServerMovieListProvider serverMovieListProvider) {
        this.serverMovieListProvider = serverMovieListProvider;
    }

    public void getTrending() {
        serverMovieListProvider.fetchTrendingMovies(this);
    }

    public void getUpcoming() {
        serverMovieListProvider.fetchUpcomingMovies(this);
    }

    public void registerListener(MoviesDataListener dataListener) {
        responseListeners.add(dataListener);
    }

    public void unregisterListener(MoviesDataListener dataListener) {
        responseListeners.remove(dataListener);
    }

    private void notifyListener(MovieResponse movies) {
        responseListeners.forEach(listener -> listener.onChangesReceived(movies));
    }

    @Override
    public void trendingMoviesReceived(MovieResponse movies) {
        notifyListener(movies);
    }

    @Override
    public void upcomingMoviesReceived(MovieResponse movies) {
        notifyListener(movies);
    }

    @Override
    public void errorReceived(Error e) {
        responseListeners.forEach(listener -> listener.onError(e));
    }
}
