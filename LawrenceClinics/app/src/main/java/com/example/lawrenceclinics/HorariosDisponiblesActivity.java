package com.example.lawrenceclinics;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lawrenceclinics.api.ApiClinica;
import com.example.lawrenceclinics.api.ServicioRetrofit;
import com.example.lawrenceclinics.api.respuestas.DatosDoctorEspecialidad;
import com.example.lawrenceclinics.api.respuestas.DoctoresEspecialidad;
import com.example.lawrenceclinics.api.respuestas.HorarioDisponible;
import com.example.lawrenceclinics.api.respuestas.HorariosDisponibles;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HorariosDisponiblesActivity extends AppCompatActivity implements HorariosDisponiblesAdapter.Listener {

    private int año, mes, dia;

    private RecyclerView listaHorarios;
    private ApiClinica api;

    private Button BotonSeleccionarFecha;
    private EditText MostrarFecha;
    private Spinner selectorDoctor;

    public static final String ESPECIALIDAD_ID = "ESPECIALIDAD";
    public static final String ESPECIALIDAD_NOMBRE = "ESPECIALIDADN";

    private String idEspecialidad;

    private HorariosDisponiblesAdapter adapter;
    private String idDoctor;
    private String especialidad;
    private List<DatosDoctorEspecialidad> listaDoctores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horarios_disponibles);

        idEspecialidad = getIntent().getStringExtra(ESPECIALIDAD_ID);
        especialidad = getIntent().getStringExtra(ESPECIALIDAD_NOMBRE);
        api = ServicioRetrofit.generarApi();

        BotonSeleccionarFecha = findViewById(R.id.Boton_SeleccionarFecha);
        MostrarFecha = findViewById(R.id.editTextFechaSeleccionable);
        selectorDoctor = findViewById(R.id.selectorDoctores);
        listaHorarios = findViewById(R.id.horariosDisponiblesRv);

        listaHorarios.setLayoutManager(new LinearLayoutManager(this));

        Calendar calendario = Calendar.getInstance();
        año = calendario.get(Calendar.YEAR);
        mes = calendario.get(Calendar.MONTH);
        dia = calendario.get(Calendar.DAY_OF_MONTH);

        mes = mes + 1;

        MostrarFecha.setText(dia + "/" + mes + "/" + año);

        selectorDoctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idDoctor = listaDoctores.get(position).getIdDoctor();
                actualizarListaHorarios();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        api.doctoresEspecialidad(idEspecialidad).enqueue(new Callback<DoctoresEspecialidad>() {
            @Override
            public void onResponse(Call<DoctoresEspecialidad> call, Response<DoctoresEspecialidad> response) {
                if(response.isSuccessful()) {
                    DoctoresEspecialidad datos = response.body();
                    if(!datos.isError()) {
                        listaDoctores = datos.getDatos();
                        ArrayAdapter<DatosDoctorEspecialidad> adaptadorDoctores = new ArrayAdapter<>(HorariosDisponiblesActivity.this, android.R.layout.simple_spinner_item, listaDoctores);
                        adaptadorDoctores.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        selectorDoctor.setAdapter(adaptadorDoctores);
                    }
                    else {
                        Toast.makeText(HorariosDisponiblesActivity.this, datos.getErrorMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<DoctoresEspecialidad> call, Throwable t) {

            }
        });


        BotonSeleccionarFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(HorariosDisponiblesActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        año = year;
                        mes = month;
                        dia = day;

                        mes = mes + 1;

                        MostrarFecha.setText(dia + "/" + mes + "/" + año);
                        actualizarListaHorarios();
                    }
                }, año, mes-1, dia);
                datePickerDialog.show();
            }
        });


    }

    private void actualizarListaHorarios() {
        api.horariosDisponibles(idDoctor,año+"-"+mes+"-"+dia).enqueue(new Callback<HorariosDisponibles>() {
            @Override
            public void onResponse(Call<HorariosDisponibles> call, Response<HorariosDisponibles> response) {
                if(response.isSuccessful()) {
                    HorariosDisponibles datos = response.body();
                    if(!datos.isError()) {
                        adapter = new HorariosDisponiblesAdapter(datos.getDatos());
                        adapter.setListener(HorariosDisponiblesActivity.this);
                        listaHorarios.setAdapter(adapter);
                    }
                    else {
                        Toast.makeText(HorariosDisponiblesActivity.this, datos.getErrorMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<HorariosDisponibles> call, Throwable t) {

            }
        });
    }

    @Override
    public void onHorarioDisponibleClick(HorarioDisponible h) {
        Intent intentoHoraDisponible = new Intent(HorariosDisponiblesActivity.this, VerificarCita.class);
        intentoHoraDisponible.putExtra(VerificarCita.ESPECIALIDAD,especialidad);
        intentoHoraDisponible.putExtra(VerificarCita.DOCTOR_ID,idDoctor);
        intentoHoraDisponible.putExtra(VerificarCita.DOCTOR_NOMBRE,selectorDoctor.getSelectedItem().toString());
        intentoHoraDisponible.putExtra(VerificarCita.FECHA,MostrarFecha.getText().toString());
        intentoHoraDisponible.putExtra(VerificarCita.HORARIO,h.horarioFormateado());
        intentoHoraDisponible.putExtra(VerificarCita.HORARIO_ID,h.getId());

        startActivity(intentoHoraDisponible);
    }

}
