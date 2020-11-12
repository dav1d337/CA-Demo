package com.koch.sampleproject.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.koch.sampleproject.databinding.ItemChangeBinding;
import com.koch.sampleproject.model.Change;

import java.util.List;

public class MainAdapter extends  RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<Change> dataSet;

    public static class MainViewHolder extends RecyclerView.ViewHolder {

        private ItemChangeBinding binding;

        public MainViewHolder(ItemChangeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Change change) {
            binding.setChange(change);
            binding.executePendingBindings();
        }
    }

    public MainAdapter(List<Change> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ItemChangeBinding changeBinding = ItemChangeBinding.inflate(layoutInflater, parent, false);

        return new MainViewHolder(changeBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        Change change = dataSet.get(position);
        holder.bind(change);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public void setData(List<Change> changes) {
        this.dataSet = changes;
        notifyDataSetChanged();
    }
}
