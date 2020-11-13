package com.koch.sampleproject.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("/3/trending/all/day")
    Call<MovieResponse> fetchTrendingMovies(@Query("api_key") String apiKey);

    @GET("/3/movie/upcoming")
    Call<MovieResponse> fetchUpcomingMovies(@Query("api_key") String apiKey);
}
