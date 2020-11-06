package com.koch.sampleproject.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.koch.sampleproject.R;

import java.util.List;

public class MainAdapter extends  RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    private List<String> dataSet;

    public static class MainViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public MainViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }

    public MainAdapter(List<String> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public MainAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView textView = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.main_text_view_item, parent, false);

        MainViewHolder viewHolder = new MainViewHolder(textView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.textView.setText(dataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
