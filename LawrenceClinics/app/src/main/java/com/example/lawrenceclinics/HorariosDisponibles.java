package com.example.lawrenceclinics;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class HorariosDisponibles extends AppCompatActivity{

    private int año, mes, dia;

    private ConstraintLayout hora1, hora2;
    private Button BotonSeleccionarFecha;
    private EditText MostrarFecha;
    private Spinner selectorDoctor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_disponibles);

        BotonSeleccionarFecha = findViewById(R.id.Boton_SeleccionarFecha);
        MostrarFecha = findViewById(R.id.editTextFechaSeleccionable);
        selectorDoctor = findViewById(R.id.selectorDoctores);

        hora1 = findViewById(R.id.horarioDisponible1);
        hora2 = findViewById(R.id.horarioDisponible2);

        Calendar calendario = Calendar.getInstance();
        año = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH);
        dia = calendario.get(Calendar.DAY_OF_MONTH);


        mes = mes + 1;

        MostrarFecha.setText(dia + "/" + mes + "/" + año);

        MostrarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(HorariosDisponibles.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mes = mes+1;
                        MostrarFecha.setText(dia + "/" + mes + "/" + año);
                    }
                }, dia, mes, año);
                datePickerDialog.show();
            }
        });

        hora1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentoHora1 = new Intent(HorariosDisponibles.this, VerificarCita.class);
                startActivity(intentoHora1);
            }
        });


        hora2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intentoHora2 = new Intent(HorariosDisponibles.this, VerificarCita.class);
                    startActivity(intentoHora2);
            }
        });

    }


}
