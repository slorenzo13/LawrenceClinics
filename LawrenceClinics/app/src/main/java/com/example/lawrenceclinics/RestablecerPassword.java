package com.example.lawrenceclinics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class RestablecerPassword extends AppCompatActivity {

    private EditText emailRestablecerPasswd;
    private Button restablecerPasswd;

    private String email = "";

    private FirebaseAuth auth;

    private ProgressDialog cargaProgreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restablecer_password);

        auth = FirebaseAuth.getInstance();
        cargaProgreso = new ProgressDialog(this);

        emailRestablecerPasswd = findViewById(R.id.emailRecuperacionPassword);
        restablecerPasswd = findViewById(R.id.botonRestablecerPassword);

        emailRestablecerPasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        restablecerPasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailRestablecerPasswd.getText().toString();

                if(!email.isEmpty()){
                    cargaProgreso.setMessage("Espere por favor...");
                    cargaProgreso.setCanceledOnTouchOutside(false);
                    cargaProgreso.show();
                    resetearPassword();
                }

                else{
                    Toast.makeText(RestablecerPassword.this, "Por favor, introduzca su dirección de correo electrónico", Toast.LENGTH_SHORT).show();
                }
                cargaProgreso.dismiss();
            }
        });
    }

    private void resetearPassword() {
        auth.setLanguageCode("es"); //le asigno el idioma en el que quiero que le llegue el correo de restablecer contraseña
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(RestablecerPassword.this, "Se ha enviado un correo para restablecer la contraseña", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(RestablecerPassword.this, "No se ha podido enviar el correo para restablecer la contraseña", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}