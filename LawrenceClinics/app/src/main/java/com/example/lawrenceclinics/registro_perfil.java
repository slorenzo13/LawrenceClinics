package com.example.lawrenceclinics;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registro_perfil extends AppCompatActivity {

    private EditText numCedula, nomCompleto, mailRegistro, telefonoRegistro;
    private Button registrarPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_perfil);

        numCedula = findViewById(R.id.numeroCedula);
        nomCompleto = findViewById(R.id.nombre_registro);
        mailRegistro = findViewById(R.id.email_registro);
        telefonoRegistro = findViewById(R.id.telefono_registro);

        registrarPerfil = findViewById(R.id.BotonRegistrar);

        registrarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargaRegistro();
                Toast.makeText(registro_perfil.this, "Perfil registrado correctamente", Toast.LENGTH_SHORT).show();
                Intent IntentoRegistro = new Intent(registro_perfil.this, MainActivity.class);
                startActivity(IntentoRegistro);
            }
        });
    }

    public void CargaRegistro(){
        ProgressDialog barraCargaRegistro = new ProgressDialog(this);
        barraCargaRegistro.setTitle("Registrar");
        barraCargaRegistro.setMessage("Comprobando...");
        barraCargaRegistro.setCancelable(true);
        barraCargaRegistro.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);

                } catch (Exception ex){
                    Toast.makeText(registro_perfil.this, "ERROR", Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }
            }
        });
    }
}
