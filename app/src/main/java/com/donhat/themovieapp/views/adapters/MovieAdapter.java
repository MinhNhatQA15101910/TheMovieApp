package com.donhat.themovieapp.views.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.donhat.themovieapp.R;
import com.donhat.themovieapp.databinding.LayoutItemMovieBinding;
import com.donhat.themovieapp.models.Movie;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private ArrayList<Movie> _movies;

    public MovieAdapter(ArrayList<Movie> movies) {
        _movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutItemMovieBinding layoutItemMovieBinding = DataBindingUtil
                .inflate(
                        LayoutInflater.from(parent.getContext()),
                        R.layout.layout_item_movie,
                        parent,
                        false
                );

        return new MovieViewHolder(layoutItemMovieBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = _movies.get(position);

        holder.layoutItemMovieBinding.setMovie(movie);
    }

    @Override
    public int getItemCount() {
        return _movies.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private LayoutItemMovieBinding layoutItemMovieBinding;

        public MovieViewHolder(LayoutItemMovieBinding layoutItemMovieBinding) {
            super(layoutItemMovieBinding.getRoot());
            this.layoutItemMovieBinding = layoutItemMovieBinding;
        }
    }
}
