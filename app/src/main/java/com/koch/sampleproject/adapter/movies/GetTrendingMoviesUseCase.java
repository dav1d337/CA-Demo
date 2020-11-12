package com.koch.sampleproject.adapter.movies;

import com.koch.sampleproject.adapter.changes.ChangeDataListener;
import com.koch.sampleproject.model.Movie;
import com.koch.sampleproject.model.MovieResponse;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.inject.Inject;

public class GetTrendingMoviesUseCase implements TrendingMoviesReceivedCallback {

    private Set<MoviesDataListener> responseListeners = new CopyOnWriteArraySet<>();

    private ServerMovieListProvider serverMovieListProvider;

    @Inject
    public GetTrendingMoviesUseCase(ServerMovieListProvider serverMovieListProvider) {
        this.serverMovieListProvider = serverMovieListProvider;
    }

    public void execute() {
        serverMovieListProvider.fetchTrendingMovies(this);
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
}
