package com.koch.sampleproject.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingResource;

import android.os.Bundle;

import com.koch.sampleproject.MainApplication;
import com.koch.sampleproject.R;
import com.koch.sampleproject.databinding.MainActivityBinding;
import com.koch.sampleproject.network.SimpleIdlingResource;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    private SimpleIdlingResource idlingResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.init();

        layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        String[] testData = {"eins", "zwei", "drei"};
        adapter = new MainAdapter(testData);
        binding.recyclerView.setAdapter(adapter);

        binding.button.setOnClickListener(v -> viewModel.callTestApi(idlingResource));
    }

    /**
     * Only called from test, creates and returns a new {@link SimpleIdlingResource}.
     */
    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (idlingResource == null) {
            idlingResource = new SimpleIdlingResource();
        }
        return idlingResource;
    }
}