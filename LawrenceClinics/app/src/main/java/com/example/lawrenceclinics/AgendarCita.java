package com.example.lawrenceclinics;

import android.app.ProgressDialog;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AgendarCita extends AppCompatActivity {

    private EditText codAutomatico;
    private TextView espec, doct, fech, hor;
    private Button botonAgendarCita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendar_cita);

        espec = findViewById(R.id.resultadoEspecialidad_agendado);
        doct = findViewById(R.id.resultadoDoctor_agendado);
        fech = findViewById(R.id.resultadoFecha_agendado);
        hor = findViewById(R.id.resultadoHorario_agendado);

        botonAgendarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgendarCitaMedica();
                Toast.makeText(AgendarCita.this, "Cita agendada correctamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AgendarCita.this, AgendaCitasMedicas.class);
                startActivity(intent);
            }
        });
    }

    public void AgendarCitaMedica() {
        ProgressDialog barraCargaRegistro = new ProgressDialog(this);
        barraCargaRegistro.setTitle("Agendar");
        barraCargaRegistro.setMessage("Cargando...");
        barraCargaRegistro.setCancelable(true);
        barraCargaRegistro.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);

                } catch (Exception ex) {
                    Toast.makeText(AgendarCita.this, "ERROR", Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }
            }
        });
    }
}