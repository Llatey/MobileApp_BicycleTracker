package com.example.demo;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class FeedbackActivity extends BaseActivity {

    private RatingBar ratingBar;
    private TextInputEditText feedbackEditText;
    private MaterialButton submitButton;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected String getActivityTitle() {
        return "Feedback";
    }

    @Override
    protected int getCurrentNavigationItemId() {
        return R.id.navigation_feedback;
    }

    @Override
    protected void onActivityCreated() {
        // Initialize views
        initializeFeedbackViews();
        
        // Setup submit button
        setupSubmitButton();
    }
    
    private void initializeFeedbackViews() {
        ratingBar = findViewById(R.id.ratingBar);
        feedbackEditText = findViewById(R.id.feedbackEditText);
        submitButton = findViewById(R.id.submitButton);
    }
    
    private void setupSubmitButton() {
        submitButton.setOnClickListener(v -> {
            float rating = ratingBar.getRating();
            String feedback = feedbackEditText.getText().toString().trim();
            
            if (feedback.isEmpty()) {
                feedbackEditText.setError("Please enter your feedback");
                return;
            }
            
            // TODO: Submit feedback to server
            // For now, just show a toast message
            Toast.makeText(this, "Thank you for your feedback!", Toast.LENGTH_SHORT).show();
            
            // Clear the form
            ratingBar.setRating(0);
            feedbackEditText.setText("");
        });
    }
} 