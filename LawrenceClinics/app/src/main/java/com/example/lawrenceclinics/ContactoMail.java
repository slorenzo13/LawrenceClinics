package com.example.lawrenceclinics;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ContactoMail extends AppCompatActivity {

    private TextInputEditText CorreoCliente, AsuntoContacto, DescripcionMensajeContacto;
    private ImageButton botonEnviarMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto_mail);

        CorreoCliente = (TextInputEditText)findViewById(R.id.campoEmailContacto);
        AsuntoContacto = (TextInputEditText)findViewById(R.id.campoAsuntoContacto);
        DescripcionMensajeContacto = (TextInputEditText)findViewById(R.id.campoDescripcionContacto);

        botonEnviarMail = findViewById(R.id.BotonEnviarMail_Contacto);

        botonEnviarMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String correoCliente = CorreoCliente.getText().toString().trim(); //.trim elimina los espacios en blanco antes y despues del texto
                String AsuntoMail = AsuntoContacto.getText().toString().trim();
                String DescripcionMensaje = DescripcionMensajeContacto.getText().toString().trim();

                if(DescripcionMensaje.length() > 350){
                    Toast.makeText(ContactoMail.this, "La descripción de su mensaje debe ser más corta", Toast.LENGTH_SHORT).show();
                } else{
                    EnviarMail(correoCliente, AsuntoMail, DescripcionMensaje);
                }
            }
        });
    }

    private void EnviarMail(String correoCliente, String asuntoMail, String descripcionMensaje) {
        Intent intentoEnviarMail = new Intent(Intent.ACTION_SEND);
        intentoEnviarMail.setData(Uri.parse("mailto"));
        intentoEnviarMail.setType("text/plain");

        intentoEnviarMail.putExtra(Intent.EXTRA_EMAIL, new String[]{correoCliente}); //con el array puedo realizar el envio a varios destinatarios al mismo tiempo
        intentoEnviarMail.putExtra(Intent.EXTRA_SUBJECT, asuntoMail);
        intentoEnviarMail.putExtra(Intent.EXTRA_TEXT, descripcionMensaje);

        try {

            startActivity(Intent.createChooser(intentoEnviarMail, "Seleccione un cliente"));

        } catch (Exception e){
            Toast.makeText(this, "No se ha podido enviar el mensaje", Toast.LENGTH_SHORT).show();
        }
    }
}
