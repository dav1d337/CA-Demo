package com.koch.sampleproject.ui.movies;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.koch.sampleproject.R;
import com.koch.sampleproject.databinding.MoviesActivityBinding;
import com.koch.sampleproject.ui.main.MainActivity;
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

        viewModel.errorMessage.observe(this, this::displayErrorToast);
        Log.d("lifecycle moviesActivity","onCreate invoked");
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this, providerFactory).get(MoviesViewModel.class);
        viewModel.init();
    }

    public void navigateToMainActivity(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void displayErrorToast(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("hallo start", String.valueOf(viewModel.movies.getValue().size()));
        Log.d("lifecycle moviesActivity","onStart invoked");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("lifecycle moviesActivity","onResume invoked");
        Log.i("hallo res", String.valueOf(viewModel.movies.getValue().size()));
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("hallo pause", String.valueOf(viewModel.movies.getValue().size()));
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
        Log.i("hallo restar", String.valueOf(viewModel.movies.getValue().size()));
        Log.d("lifecycle moviesActivity","onRestart invoked");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("lifecycle moviesActivity","onDestroy invoked");
        viewModel.unregisterListener();
    }

}
