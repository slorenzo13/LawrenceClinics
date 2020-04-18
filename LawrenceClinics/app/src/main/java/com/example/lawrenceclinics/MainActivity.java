package com.example.lawrenceclinics;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lawrenceclinics.bd.AutenticacionBD;
import com.example.lawrenceclinics.bd.ManejoBD;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText NumCedula, Contrasena;
    private TextView register;
    private AutenticacionBD autenticacionBD;
    private Button ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NumCedula = (TextInputEditText)findViewById(R.id.TarjetaSanitariaLogin);
        Contrasena = (TextInputEditText)findViewById(R.id.passwordLogin);
        register = findViewById(R.id.textViewRegistrarme);
        ingresar = findViewById(R.id.BotonIngresar);

        autenticacionBD = new AutenticacionBD(ManejoBD.getInstance(this));

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, registro_perfil.class);
                startActivity(intent);
            }
        });

        ingresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargaIngreso();
                /*ProgressDialog barraCargaRegistro = new ProgressDialog(MainActivity.this);
                barraCargaRegistro.setTitle("Ingresar");
                barraCargaRegistro.setMessage("Verificando la cuenta...");
                barraCargaRegistro.setCancelable(true);
                barraCargaRegistro.show();

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            CargaIngreso();

                        } catch (Exception ex) {
                            Toast.makeText(MainActivity.this, "Lo siento, no existe esa cuenta", Toast.LENGTH_SHORT).show();
                            ex.printStackTrace();
                        }
                    }
                });*/
            }

            public void CargaIngreso() {
                ProgressDialog barraCargaRegistro = new ProgressDialog(MainActivity.this);
                barraCargaRegistro.setTitle("Ingresar");
                barraCargaRegistro.setMessage("Comprobando...");
                barraCargaRegistro.setCancelable(false);
                barraCargaRegistro.show();

                String numeroCedula = NumCedula.getText().toString();
                String pass = Contrasena.getText().toString();

                if (autenticacionBD.login(numeroCedula, pass)) {
                    barraCargaRegistro.cancel();
                    Toast.makeText(MainActivity.this, "Sesión iniciada", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(MainActivity.this, AgendaCitasMedicas.class);
                    startActivity(i);
                } else {
                    barraCargaRegistro.cancel();
                    Toast.makeText(MainActivity.this, "Usuario no existente", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    @Override
    public void onBackPressed() {
        SalirAplicacion();
    }

    public void SalirAplicacion() {
        android.app.AlertDialog.Builder logout = new android.app.AlertDialog.Builder(MainActivity.this);
        logout.setIcon(R.drawable.logo_clinica_3);
        logout.setTitle("Cerrar la aplicación");
        logout.setMessage("¿Desea salir de la aplicación?");
        logout.setCancelable(false);
        logout.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        logout.setNegativeButton("Sí", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        logout.show();
    }
}
