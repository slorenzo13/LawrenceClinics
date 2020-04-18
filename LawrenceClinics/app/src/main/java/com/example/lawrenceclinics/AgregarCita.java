package com.example.lawrenceclinics;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.lawrenceclinics.api.ApiClinica;
import com.example.lawrenceclinics.api.ServicioRetrofit;
import com.example.lawrenceclinics.api.respuestas.DatosEspecialidad;
import com.example.lawrenceclinics.api.respuestas.Especialidades;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgregarCita extends AppCompatActivity implements AreaMedicaAdapter.Listener {

    private RecyclerView especialidades;
    private ApiClinica apiClinica;
    private Group contenidoGp;
    private ProgressBar cargando;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_cita);

        especialidades = findViewById(R.id.especialidadesRv);
        especialidades.setLayoutManager(new LinearLayoutManager(this));

        contenidoGp = findViewById(R.id.contenidoGp);
        cargando = findViewById(R.id.cargandoPb);

        apiClinica = ServicioRetrofit.generarApi();

        apiClinica.especialidades().enqueue(new Callback<Especialidades>() {
            @Override
            public void onResponse(Call<Especialidades> call, Response<Especialidades> response) {
                if(response.isSuccessful()) {
                    Especialidades respuesta = response.body();
                    AreaMedicaAdapter adapter = new AreaMedicaAdapter(respuesta.getDatos());
                    adapter.setListener(AgregarCita.this);
                    especialidades.setAdapter(adapter);

                    cargando.setVisibility(View.GONE);
                    contenidoGp.setVisibility(View.VISIBLE);
                }
                else {
                    Toast.makeText(AgregarCita.this, "Ha habido un error a la hora de sacar la lista ", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Especialidades> call, Throwable t) {

            }
        });

    }

    @Override
    public void onAreaMedicaClick(DatosEspecialidad especialidad) {
        Intent intentoAreaMedica = new Intent(AgregarCita.this, HorariosDisponiblesActivity.class);
        intentoAreaMedica.putExtra(HorariosDisponiblesActivity.ESPECIALIDAD_ID,especialidad.getIdArea());
        intentoAreaMedica.putExtra(HorariosDisponiblesActivity.ESPECIALIDAD_NOMBRE,especialidad.getNombreArea());
        startActivity(intentoAreaMedica);
    }
}
