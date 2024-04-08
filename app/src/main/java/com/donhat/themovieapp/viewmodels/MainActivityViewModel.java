package com.donhat.themovieapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.donhat.themovieapp.models.Movie;
import com.donhat.themovieapp.repositories.MovieRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    private final MovieRepository _movieRepository;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        _movieRepository = new MovieRepository(application);
    }

    public LiveData<List<Movie>> getAllMovies() {
        return _movieRepository.getMutableLiveDataMovies();
    }
}
