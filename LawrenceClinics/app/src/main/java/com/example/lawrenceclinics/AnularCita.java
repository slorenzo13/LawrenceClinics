package com.example.lawrenceclinics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lawrenceclinics.api.ApiClinica;
import com.example.lawrenceclinics.api.ServicioRetrofit;
import com.example.lawrenceclinics.api.respuestas.DatosUltimaCita;
import com.example.lawrenceclinics.api.respuestas.UltimaCita;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnularCita extends AppCompatActivity {

    private EditText CodCta, MotivoCita;
    private ImageButton BotonSearch;
    private Button AnularCita;
    private TextView textEspecialidad, textDoctor, textFecha, textHorario;
    private ApiClinica apiClinica;
    private String idCita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anular_cita);

        apiClinica = ServicioRetrofit.generarApi();

        CodCta = findViewById(R.id.Codigo_AnularCita);
        MotivoCita = findViewById(R.id.MotivoAnularCita);

        BotonSearch = findViewById(R.id.BotonBusquedaCita);

        AnularCita = findViewById(R.id.Boton_AnularCita);

        AnularCita.setEnabled(false);

        textEspecialidad = findViewById(R.id.textViewEspecialidad);
        textDoctor = findViewById(R.id.textViewDoctor);
        textFecha = findViewById(R.id.textViewFecha);
        textHorario = findViewById(R.id.textViewHorario);

        BotonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cedula = CodCta.getText().toString();
                Call<UltimaCita> peticion = apiClinica.obtenerUltimaCita(cedula);

                peticion.enqueue(new Callback<UltimaCita>() {
                    @Override
                    public void onResponse(Call<UltimaCita> call, Response<UltimaCita> response) {
                        UltimaCita ultimaCita = response.body();
                        if (ultimaCita.isError()) {
                            idCita = null;
                            Toast.makeText(com.example.lawrenceclinics.AnularCita.this, ultimaCita.getErrorMsg(), Toast.LENGTH_SHORT).show();
                        } else {
                            DatosUltimaCita datosCita = ultimaCita.getDatos();
                            String nombreArea = datosCita.getNombreArea();
                            String fecha = datosCita.getFecha();
                            String nombreDoctor = datosCita.getNombreDoctor();
                            String hora = datosCita.getHora();
                            idCita = datosCita.getIdCita();

                            SimpleDateFormat formatoEsp = new SimpleDateFormat("dd-MM-yyyy");
                            SimpleDateFormat formatoApi = new SimpleDateFormat("yyyy-MM-dd");
                            try {
                                Date date = formatoApi.parse(fecha);

                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(date);

                                textFecha.setText(formatoEsp.format(date));
                                textHorario.setText(hora);

                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            textDoctor.setText(nombreDoctor);
                            textEspecialidad.setText(nombreArea);
                        }
                    }

                    @Override
                    public void onFailure(Call<UltimaCita> call, Throwable t) {

                    }
                });
            }
        });

        MotivoCita.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                AnularCita.setEnabled(count > 0 && idCita != null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        AnularCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String motivo = MotivoCita.getText().toString();
                Call<com.example.lawrenceclinics.api.respuestas.AnularCita> peticionAnular = apiClinica.anularCita(idCita ,motivo);

                peticionAnular.enqueue(new Callback<com.example.lawrenceclinics.api.respuestas.AnularCita>() {
                    @Override
                    public void onResponse(Call<com.example.lawrenceclinics.api.respuestas.AnularCita> call, Response<com.example.lawrenceclinics.api.respuestas.AnularCita> response) {
                        com.example.lawrenceclinics.api.respuestas.AnularCita anCita = response.body();
                        if (anCita.isError()) {
                            Toast.makeText(com.example.lawrenceclinics.AnularCita.this, anCita.getErrorMsg(), Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AnularCita.this, "Cita anulada con éxito", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<com.example.lawrenceclinics.api.respuestas.AnularCita> call, Throwable t) {

                    }
                });

            }
        });


    }
}
