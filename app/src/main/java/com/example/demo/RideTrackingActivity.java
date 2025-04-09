package com.example.demo;

import android.os.Bundle;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RideTrackingActivity extends BaseActivity {

    private TextView timerTextView;
    private TextView distanceTextView;
    private TextView speedTextView;
    private TextView caloriesTextView;
    private MaterialButton startStopButton;
    private MaterialButton endRideButton;
    
    private boolean isTracking = false;
    private long startTime;
    private SimpleDateFormat timeFormat;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ride_tracking;
    }

    @Override
    protected String getActivityTitle() {
        return "Track Your Ride";
    }

    @Override
    protected int getCurrentNavigationItemId() {
        return R.id.navigation_dashboard;
    }

    @Override
    protected void onActivityCreated() {
        // Initialize views
        initializeViews();
        
        // Set up time format
        timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        
        // Set up click listeners
        setupClickListeners();
    }
    
    @Override
    protected void initializeViews() {
        timerTextView = findViewById(R.id.timerTextView);
        distanceTextView = findViewById(R.id.distanceTextView);
        speedTextView = findViewById(R.id.speedTextView);
        caloriesTextView = findViewById(R.id.caloriesTextView);
        startStopButton = findViewById(R.id.startStopButton);
        endRideButton = findViewById(R.id.endRideButton);
        
        // Set initial values
        timerTextView.setText("00:00:00");
        distanceTextView.setText("0.0 km");
        speedTextView.setText("0.0 km/h");
        caloriesTextView.setText("0 kcal");
    }
    
    private void setupClickListeners() {
        startStopButton.setOnClickListener(v -> {
            if (isTracking) {
                // Stop tracking
                isTracking = false;
                startStopButton.setText("Start");
                // TODO: Pause timer
            } else {
                // Start tracking
                isTracking = true;
                startStopButton.setText("Stop");
                startTime = System.currentTimeMillis();
                // TODO: Start timer
            }
        });
        
        endRideButton.setOnClickListener(v -> {
            // TODO: Save ride data
            // Navigate back to dashboard
            finish();
        });
    }
} 