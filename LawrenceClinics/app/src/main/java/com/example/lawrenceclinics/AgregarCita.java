package com.example.lawrenceclinics;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class AgregarCita extends AppCompatActivity {

    private ConstraintLayout consLayoutTraumatologia, consLayoutCardiologia, consLayoutPediatria, consLayoutGinecologia, consLayoutObstetricia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cita);

        consLayoutTraumatologia = findViewById(R.id.seccionTraumatologia);
        consLayoutCardiologia = findViewById(R.id.seccionCardiologia);
        consLayoutPediatria = findViewById(R.id.seccionPediatria);
        consLayoutGinecologia = findViewById(R.id.seccionGinecologia);
        consLayoutObstetricia = findViewById(R.id.seccionObstetricia);

        consLayoutTraumatologia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgregarCita.this, HorariosDisponibles.class);
                startActivity(intent);
            }
        });

        consLayoutCardiologia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AgregarCita.this, HorariosDisponibles.class);
                startActivity(i);
            }
        });

        consLayoutPediatria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(AgregarCita.this, HorariosDisponibles.class);
                startActivity(inte);
            }
        });

        consLayoutGinecologia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inten = new Intent(AgregarCita.this, HorariosDisponibles.class);
                startActivity(inten);
            }
        });

        consLayoutObstetricia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intento = new Intent(AgregarCita.this, HorariosDisponibles.class);
                startActivity(intento);
            }
        });
    }
}
