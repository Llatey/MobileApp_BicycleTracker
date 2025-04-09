package com.example.demo;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;
import java.util.List;

public class AllActivitiesActivity extends BaseActivity {
    private RecyclerView activitiesRecyclerView;
    private TextView noActivitiesTextView;
    private ActivitiesAdapter activitiesAdapter;
    private List<Activity> activities;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_all_activities;
    }

    @Override
    protected String getActivityTitle() {
        return "All Activities";
    }

    @Override
    protected int getCurrentNavigationItemId() {
        return R.id.navigation_dashboard;
    }

    @Override
    protected void onActivityCreated() {
        initializeViews();
        setupRecyclerView();
        loadActivities();
    }

    @Override
    protected void initializeViews() {
        activitiesRecyclerView = findViewById(R.id.activitiesRecyclerView);
        noActivitiesTextView = findViewById(R.id.noActivitiesTextView);
        setupBottomNavigation();
    }

    private void setupRecyclerView() {
        activities = new ArrayList<>();
        activitiesAdapter = new ActivitiesAdapter(activities);
        activitiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        activitiesRecyclerView.setAdapter(activitiesAdapter);
    }

    private void loadActivities() {
        // TODO: Load activities from database or API
        // For now, we'll just show the "No activities" message
        activitiesRecyclerView.setVisibility(View.GONE);
        noActivitiesTextView.setVisibility(View.VISIBLE);
    }

    // Activity data class
    public static class Activity {
        private String date;
        private String duration;
        private String distance;
        private String calories;

        public Activity(String date, String duration, String distance, String calories) {
            this.date = date;
            this.duration = duration;
            this.distance = distance;
            this.calories = calories;
        }

        public String getDate() { return date; }
        public String getDuration() { return duration; }
        public String getDistance() { return distance; }
        public String getCalories() { return calories; }
    }
} 