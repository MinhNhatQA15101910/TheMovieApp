package com.donhat.themovieapp.apiservices;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static Retrofit _retrofit = null;
    private static String BASE_URL = "https://api.themoviedb.org/3/";

    public static IMovieApiService getMovieApiService() {
        if (_retrofit == null) {
            _retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return _retrofit.create(IMovieApiService.class);
    }
}
