package com.example.tasktracker;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class modal extends DialogFragment implements Dates.TimeSelectedListener{

Button btnDate;
    private Calendar calendar;
    private TextView timeView;
    String selectedDate,selectedTime;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View modalView = inflater.inflate(R.layout.modals,null);
        timeView = modalView.findViewById(R.id.timeView);


            btnDate = modalView.findViewById(R.id.selectDate);
            btnDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Dates selectedDate = new Dates();
                    selectedDate.show(getActivity().getSupportFragmentManager(),"Dates" );*/
                    showDatePicker();
                }
            });


        final EditText desc = modalView.findViewById(R.id.desc);
    builder.setView(modalView)
            .setTitle("Add a task")
            .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    String taskDESC = desc.getText().toString();
                    String deadLIne = timeView.getText().toString();
                    db dbHelper = new db(getContext());
                    dbHelper.addTask(taskDESC,"pending",deadLIne);
                    Toast.makeText(getContext(),"Task added",Toast.LENGTH_SHORT).show();
                }
            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
        return builder.create();
    }

    @Override
    public void onTimeSelected(String formattedTime) {
        //View modalView = getView();
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View modalView = inflater.inflate(R.layout.modals,null);
        /*if (modalView != null) {

            if (timeView != null) {

            }
        }*/
        TextView timeView = modalView.findViewById(R.id.timeView);
        timeView.setText(formattedTime);
    }
    private void showDatePicker() {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        selectedDate = formatDate(year, monthOfYear, dayOfMonth);
                        showTimePicker();
                    }
                }, year, month, day);

        datePickerDialog.show();
    }

    private void showTimePicker() {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedTime = (String.format("%02d:%02d", hourOfDay, minute));
                        timeView.setText(selectedDate + " " + selectedTime);
                    }
                }, hour, minute, true);

        timePickerDialog.show();
    }
    private String formatDate(int year, int month, int day) {

        return String.format("%d-%02d-%02d", year, month + 1, day);
    }
}
