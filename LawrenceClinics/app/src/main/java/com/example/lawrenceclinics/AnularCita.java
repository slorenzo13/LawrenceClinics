package com.example.lawrenceclinics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lawrenceclinics.api.ApiClinica;
import com.example.lawrenceclinics.api.ServicioRetrofit;
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
    private Button AnularCita, EnviarMotivo;
    private TextView textEspecialidad, textDoctor, textFecha, textHorario;
    private ApiClinica apiClinica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anular_cita);

        apiClinica = ServicioRetrofit.generarApi();

        CodCta = findViewById(R.id.Codigo_AnularCita);
        MotivoCita = findViewById(R.id.MotivoAnularCita);

        BotonSearch = findViewById(R.id.BotonBusquedaCita);

        AnularCita = findViewById(R.id.Boton_AnularCita);
        EnviarMotivo = findViewById(R.id.Boton_EnviarMotivo);

        textEspecialidad = findViewById(R.id.textViewEspecialidad);
        textDoctor = findViewById(R.id.textViewDoctor);
        textFecha = findViewById(R.id.textViewFecha);
        textHorario = findViewById(R.id.textViewHorario);

        EnviarMotivo.setEnabled(false);
/*
        BotonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cedula = CodCta.getText().toString();
                Call<UltimaCita> peticion = apiClinica.obtenerUltimaCita(cedula);

                peticion.enqueue(new Callback<UltimaCita>() {
                    @Override
                    public void onResponse(Call<UltimaCita> call, Response<UltimaCita> response) {
                        UltimaCita ultimaCita = response.body();
                        Log.d("info", ultimaCita.toString());
                        //System.out.println(ultimaCita.toString());
                        if(ultimaCita.isError()) {
                            Toast.makeText(com.example.lawrenceclinics.AnularCita.this, ultimaCita.getErrorMsg(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            String nombreDoctor = ultimaCita.getDatos().getNombreDoctor();
                            String nombreArea = ultimaCita.getDatos().get();
                            String fecha = ultimaCita.getDatos().getFecha();

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            SimpleDateFormat diaMesAnno = new SimpleDateFormat("yyyy-MM-dd");
                            SimpleDateFormat hora = new SimpleDateFormat("HH:mm");
                            try {
                                Date date = sdf.parse(fecha);

                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(date);

                                textFecha.setText(diaMesAnno.format(date));
                                textHorario.setText(hora.format(date));

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

        AnularCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String numCedula = CodCta.getText().toString();
                Call<com.example.lawrenceclinics.api.respuestas.AnularCita> peticionAnular = apiClinica.cancelarCita(numCedula);

                if (textEspecialidad.getText().toString().length() == 0 && textDoctor.getText().toString().length() == 0 &&
                        textFecha.getText().toString().length() == 0 && textHorario.getText().toString().length() == 0){
                    Toast.makeText(com.example.lawrenceclinics.AnularCita.this, "No puede cancelarse una cita vacía", Toast.LENGTH_SHORT).show();
                }

                else if(MotivoCita.getText().toString().length() == 0){
                    Toast.makeText(com.example.lawrenceclinics.AnularCita.this, "Debe insertar un motivo para cancelar su cita", Toast.LENGTH_SHORT).show();
                }
                else{
                    peticionAnular.enqueue(new Callback<com.example.lawrenceclinics.api.respuestas.AnularCita>() {
                        @Override
                        public void onResponse(Call<com.example.lawrenceclinics.api.respuestas.AnularCita> call, Response<com.example.lawrenceclinics.api.respuestas.AnularCita> response) {
                            com.example.lawrenceclinics.api.respuestas.AnularCita anCita = response.body();
                            if (anCita.isError()){
                                Toast.makeText(com.example.lawrenceclinics.AnularCita.this, anCita.getErrorMsg(), Toast.LENGTH_SHORT).show();
                            }
                            else{

                            }
                        }

                        @Override
                        public void onFailure(Call<com.example.lawrenceclinics.api.respuestas.AnularCita> call, Throwable t) {

                        }
                    });
                }

                }
            });*/

    }
}
