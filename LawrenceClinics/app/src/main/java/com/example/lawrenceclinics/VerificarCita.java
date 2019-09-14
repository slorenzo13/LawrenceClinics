package com.example.lawrenceclinics;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class VerificarCita extends AppCompatActivity {

    private Button verificarCita;
    private TextView especialidad, doctor, fecha, horario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar_cita);

        especialidad = findViewById(R.id.resultadoEspecialidad_agendado);
        doctor = findViewById(R.id.resultadoDoctor_agendado);
        fecha = findViewById(R.id.resultadoFecha_agendado);
        horario = findViewById(R.id.resultadoHorario_agendado);

        verificarCita = findViewById(R.id.BotonAgendarCita);

        verificarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VerificarCita();
                Toast.makeText(VerificarCita.this, "Cita agregada correctamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(VerificarCita.this, AgendarCita.class);
                startActivity(intent);
            }
        });
    }

    public void VerificarCita() {
        ProgressDialog barraCargaRegistro = new ProgressDialog(this);
        barraCargaRegistro.setTitle("Verificar");
        barraCargaRegistro.setMessage("Verificando la disponibilidad...");
        barraCargaRegistro.setCancelable(true);
        barraCargaRegistro.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);

                } catch (Exception ex) {
                    Toast.makeText(VerificarCita.this, "ERROR", Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }
            }
        });
    }

}
