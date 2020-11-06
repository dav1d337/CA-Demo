package com.koch.sampleproject.ui.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.IdlingResource;

import android.os.Bundle;
import android.view.View;

import com.koch.sampleproject.MainApplication;
import com.koch.sampleproject.R;
import com.koch.sampleproject.databinding.MainActivityBinding;
import com.koch.sampleproject.model.Change;
import com.koch.sampleproject.network.SimpleIdlingResource;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;
    List<String> testData = new ArrayList<>();

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

        adapter = new MainAdapter(testData);
        binding.recyclerView.setAdapter(adapter);
        binding.button.setOnClickListener(v ->
                viewModel.callTestApi(idlingResource)
        );

        // TODO: databinding
        viewModel.changes.observe(this, new Observer<List<Change>>() {
            @Override
            public void onChanged(List<Change> changes) {
                testData.clear();
                changes.forEach(change -> {
                    testData.add(change.getSubject());
                    adapter.notifyDataSetChanged();
                });
            }
        });
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