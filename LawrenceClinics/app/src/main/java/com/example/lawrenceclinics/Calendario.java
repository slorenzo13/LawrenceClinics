package com.example.lawrenceclinics;

import android.app.DatePickerDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class Calendario extends AppCompatActivity {

    private TextView txtv;
    private Calendar calendario;
    private int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        calendario = Calendar.getInstance();

        day = calendario.get(Calendar.DAY_OF_MONTH);
        month = calendario.get(Calendar.MONTH);
        year = calendario.get(Calendar.YEAR);

        month = month + 1;

        txtv.setText(day + "/" + month + "/" + year);

        txtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(Calendario.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        txtv.setText(day + "/" + month + "/" + year);
                    }
                }, day, month, year);
                datePickerDialog.show();
            }
        });
    }
}
