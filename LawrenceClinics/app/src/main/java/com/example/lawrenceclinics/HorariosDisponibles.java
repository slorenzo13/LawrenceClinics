package com.example.lawrenceclinics;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HorariosDisponibles extends AppCompatActivity{

    private int año, mes, dia;

    private ConstraintLayout hora1, hora2, hora3, hora4, hora5, hora6;
    private Button BotonSeleccionarFecha;
    private EditText MostrarFecha;
    private Spinner selectorDoctor;

    public static final String ESPECIALIDAD = "ESPECIALIDAD";

    private String especialidad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_disponibles);

        especialidad = getIntent().getStringExtra(ESPECIALIDAD);

        BotonSeleccionarFecha = findViewById(R.id.Boton_SeleccionarFecha);
        MostrarFecha = findViewById(R.id.editTextFechaSeleccionable);
        selectorDoctor = findViewById(R.id.selectorDoctores);

        hora1 = findViewById(R.id.horarioDisponible1);
        hora2 = findViewById(R.id.horarioDisponible2);
        hora3 = findViewById(R.id.horarioDisponible3);
        hora4 = findViewById(R.id.horarioDisponible4);
        hora5 = findViewById(R.id.horarioDisponible5);
        hora6 = findViewById(R.id.horarioDisponible6);

        Calendar calendario = Calendar.getInstance();
        año = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH);
        dia = calendario.get(Calendar.DAY_OF_MONTH);


        mes = mes + 1;

        MostrarFecha.setText(dia + "/" + mes + "/" + año);

        BotonSeleccionarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(HorariosDisponibles.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        MostrarFecha.setText(day + " / " + (month+1) + " / " + year);
                    }
                }, dia, mes, año);
                datePickerDialog.show();
            }
        });

        hora1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentoHora1 = new Intent(HorariosDisponibles.this, VerificarCita.class);
                intentoHora1.putExtra(VerificarCita.ESPECIALIDAD,especialidad);
                intentoHora1.putExtra(VerificarCita.DOCTOR,selectorDoctor.getSelectedItem().toString());
                intentoHora1.putExtra(VerificarCita.FECHA,MostrarFecha.getText().toString());
                intentoHora1.putExtra(VerificarCita.HORARIO,"8.00AM - 8.30AM");
                startActivity(intentoHora1);
            }
        });


        hora2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intentoHora2 = new Intent(HorariosDisponibles.this, VerificarCita.class);
                    intentoHora2.putExtra(VerificarCita.ESPECIALIDAD,especialidad);
                    intentoHora2.putExtra(VerificarCita.DOCTOR,selectorDoctor.getSelectedItem().toString());
                    intentoHora2.putExtra(VerificarCita.FECHA,MostrarFecha.getText().toString());
                    intentoHora2.putExtra(VerificarCita.HORARIO,"8.30AM - 9.00AM");
                    startActivity(intentoHora2);
            }
        });

        hora3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentoHora3 = new Intent(HorariosDisponibles.this, VerificarCita.class);
                intentoHora3.putExtra(VerificarCita.ESPECIALIDAD,especialidad);
                intentoHora3.putExtra(VerificarCita.DOCTOR,selectorDoctor.getSelectedItem().toString());
                intentoHora3.putExtra(VerificarCita.FECHA,MostrarFecha.getText().toString());
                intentoHora3.putExtra(VerificarCita.HORARIO,"9.00AM - 9.30AM");
                startActivity(intentoHora3);
            }
        });

        hora4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentoHora4 = new Intent(HorariosDisponibles.this, VerificarCita.class);
                intentoHora4.putExtra(VerificarCita.ESPECIALIDAD,especialidad);
                intentoHora4.putExtra(VerificarCita.DOCTOR,selectorDoctor.getSelectedItem().toString());
                intentoHora4.putExtra(VerificarCita.FECHA,MostrarFecha.getText().toString());
                intentoHora4.putExtra(VerificarCita.HORARIO,"9.30AM - 10.00AM");
                startActivity(intentoHora4);
            }
        });

        hora5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentoHora5 = new Intent(HorariosDisponibles.this, VerificarCita.class);
                intentoHora5.putExtra(VerificarCita.ESPECIALIDAD,especialidad);
                intentoHora5.putExtra(VerificarCita.DOCTOR,selectorDoctor.getSelectedItem().toString());
                intentoHora5.putExtra(VerificarCita.FECHA,MostrarFecha.getText().toString());
                intentoHora5.putExtra(VerificarCita.HORARIO,"10.00AM - 10.30AM");
                startActivity(intentoHora5);
            }
        });

        hora6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentoHora6 = new Intent(HorariosDisponibles.this, VerificarCita.class);
                intentoHora6.putExtra(VerificarCita.ESPECIALIDAD,especialidad);
                intentoHora6.putExtra(VerificarCita.DOCTOR,selectorDoctor.getSelectedItem().toString());
                intentoHora6.putExtra(VerificarCita.FECHA,MostrarFecha.getText().toString());
                intentoHora6.putExtra(VerificarCita.HORARIO,"10.30AM - 11.00AM");
                startActivity(intentoHora6);
            }
        });

        //SelectorDoctores();
        List<String> listaDoctores = new ArrayList<>();
        listaDoctores.add("Sergio García");
        listaDoctores.add("Federico González");
        listaDoctores.add("Fernando Martínez");
        listaDoctores.add("Alejandro Pérez");
        listaDoctores.add("Rodrigo Santos");

        ArrayAdapter<String> adaptadorDoctores = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaDoctores);
        adaptadorDoctores.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectorDoctor.setAdapter(adaptadorDoctores);
    }

   /* public void SelectorDoctores(){
        List<String> listaDoctores = new ArrayList<>();
        listaDoctores.add("Sergio García");
        listaDoctores.add("Federico González");
        listaDoctores.add("Fernando Martínez");
        listaDoctores.add("Alejandro Pérez");
        listaDoctores.add("Rodrigo Santos");

        ArrayAdapter<String> adaptadorDoctores = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listaDoctores);
        adaptadorDoctores.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectorDoctor.setAdapter(adaptadorDoctores);

        selectorDoctor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });
    } */

}
