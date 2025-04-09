package com.example.demo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.slider.Slider;
import com.google.android.material.textfield.TextInputEditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookingActivity extends BaseActivity {

    private AutoCompleteTextView locationAutoCompleteTextView;
    private TextInputEditText dateEditText;
    private TextInputEditText timeEditText;
    private Slider durationSlider;
    private TextView durationTextView;
    private RadioGroup bicycleTypeRadioGroup;
    private TextView summaryDurationTextView;
    private TextView rateTextView;
    private TextView totalPriceTextView;
    private MaterialButton bookButton;

    private Calendar calendar;
    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat timeFormatter;

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_booking;
    }

    @Override
    protected String getActivityTitle() {
        return "Book a Bicycle";
    }

    @Override
    protected int getCurrentNavigationItemId() {
        return R.id.navigation_booking;
    }

    @Override
    protected void onActivityCreated() {
        // Initialize views
        initializeBookingViews();
        
        // Setup date and time formatters
        setupFormatters();
        
        // Setup location dropdown
        setupLocationDropdown();
        
        // Setup date and time pickers
        setupDateAndTimePickers();
        
        // Setup duration slider
        setupDurationSlider();
        
        // Setup bicycle type radio group
        setupBicycleTypeRadioGroup();
        
        // Setup book button
        setupBookButton();
    }

    private void initializeBookingViews() {
        locationAutoCompleteTextView = findViewById(R.id.locationAutoCompleteTextView);
        dateEditText = findViewById(R.id.dateEditText);
        timeEditText = findViewById(R.id.timeEditText);
        durationSlider = findViewById(R.id.durationSlider);
        durationTextView = findViewById(R.id.durationTextView);
        bicycleTypeRadioGroup = findViewById(R.id.bicycleTypeRadioGroup);
        summaryDurationTextView = findViewById(R.id.summaryDurationTextView);
        rateTextView = findViewById(R.id.rateTextView);
        totalPriceTextView = findViewById(R.id.totalPriceTextView);
        bookButton = findViewById(R.id.bookButton);
        
        calendar = Calendar.getInstance();
    }

    private void setupFormatters() {
        dateFormatter = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        timeFormatter = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        
        // Set default date and time
        dateEditText.setText(dateFormatter.format(calendar.getTime()));
        timeEditText.setText(timeFormatter.format(calendar.getTime()));
    }

    private void setupLocationDropdown() {
        String[] locations = new String[] {
            "Central Station", "City Park", "Shopping Mall", "University Campus", "Sports Center"
        };
        
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
            this,
            android.R.layout.simple_dropdown_item_1line,
            locations
        );
        
        locationAutoCompleteTextView.setAdapter(adapter);
    }

    private void setupDateAndTimePickers() {
        dateEditText.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, month);
                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    dateEditText.setText(dateFormatter.format(calendar.getTime()));
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            );
            
            // Set minimum date to today
            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            
            datePickerDialog.show();
        });
        
        timeEditText.setOnClickListener(v -> {
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                (view, hourOfDay, minute) -> {
                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    calendar.set(Calendar.MINUTE, minute);
                    timeEditText.setText(timeFormatter.format(calendar.getTime()));
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                false
            );
            
            timePickerDialog.show();
        });
    }

    private void setupDurationSlider() {
        durationSlider.addOnChangeListener((slider, value, fromUser) -> {
            int minutes = (int) value;
            int hours = minutes / 60;
            int remainingMinutes = minutes % 60;
            
            String durationText;
            if (hours == 1) {
                durationText = "1 hour";
            } else if (hours > 1) {
                durationText = hours + " hours";
            } else {
                durationText = remainingMinutes + " minutes";
            }
            
            if (hours > 0 && remainingMinutes > 0) {
                durationText = hours + " hour" + (hours > 1 ? "s" : "") + " " + remainingMinutes + " minutes";
            }
            
            durationTextView.setText(durationText);
            summaryDurationTextView.setText(durationText);
            updateTotalPrice();
        });
    }

    private void setupBicycleTypeRadioGroup() {
        bicycleTypeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            updateTotalPrice();
        });
    }

    private void updateTotalPrice() {
        int minutes = (int) durationSlider.getValue();
        double hours = minutes / 60.0;
        
        double ratePerHour = 5.0; // Base rate
        
        // Adjust rate based on bicycle type
        int selectedBicycleTypeId = bicycleTypeRadioGroup.getCheckedRadioButtonId();
        if (selectedBicycleTypeId == R.id.electricRadioButton) {
            ratePerHour = 8.0; // Electric bikes cost more
        } else if (selectedBicycleTypeId == R.id.mountainRadioButton) {
            ratePerHour = 6.0; // Mountain bikes cost a bit more
        }
        
        double totalPrice = hours * ratePerHour;
        
        rateTextView.setText("$" + String.format("%.2f", ratePerHour) + "/hour");
        totalPriceTextView.setText("$" + String.format("%.2f", totalPrice));
    }

    private void setupBookButton() {
        bookButton.setOnClickListener(v -> {
            String location = locationAutoCompleteTextView.getText().toString();
            
            if (location.isEmpty()) {
                locationAutoCompleteTextView.setError("Please select a location");
                return;
            }
            
            // TODO: Implement actual booking logic
            // For now, just show a success message
            Toast.makeText(this, "Booking successful!", Toast.LENGTH_SHORT).show();
            
            // Navigate back to dashboard
            finish();
        });
    }
} 