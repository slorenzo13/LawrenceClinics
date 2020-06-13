package com.example.lawrenceclinics;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lawrenceclinics.api.ApiClinica;
import com.example.lawrenceclinics.api.ServicioRetrofit;
import com.example.lawrenceclinics.api.respuestas.AgregarCita;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificarCita extends AppCompatActivity {

    public static final String ESPECIALIDAD = "ESPECIALIDAD";
    public static final String DOCTOR_NOMBRE = "DOCTOR";
    public static final String DOCTOR_ID = "DOCTORI";
    public static final String FECHA = "FECHA";
    public static final String HORARIO = "HORARIO";
    public static final String HORARIO_ID = "HORARIOI";
    private Button verificarCita;
    private TextView especialidad, doctor, fecha, horario;
    private ApiClinica apiClinica;
    private SimpleDateFormat dateFormatInicio = new SimpleDateFormat("dd/MM/yyyy");
    private SimpleDateFormat dateFormatFin = new SimpleDateFormat("yyyy-MM-dd");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar_cita);

        apiClinica = ServicioRetrofit.generarApi();

        especialidad = findViewById(R.id.resultadoEspecialidad_agendado);
        doctor = findViewById(R.id.resultadoDoctor_agendado);
        fecha = findViewById(R.id.resultadoFecha_agendado);
        horario = findViewById(R.id.resultadoHorario_agendado);

        String tEspecialidad = getIntent().getStringExtra(ESPECIALIDAD);
        String tDoctorId = getIntent().getStringExtra(DOCTOR_ID);
        String tHorarioId = getIntent().getStringExtra(HORARIO_ID);
        String tDoctor = getIntent().getStringExtra(DOCTOR_NOMBRE);
        String tFecha = getIntent().getStringExtra(FECHA);
        String tHorario = getIntent().getStringExtra(HORARIO);

        this.especialidad.setText(tEspecialidad);
        this.doctor.setText(tDoctor);
        this.fecha.setText(tFecha);
        this.horario.setText(tHorario);

        verificarCita = findViewById(R.id.BotonAgendarCita);

        verificarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tEspecialidad = especialidad.getText().toString();
                String tDoctor = doctor.getText().toString();
                String tFecha = fecha.getText().toString();
                String tHorario = horario.getText().toString();
                Cita cita = new Cita(tEspecialidad, tDoctor,tFecha,tHorario);

                Date formatoInicio = null;
                try {
                    formatoInicio = dateFormatInicio.parse(tFecha);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                String fechaFin = dateFormatFin.format(formatoInicio);

                apiClinica.insertarCita(tDoctorId, fechaFin, tHorarioId).enqueue(new Callback<AgregarCita>() {
                    @Override
                    public void onResponse(Call<AgregarCita> call, Response<AgregarCita> response) {
                        if(response.isSuccessful()) {
                            AgregarCita datos = response.body();
                            if(!datos.isError()) {
                                Toast.makeText(VerificarCita.this, datos.getDatos(), Toast.LENGTH_SHORT).show();
                                Intent intentoRegresoPantallaPrincipal = new Intent(VerificarCita.this, AgendaCitasMedicas.class);
                                startActivity(intentoRegresoPantallaPrincipal);
                            }
                            else {
                                Toast.makeText(VerificarCita.this, datos.getErrorMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AgregarCita> call, Throwable t) {

                    }
                });
            }
        });
    }

}
