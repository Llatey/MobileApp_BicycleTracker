package com.example.demo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ActivityViewHolder> {
    private List<AllActivitiesActivity.Activity> activities;

    public ActivitiesAdapter(List<AllActivitiesActivity.Activity> activities) {
        this.activities = activities;
    }

    @NonNull
    @Override
    public ActivityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_activity, parent, false);
        return new ActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ActivityViewHolder holder, int position) {
        AllActivitiesActivity.Activity activity = activities.get(position);
        holder.dateTextView.setText(activity.getDate());
        holder.durationTextView.setText(activity.getDuration());
        holder.distanceTextView.setText(activity.getDistance());
        holder.caloriesTextView.setText(activity.getCalories());
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    static class ActivityViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView durationTextView;
        TextView distanceTextView;
        TextView caloriesTextView;

        ActivityViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            durationTextView = itemView.findViewById(R.id.durationTextView);
            distanceTextView = itemView.findViewById(R.id.distanceTextView);
            caloriesTextView = itemView.findViewById(R.id.caloriesTextView);
        }
    }
} 