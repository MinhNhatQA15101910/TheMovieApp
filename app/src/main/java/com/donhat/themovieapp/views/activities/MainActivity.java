package com.donhat.themovieapp.views.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.donhat.themovieapp.R;
import com.donhat.themovieapp.databinding.ActivityMainBinding;
import com.donhat.themovieapp.models.Movie;
import com.donhat.themovieapp.viewmodels.MainActivityViewModel;
import com.donhat.themovieapp.views.adapters.MovieAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Movie> _movies;
    private RecyclerView _moviesRecyclerView;
    private MovieAdapter _movieAdapter;
    private SwipeRefreshLayout _swipeRefreshLayout;
    private ActivityMainBinding _activityMainBinding;
    private MainActivityViewModel _mainActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        _activityMainBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );

        _mainActivityViewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        getPopularMovies();

        _swipeRefreshLayout = _activityMainBinding.main;
        _swipeRefreshLayout.setColorSchemeResources(R.color.black);
        _swipeRefreshLayout.setOnRefreshListener(this::getPopularMovies);
    }

    private void getPopularMovies() {
        _mainActivityViewModel.getAllMovies().observe(this, movies -> {
            _movies = (ArrayList<Movie>) movies;
            displayMoviesInRecyclerView();
        });
    }

    private void displayMoviesInRecyclerView() {
        _moviesRecyclerView = _activityMainBinding.moviesRecyclerView;

        _movieAdapter = new MovieAdapter(_movies);

        _moviesRecyclerView.setItemAnimator(new DefaultItemAnimator());
        _moviesRecyclerView.setAdapter(_movieAdapter);
        _moviesRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        _movieAdapter.notifyDataSetChanged();
    }
}