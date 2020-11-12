package com.koch.sampleproject.ui.movies;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.koch.sampleproject.R;
import com.koch.sampleproject.databinding.MoviesActivityBinding;
import com.koch.sampleproject.ui.main.MainViewModel;
import com.koch.sampleproject.ui.utils.ViewModelFactory;
import com.koch.sampleproject.ui.utils.ViewModelProviderFactory;

import java.util.ArrayList;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MoviesActivity extends DaggerAppCompatActivity {

    private MoviesViewModel viewModel;
    private RecyclerView.Adapter adapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();
        MoviesActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.movies_activity);
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);

        adapter = new MoviesAdapter(new ArrayList<>());
        binding.moviesList.setAdapter(adapter);
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this, providerFactory).get(MoviesViewModel.class);
        viewModel.init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("lifecycle moviesActivity","onStart invoked");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle moviesActivity","onResume invoked");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.d("lifecycle moviesActivity","onPause invoked");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.d("lifecycle moviesActivity","onStop invoked");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("lifecycle moviesActivity","onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle moviesActivity","onDestroy invoked");
    }
}
