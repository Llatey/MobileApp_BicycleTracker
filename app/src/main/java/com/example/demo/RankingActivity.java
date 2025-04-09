package com.example.demo;

import android.os.Bundle;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.tabs.TabLayout;

public class RankingActivity extends BaseActivity {

    private TabLayout tabLayout;
    private RecyclerView rankingsRecyclerView;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_ranking;
    }

    @Override
    protected String getActivityTitle() {
        return "Rankings";
    }

    @Override
    protected int getCurrentNavigationItemId() {
        return R.id.navigation_ranking;
    }

    @Override
    protected void onActivityCreated() {
        // Initialize views
        initializeRankingViews();
        
        // Setup tabs
        setupTabs();
        
        // Setup rankings recycler view
        setupRankingsRecyclerView();
    }
    
    private void initializeRankingViews() {
        tabLayout = findViewById(R.id.tabLayout);
        rankingsRecyclerView = findViewById(R.id.rankingsRecyclerView);
    }
    
    private void setupTabs() {
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Load data based on selected tab
                int position = tab.getPosition();
                switch (position) {
                    case 0: // Weekly
                        loadWeeklyRankings();
                        break;
                    case 1: // Monthly
                        loadMonthlyRankings();
                        break;
                    case 2: // All Time
                        loadAllTimeRankings();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // Do nothing
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Do nothing
            }
        });
    }
    
    private void setupRankingsRecyclerView() {
        rankingsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        // TODO: Set up adapter for rankings
    }
    
    private void loadWeeklyRankings() {
        // TODO: Load weekly rankings data
    }
    
    private void loadMonthlyRankings() {
        // TODO: Load monthly rankings data
    }
    
    private void loadAllTimeRankings() {
        // TODO: Load all time rankings data
    }
} 