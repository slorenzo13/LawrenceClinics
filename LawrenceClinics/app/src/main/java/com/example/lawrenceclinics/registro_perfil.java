package com.example.lawrenceclinics;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lawrenceclinics.api.ApiClinica;
import com.example.lawrenceclinics.api.ServicioRetrofit;
import com.example.lawrenceclinics.api.respuestas.Registrar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class registro_perfil extends AppCompatActivity {

    private EditText numCedula, nomCompleto, mailRegistro, passwordRegistro;
    private Button registrarPerfil;

    boolean cancel = false;
    String focusView = null;

    private ApiClinica apiClinica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_perfil);

        numCedula = findViewById(R.id.campoNombreDoctor);
        nomCompleto = findViewById(R.id.emailRecuperacionPassword);
        mailRegistro = findViewById(R.id.campoMail_RegistroDoctor);
        passwordRegistro = findViewById(R.id.campoPassword_RegistroDoctor);

        registrarPerfil = findViewById(R.id.BotonRegistrar);

        apiClinica = ServicioRetrofit.generarApi();

        registrarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargaRegistro();
            }
        });
    }

    public void CargaRegistro(){
        ProgressDialog barraCargaRegistro = new ProgressDialog(this);
        barraCargaRegistro.setTitle("Registrar");
        barraCargaRegistro.setMessage("Comprobando...");
        barraCargaRegistro.setCancelable(true);
        barraCargaRegistro.show();

        String numeroCedula = numCedula.getText().toString();
        String mail = mailRegistro.getText().toString();
        String password = passwordRegistro.getText().toString();
        String nombre = nomCompleto.getText().toString();


        if(VerificarSeguridadPassword()) {
            apiClinica.registrar(numeroCedula, password, nombre, mail).enqueue(new Callback<Registrar>() {
                @Override
                public void onResponse(Call<Registrar> call, Response<Registrar> response) {
                    Registrar respuesta = response.body();
                    if(!respuesta.isError()) {
                        barraCargaRegistro.dismiss();
                        Toast.makeText(registro_perfil.this, "Registro completado", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    else {
                        barraCargaRegistro.dismiss();
                        Toast.makeText(registro_perfil.this, respuesta.getErrorMsg(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Registrar> call, Throwable t) {

                }
            });
        }
        else {
            Toast.makeText(this, "Introduzca una contraseña más segura", Toast.LENGTH_SHORT).show();
        }

    }

    public boolean VerificarSeguridadPassword(){
        String password = passwordRegistro.getText().toString().trim();

        //No ha insertado caracteres, mayusculas
        if (!password.matches("((?=.*[0-9])(?=.*[*@#$%^&+=])(?=.*[A-Z])(?=\\S+$).{8,})")){
            focusView = password;
            cancel = true;
            return false;
        }
        return true;
    }

}
