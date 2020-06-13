package com.example.lawrenceclinics;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lawrenceclinics.api.ApiClinica;
import com.example.lawrenceclinics.api.ServicioRetrofit;
import com.example.lawrenceclinics.api.Sesion;
import com.example.lawrenceclinics.api.respuestas.Login;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText NumCedula, Contrasena;
    private TextView register, olvidoPassword;
    private Button ingresar, ingresarInvitado;
    private ApiClinica apiClinica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NumCedula = (TextInputEditText)findViewById(R.id.TarjetaSanitariaLogin);
        Contrasena = (TextInputEditText)findViewById(R.id.passwordLogin);
        register = findViewById(R.id.textViewRegistrarme);
        olvidoPassword = findViewById(R.id.textViewOlvidoPassword);
        ingresar = findViewById(R.id.BotonIngresar);
        ingresarInvitado = findViewById(R.id.botonIngresarInvitado);

        apiClinica = ServicioRetrofit.generarApi();

        olvidoPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentoRestablecerPassword = new Intent(MainActivity.this, RestablecerPassword.class);
                startActivity(intentoRestablecerPassword);
            }
        });

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
            }

            public void CargaIngreso() {
                ProgressDialog barraCargaRegistro = new ProgressDialog(MainActivity.this);
                barraCargaRegistro.setTitle("Ingresar");
                barraCargaRegistro.setMessage("Comprobando...");
                barraCargaRegistro.setCancelable(false);
                barraCargaRegistro.show();

                String numeroCedula = NumCedula.getText().toString();
                String pass = Contrasena.getText().toString();

                apiClinica.login(numeroCedula, pass).enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        Login respuesta = response.body();
                        if(!respuesta.isError()) {
                            sesionIniciada(respuesta);
                        }
                        else {
                            Toast.makeText(MainActivity.this, respuesta.getErrorMsg(), Toast.LENGTH_SHORT).show();
                        }
                        barraCargaRegistro.dismiss();
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {

                    }
                });
            }

        });

        ingresarInvitado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentoEntrarInvitado = new Intent(MainActivity.this, AgendaCitasMedicas.class);
                startActivity(intentoEntrarInvitado);
            }
        });

    }

    private void sesionIniciada(Login respuesta) {
        Sesion.getInstance().setDatos(respuesta.getDatos().getToken());
        Toast.makeText(MainActivity.this, "Sesión iniciada", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(MainActivity.this, AgendaCitasMedicas.class);
        NumCedula.setText("");
        Contrasena.setText("");
        startActivity(i);
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
