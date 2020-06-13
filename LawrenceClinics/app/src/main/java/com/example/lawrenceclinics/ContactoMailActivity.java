package com.example.lawrenceclinics;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.lawrenceclinics.api.ApiClinica;
import com.example.lawrenceclinics.api.ServicioRetrofit;
import com.example.lawrenceclinics.api.respuestas.ContactoMail;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactoMailActivity extends AppCompatActivity {

    private TextInputEditText CorreoCliente, AsuntoContacto, DescripcionMensajeContacto;
    private Button BotonEnviarMail;
    private ApiClinica apiClinica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_mail);

        CorreoCliente = (TextInputEditText)findViewById(R.id.campoEmailContacto);
        AsuntoContacto = (TextInputEditText)findViewById(R.id.campoAsuntoContacto);
        DescripcionMensajeContacto = (TextInputEditText)findViewById(R.id.campoDescripcionContacto);

        BotonEnviarMail = findViewById(R.id.botonEnviarMail);

        apiClinica = ServicioRetrofit.generarApi();

        BotonEnviarMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String correoCliente = CorreoCliente.getText().toString().trim(); //.trim elimina los espacios en blanco antes y después del texto
                String AsuntoMail = AsuntoContacto.getText().toString().trim();
                String DescripcionMensaje = DescripcionMensajeContacto.getText().toString().trim();

                if(DescripcionMensaje.length() > 350){
                    Toast.makeText(ContactoMailActivity.this, "La descripción de su mensaje debe ser más corta", Toast.LENGTH_SHORT).show();
                } else{
                    EnviarMail(correoCliente, AsuntoMail, DescripcionMensaje);
                }
            }
        });
    }

    private void EnviarMail(String correoCliente, String asuntoMail, String descripcionMensaje) {
        apiClinica.contactoMail(correoCliente, asuntoMail, descripcionMensaje).enqueue(new Callback<ContactoMail>() {
            @Override
            public void onResponse(Call<ContactoMail> call, Response<ContactoMail> response) {
                ContactoMail respuesta = response.body();
                if(respuesta.isError()) {
                    Toast.makeText(ContactoMailActivity.this, respuesta.getErrorMsg(), Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(ContactoMailActivity.this, "Enviado con éxito", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ContactoMail> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
