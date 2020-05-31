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

import java.util.regex.Pattern;

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

        numCedula = findViewById(R.id.campoNumCedula_Registro);
        nomCompleto = findViewById(R.id.campoNombreCompleto_Registro);
        mailRegistro = findViewById(R.id.campoMail_Registro);
        passwordRegistro = findViewById(R.id.campoPassword_Registro);

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

        if(!VerificarSeguridadPassword()){
            barraCargaRegistro.dismiss();
            Toast.makeText(this, "Introduzca una contraseña más segura", Toast.LENGTH_SHORT).show();
            return;
        }

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
                    Toast.makeText(registro_perfil.this, respuesta.getErrorMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Registrar> call, Throwable t) {

            }
        });

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
