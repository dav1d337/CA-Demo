package com.koch.sampleproject.adapter.movies;

import com.koch.sampleproject.model.MovieApi;
import com.koch.sampleproject.model.MovieResponse;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieApiController implements ServerMovieListProvider {

    MovieApi movieApi;

    @Inject
    public MovieApiController(MovieApi movieApi) {
        this.movieApi = movieApi;
    }

    @Override
    public void fetchTrendingMovies(MoviesReceivedCallback moviesReceivedCallback) {
        Call<MovieResponse> call = movieApi.fetchTrendingMovies("0502e000edfc354b128cf682348660a3");

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    movieResponse.getResults().forEach(movie -> System.out.println("Movie:" + movie.getOriginalTitle()));
                    moviesReceivedCallback.trendingMoviesReceived(movieResponse);
                } else {
                    try {
                        moviesReceivedCallback.errorReceived(new Error(response.errorBody().string()));
                    } catch (IOException e) {
                        moviesReceivedCallback.errorReceived(new Error(e));
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                System.out.println("Failure");
                t.printStackTrace();
                moviesReceivedCallback.errorReceived(new Error(t));
            }
        });
    }

    @Override
    public void fetchUpcomingMovies(MoviesReceivedCallback moviesReceivedCallback) {
        Call<MovieResponse> call = movieApi.fetchUpcomingMovies("0502e000edfc354b128cf682348660a3");

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful()) {
                    MovieResponse movieResponse = response.body();
                    movieResponse.getResults().forEach(movie -> System.out.println("Movie:" + movie.getOriginalTitle()));
                    moviesReceivedCallback.trendingMoviesReceived(movieResponse);
                } else {
                    try {
                        moviesReceivedCallback.errorReceived(new Error(response.errorBody().string()));
                    } catch (IOException e) {
                        moviesReceivedCallback.errorReceived(new Error(e));
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                System.out.println("Failure");
                t.printStackTrace();
                moviesReceivedCallback.errorReceived(new Error(t));
            }
        });
    }
}
