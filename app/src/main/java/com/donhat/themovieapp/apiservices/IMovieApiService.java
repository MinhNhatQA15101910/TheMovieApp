package com.donhat.themovieapp.apiservices;

import com.donhat.themovieapp.models.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IMovieApiService {
    @GET("movie/popular")
    Call<Result> getPopularMovies(@Query("api_key") String apiKey);
}
