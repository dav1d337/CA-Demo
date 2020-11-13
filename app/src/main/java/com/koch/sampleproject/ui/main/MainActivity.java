package com.koch.sampleproject.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.koch.sampleproject.R;
import com.koch.sampleproject.databinding.MainActivityBinding;
import com.koch.sampleproject.model.Change;
import com.koch.sampleproject.network.SimpleIdlingResource;
import com.koch.sampleproject.ui.movies.MoviesActivity;
import com.koch.sampleproject.ui.utils.ViewModelProviderFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity {

    private MainViewModel viewModel;

    private RecyclerView.Adapter adapter;

    @Nullable
    private SimpleIdlingResource idlingResource;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();

        MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);

        adapter = new MainAdapter(new ArrayList<>());
        binding.recyclerView.setAdapter(adapter);

        viewModel.changes.observe(this, this::onListChanged);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.unregisterListener();
    }

    public void navigateToMoviesActivity(View view) {
        Intent intent = new Intent(this, MoviesActivity.class);
        startActivity(intent);
    }

    private void onListChanged(List<Change> changes) {
        if (idlingResource != null) {
            idlingResource.setIdleState(true);
        }
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this, providerFactory).get(MainViewModel.class);
        viewModel.init();
    }

    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public SimpleIdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new SimpleIdlingResource();
        }
        return idlingResource;
    }
}