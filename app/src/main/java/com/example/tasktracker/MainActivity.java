package com.example.tasktracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
Button btnModal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnModal = findViewById(R.id.showModal);
        btnModal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modal myModal = new modal();
                myModal.show(getSupportFragmentManager(),"dialog");
            }
        });
    }

//    private void showTimePickerDialog() {
//        // Create a TimePickerDialog
//        Dates timePickerDialog = new Dates(MainActivity.this,
//                new TimePickerDialog.OnTimeSetListener() {
//                    @Override
//                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//                        // Handle time selection
//                        // Get the TextView from the modal dialog and set the selected time
//                        TextView timeTextView = modals.findViewById(R.id.TimeView);
//                        String selectedTime = String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute);
//                        timeTextView.setText(selectedTime);
//                    }
//                },  // Set initial time to current time
//                Calendar.getInstance().get(Calendar.HOUR_OF_DAY),
//                Calendar.getInstance().get(Calendar.MINUTE),
//                true); // 24-hour format
//
//        // Show the TimePickerDialog
//        timePickerDialog.show();
//    }
}