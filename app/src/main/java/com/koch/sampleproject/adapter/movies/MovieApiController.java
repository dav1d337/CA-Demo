package com.koch.sampleproject.adapter.movies;

import com.koch.sampleproject.model.MovieApi;
import com.koch.sampleproject.model.MovieResponse;

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
    public void fetchTrendingMovies(TrendingMoviesReceivedCallback trendingMoviesReceivedCallback) {
        Call<MovieResponse> call = movieApi.fetchTrendingMovies("0502e000edfc354b128cf682348660a3");

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                //MovieResponse movieResponse = objectMapper.readValue(response.body(), MovieResponse.class);
                movieResponse.getResults().forEach(movie -> System.out.println("Movie:" + movie.getOriginalTitle()));
                trendingMoviesReceivedCallback.trendingMoviesReceived(movieResponse);
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                System.out.println("Failure");
                t.printStackTrace();
            }
        });
    }
}
