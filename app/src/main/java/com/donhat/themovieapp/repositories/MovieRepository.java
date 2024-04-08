package com.donhat.themovieapp.repositories;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.donhat.themovieapp.R;
import com.donhat.themovieapp.apiservices.IMovieApiService;
import com.donhat.themovieapp.apiservices.RetrofitInstance;
import com.donhat.themovieapp.models.Movie;
import com.donhat.themovieapp.models.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private ArrayList<Movie> _movies = new ArrayList<>();
    private final MutableLiveData<List<Movie>> _mutableLiveDataMovies = new MutableLiveData<>();
    private final Application _application;

    public MovieRepository(Application application) {
        _application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveDataMovies() {
        IMovieApiService movieApiService = RetrofitInstance.getMovieApiService();
        Call<Result> call = movieApiService.getPopularMovies(
                _application.getApplicationContext()
                        .getString(R.string.api_key)
        );

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();

                if (result != null && result.getResults() != null) {
                    _movies = (ArrayList<Movie>) result.getResults();
                    _mutableLiveDataMovies.setValue(_movies);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable throwable) {

            }
        });

        return _mutableLiveDataMovies;
    }
}
