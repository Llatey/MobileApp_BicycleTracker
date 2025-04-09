package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DashboardActivity extends BaseActivity {

    private TextView welcomeTextView;
    private TextView dateTextView;
    private TextView distanceTargetTextView;
    private TextView durationTargetTextView;
    private TextView caloriesTargetTextView;
    private LinearProgressIndicator targetProgressIndicator;
    private TextView progressTextView;
    private TextView currentDistanceTextView;
    private TextView currentDurationTextView;
    private TextView currentSpeedTextView;
    private MaterialButton startRideButton;
    private MaterialButton viewAllActivitiesButton;
    private RecyclerView recentActivitiesRecyclerView;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_dashboard;
    }

    @Override
    protected String getActivityTitle() {
        return "Dashboard";
    }

    @Override
    protected int getCurrentNavigationItemId() {
        return R.id.navigation_dashboard;
    }

    @Override
    protected void onActivityCreated() {
        // Initialize views
        initializeDashboardViews();
        
        // Set up date
        updateDate();
        
        // Set up click listeners
        setupClickListeners();
        
        // Set up recent activities recycler view
        setupRecentActivitiesRecyclerView();
    }
    
    private void initializeDashboardViews() {
        welcomeTextView = findViewById(R.id.welcomeTextView);
        dateTextView = findViewById(R.id.dateTextView);
        distanceTargetTextView = findViewById(R.id.distanceTargetTextView);
        durationTargetTextView = findViewById(R.id.durationTargetTextView);
        caloriesTargetTextView = findViewById(R.id.caloriesTargetTextView);
        targetProgressIndicator = findViewById(R.id.targetProgressIndicator);
        progressTextView = findViewById(R.id.progressTextView);
        currentDistanceTextView = findViewById(R.id.currentDistanceTextView);
        currentDurationTextView = findViewById(R.id.currentDurationTextView);
        currentSpeedTextView = findViewById(R.id.currentSpeedTextView);
        startRideButton = findViewById(R.id.startRideButton);
        viewAllActivitiesButton = findViewById(R.id.viewAllActivitiesButton);
        recentActivitiesRecyclerView = findViewById(R.id.recentActivitiesRecyclerView);
    }
    
    private void updateDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM d, yyyy", Locale.getDefault());
        String currentDate = dateFormat.format(new Date());
        dateTextView.setText(currentDate);
    }
    
    private void setupClickListeners() {
        startRideButton.setOnClickListener(v -> {
            // TODO: Implement start ride functionality
            // For now, just show a message
            // Toast.makeText(DashboardActivity.this, "Starting ride...", Toast.LENGTH_SHORT).show();
            
            // Navigate to ride tracking screen
            Intent intent = new Intent(DashboardActivity.this, RideTrackingActivity.class);
            startActivity(intent);
        });
        
        viewAllActivitiesButton.setOnClickListener(v -> {
            // Navigate to all activities screen
            Intent intent = new Intent(DashboardActivity.this, AllActivitiesActivity.class);
            startActivity(intent);
        });
    }
    
    private void setupRecentActivitiesRecyclerView() {
        // Set up recycler view with a linear layout manager
        recentActivitiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        
        // TODO: Set up adapter with actual data
        // For now, we'll leave this as a placeholder
    }
} 