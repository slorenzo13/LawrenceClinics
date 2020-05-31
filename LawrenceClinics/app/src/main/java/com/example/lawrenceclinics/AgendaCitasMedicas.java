package com.example.lawrenceclinics;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class AgendaCitasMedicas extends AppCompatActivity {

    private ConstraintLayout constraint1, constraint2, constraint3, constraint4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_citas_medicas);

        constraint1 = findViewById(R.id.ConstraintCuerpo_1);
        constraint2 = findViewById(R.id.ConstraintCuerpo_2);
        constraint3 = findViewById(R.id.ConstraintCuerpo_3);
        constraint4 = findViewById(R.id.ConstraintCuerpo_4);

        Toast.makeText(this, "Bienvenido, gracias por entrar", Toast.LENGTH_SHORT).show();

        constraint1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgendaCitasMedicas.this, AgregarCita.class);
                startActivity(intent);
            }
        });

        constraint2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgendaCitasMedicas.this, AnularCita.class);
                startActivity(i);
            }
        });

        constraint3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //////////
            }
        });

        constraint4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgendaCitasMedicas.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menuAyuda) {
       getMenuInflater().inflate(R.menu.menu, menuAyuda);
       return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Toast.makeText(this, "Esta aplicación ha sido desarrollada por Sergio Lorenzo para Citas médicas", Toast.LENGTH_SHORT).show();

        switch (item.getItemId()){
            case R.id.opcion_ayuda:
                MostrarMensajeAyudaApp();
                break;

            case R.id.opcion_contacto:
                MandarMail();
                break;

            case R.id.opcion_desconexion:
                desconexion();
                break;

            /*case R.id.opcion_chat:
                mostrarChat();
                break;*/
        }

        return super.onOptionsItemSelected(item);
    }

    public void MostrarMensajeAyudaApp(){
        AlertDialog.Builder info = new AlertDialog.Builder(AgendaCitasMedicas.this);
        info.setTitle("AYUDA");
        info.setMessage("Gracias a esta aplicación, usted podrá solicitar una cita médica en cualquiera de nuestras especialidades disponibles sin moverse de casa.");
        info.setCancelable(false);
        info.setIcon(R.drawable.logo_clinica_3);
        info.setPositiveButton("Entiendo", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        info.show();
    }

    public void MandarMail() {
        Intent intent = new Intent(AgendaCitasMedicas.this, ContactoMailActivity.class);
        startActivity(intent);
    }

    public void desconexion(){
        android.app.AlertDialog.Builder logout = new android.app.AlertDialog.Builder(AgendaCitasMedicas.this);
        logout.setIcon(R.drawable.logo_clinica_3);
        logout.setTitle("Desconexión");
        logout.setMessage("¿Desea cerrar la sesión?");
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
    @Override
    public void onBackPressed() {
        desconexion();
    }
}
