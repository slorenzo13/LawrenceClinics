package com.example.lawrenceclinics;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lawrenceclinics.api.ApiClinica;
public class registro_perfil extends AppCompatActivity {

    private EditText numCedula, nomCompleto, mailRegistro, passwordRegistro;
    private Button registrarPerfil;

    private ApiClinica apiClinica_insertar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_perfil);

        numCedula = findViewById(R.id.campoNumCedula_Registro);
        nomCompleto = findViewById(R.id.campoNombreCompleto_Registro);
        mailRegistro = findViewById(R.id.campoMail_Registro);
        passwordRegistro = findViewById(R.id.campoPassword_Registro);

        registrarPerfil = findViewById(R.id.BotonRegistrar);

        registrarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CargaRegistro();
            }
        });
    }

    public void CargaRegistro(){
        /*ProgressDialog barraCargaRegistro = new ProgressDialog(this);
        barraCargaRegistro.setTitle("Registrar");
        barraCargaRegistro.setMessage("Comprobando...");
        barraCargaRegistro.setCancelable(true);
        barraCargaRegistro.show();*/

        String numeroCedula = numCedula.getText().toString();
        String mail = mailRegistro.getText().toString();
        String password = passwordRegistro.getText().toString();
        String nombre = nomCompleto.getText().toString();

    /*    if(!numeroCedula.trim().isEmpty() && !mail.trim().isEmpty() && !password.trim().isEmpty() && !nombre.trim().isEmpty()) {
            Usuario usuario = new Usuario(numeroCedula, password, mail, nombre);

            Usuario usuarioConId = autenticacionBD.registrar(usuario);
            //barraCargaRegistro.cancel();
            if (usuarioConId == null) {
                //Error
                Toast.makeText(this, "Error, cedula ya existente", Toast.LENGTH_SHORT).show();
            } else {
                //OK
                Toast.makeText(registro_perfil.this, "Perfil registrado correctamente", Toast.LENGTH_SHORT).show();
                Intent IntentoRegistro = new Intent(registro_perfil.this, MainActivity.class);
                startActivity(IntentoRegistro);
            }
        }
        else {
            Toast.makeText(this, "Alguno(s) de los datos del formulario no han sido rellenados", Toast.LENGTH_SHORT).show();
        }*/
    }

    /*private void crearRegistroUsuario(final int numCed, final String mailUs, final String passUs, final String nomUs){

        Call<InsertarCita> peticionInsertRegistro = apiClinica_insertar.insertarUsuario(numCed, mailUs, passUs, nomUs);

        peticionInsertRegistro.enqueue(new Callback<InsertarCita>() {
            @Override
            public void onResponse(@NonNull Call<InsertarCita> call, @NonNull Response<InsertarCita> response) {
                if (response.isSuccessful() && response.body() != null){
                    Boolean exito = response.body().getSuccess();
                    if (exito) {
                        Toast.makeText(registro_perfil.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(registro_perfil.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<InsertarCita> call, @NonNull Throwable t) {
                Toast.makeText(registro_perfil.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }*/

}
