package com.example.lawrenceclinics;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
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

            }
        });

        constraint3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        constraint4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
