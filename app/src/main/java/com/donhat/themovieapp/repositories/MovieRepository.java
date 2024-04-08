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

public class MovieRepository {
    private ArrayList<Movie> _movies = new ArrayList<>();
    private MutableLiveData<List<Movie>> _mutableLiveDataMovies = new MutableLiveData<>();
    private Application _application;

    public MovieRepository(Application application) {
        _application = application;
    }

    public MutableLiveData<List<Movie>> getMutableLiveDataMovies() {
        IMovieApiService movieApiService = RetrofitInstance.getMovieApiService();
        Call<Result> call = movieApiService.getPopularMovies(
                _application.getApplicationContext()
                        .getString(R.string.api_key)
        );
    }
}
