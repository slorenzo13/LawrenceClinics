package com.example.lawrenceclinics;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ImageButton desconexion;
    private EditText NumCedula, Contrasena;
    private TextView register;
    private Button ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        desconexion = findViewById(R.id.BotonDesconectar);
        NumCedula = findViewById(R.id.numeroCedula);
        Contrasena = findViewById(R.id.ContrasenaCuenta);
        register = findViewById(R.id.textViewRegistrarme);
        ingresar = findViewById(R.id.BotonIngresar);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, registro_perfil.class);
                startActivity(intent);
            }
        });

        desconexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargaIngreso();
                Toast.makeText(MainActivity.this, "Perfil registrado correctamente", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, AgendaCitasMedicas.class);
                startActivity(i);
            }
        });
    }

    public void CargaIngreso() {
        ProgressDialog barraCargaRegistro = new ProgressDialog(this);
        barraCargaRegistro.setTitle("Ingresar");
        barraCargaRegistro.setMessage("Comprobando...");
        barraCargaRegistro.setCancelable(true);
        barraCargaRegistro.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);

                } catch (Exception ex) {
                    Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    ex.printStackTrace();
                }
            }
        });

    }

}
