package com.koch.sampleproject.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApi {
    @GET("/3/trending/all/week")
    Call<MovieResponse> fetchTrendingMovies(@Query("api_key") String apiKey);
}
