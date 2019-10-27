package com.bilalmoreno.malagasport.ui.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {

    private Calendar calendar = Calendar.getInstance();
    private SimpleDateFormat dateFormat;
    private EditText editText;

    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int year;
        int month;
        int day;
        if (!editText.getText().toString().isEmpty()) {

            try {
                calendar.setTime(dateFormat.parse(editText.getText().toString()));
            } catch (ParseException e) {
                Toast.makeText(getContext(), "Fecha no válida", Toast.LENGTH_SHORT).show();
            }
        }

        // Use the current date as the default date in the picker
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        calendar.set(year, month, day);
        dateFormat.setCalendar(calendar);
        editText.setText(dateFormat.format(calendar.getTime()));
    }

    public void setEditText(EditText editText) {
        this.editText = editText;
    }
}
