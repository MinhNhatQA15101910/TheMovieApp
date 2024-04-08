package com.donhat.themovieapp.viewmodels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.donhat.themovieapp.repositories.MovieRepository;

public class MainActivityViewModel extends AndroidViewModel {
    private MovieRepository _movieRepository;


    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        _movieRepository = new MovieRepository(application);
    }
}
