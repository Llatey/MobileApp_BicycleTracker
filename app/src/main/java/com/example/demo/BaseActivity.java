package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public abstract class BaseActivity extends AppCompatActivity {
    
    protected BottomNavigationView bottomNavigationView;
    protected Toolbar toolbar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        
        // Initialize views
        initializeViews();
        
        // Setup toolbar
        setupToolbar();
        
        // Setup bottom navigation
        setupBottomNavigation();
        
        // Additional setup
        onActivityCreated();
    }
    
    protected abstract int getLayoutResourceId();
    
    protected void initializeViews() {
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        toolbar = findViewById(R.id.toolbar);
    }
    
    protected void setupToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            if (getSupportActionBar() != null) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                getSupportActionBar().setTitle(getActivityTitle());
            }
        }
    }
    
    protected abstract String getActivityTitle();
    
    protected void setupBottomNavigation() {
        if (bottomNavigationView != null) {
            // Set the current item based on the activity
            int currentItemId = getCurrentNavigationItemId();
            bottomNavigationView.setSelectedItemId(currentItemId);
            
            // Set up navigation listener
            bottomNavigationView.setOnItemSelectedListener(item -> {
                int itemId = item.getItemId();
                
                if (itemId == R.id.navigation_dashboard && !(this instanceof DashboardActivity)) {
                    navigateToActivity(DashboardActivity.class);
                    return true;
                } else if (itemId == R.id.navigation_ranking && !(this instanceof RankingActivity)) {
                    navigateToActivity(RankingActivity.class);
                    return true;
                } else if (itemId == R.id.navigation_booking && !(this instanceof BookingActivity)) {
                    navigateToActivity(BookingActivity.class);
                    return true;
                } else if (itemId == R.id.navigation_feedback && !(this instanceof FeedbackActivity)) {
                    navigateToActivity(FeedbackActivity.class);
                    return true;
                }
                
                return false;
            });
        }
    }
    
    protected abstract int getCurrentNavigationItemId();
    
    protected void navigateToActivity(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    
    protected void onActivityCreated() {
        // Override in subclasses for additional setup
    }
    
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
} 