package com.koch.sampleproject.ui.utils;


import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.koch.sampleproject.model.Change;
import com.koch.sampleproject.model.Movie;
import com.koch.sampleproject.ui.main.MainAdapter;
import com.koch.sampleproject.ui.movies.MoviesAdapter;

import java.util.List;

public class CustomBindings  {

    @BindingAdapter("change_binding")
    static public void bindChanges(RecyclerView view, List<Change> dataSet) {
        if (view.getAdapter() instanceof MainAdapter) {
            ((MainAdapter) view.getAdapter()).setData(dataSet);
        }
    }

    @BindingAdapter("movie_binding")
    static public void bindMovies(RecyclerView view, List<Movie> dataSet) {
        if (view.getAdapter() instanceof MoviesAdapter) {
            ((MoviesAdapter) view.getAdapter()).setData(dataSet);
        }
    }
}
