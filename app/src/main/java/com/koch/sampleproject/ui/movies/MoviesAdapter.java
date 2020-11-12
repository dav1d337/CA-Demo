package com.koch.sampleproject.ui.movies;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koch.sampleproject.R;
import com.koch.sampleproject.databinding.ItemMovieBinding;
import com.koch.sampleproject.model.Change;
import com.koch.sampleproject.model.Movie;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<Movie> dataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieBinding binding;

        public ViewHolder(ItemMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Movie movie) {
            binding.setMovie(movie);
            binding.executePendingBindings();
        }
    }

    public MoviesAdapter(List<Movie> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ItemMovieBinding movieBinding = ItemMovieBinding.inflate(layoutInflater, parent, false);

        return new ViewHolder(movieBinding);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        Movie movie = dataSet.get(position);
        viewHolder.bind(movie);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setData(List<Movie> movies) {
        this.dataSet = movies;
        notifyDataSetChanged();
    }
}