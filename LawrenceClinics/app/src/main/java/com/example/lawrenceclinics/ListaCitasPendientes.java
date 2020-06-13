package com.example.lawrenceclinics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lawrenceclinics.api.ApiClinica;
import com.example.lawrenceclinics.api.ServicioRetrofit;
import com.example.lawrenceclinics.api.respuestas.AnularCita;
import com.example.lawrenceclinics.api.respuestas.AnularCitaPendiente;
import com.example.lawrenceclinics.api.respuestas.CitaPendiente;
import com.example.lawrenceclinics.api.respuestas.CitasPendientes;
import com.example.lawrenceclinics.api.respuestas.DatosDoctorEspecialidad;
import com.example.lawrenceclinics.api.respuestas.DoctoresEspecialidad;
import com.example.lawrenceclinics.api.respuestas.HorarioDisponible;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaCitasPendientes extends AppCompatActivity implements CitasPendientesAdapter.Listener{


    private CitasPendientesAdapter adapter;
    private ApiClinica apiClinica;

    private Group contenidoGpPendiente;
    private ProgressBar carga;
    private TextView listaVacia;

    private RecyclerView listaCitasPendientes;

    public String idCitaPendiente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_citas_pendientes);

        apiClinica = ServicioRetrofit.generarApi();

        listaCitasPendientes = findViewById(R.id.citasPendientesRv);
        listaVacia = findViewById(R.id.listaVaciaTv);

        listaCitasPendientes.setLayoutManager(new LinearLayoutManager(this));


        apiClinica.citasPendientes().enqueue(new Callback<CitasPendientes>() {
            @Override
            public void onResponse(Call<CitasPendientes> call, Response<CitasPendientes> response) {
                if(response.isSuccessful()) {
                    CitasPendientes datos = response.body();
                    if(!datos.isError()) {
                        List<CitaPendiente> listaDatos = datos.getDatos();
                        if(listaDatos.isEmpty()) {
                            listaCitasPendientes.setVisibility(View.INVISIBLE);
                            listaVacia.setVisibility(View.VISIBLE);
                        }
                        else {
                            listaCitasPendientes.setVisibility(View.VISIBLE);
                            listaVacia.setVisibility(View.GONE);
                        }
                        adapter = new CitasPendientesAdapter(listaDatos);
                        adapter.setListener(ListaCitasPendientes.this);
                        listaCitasPendientes.setAdapter(adapter);

                    }
                    else {
                        Toast.makeText(ListaCitasPendientes.this, datos.getErrorMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<CitasPendientes> call, Throwable t) {

            }
        });
    }


    @Override
    public void onCitasPendientesClick(CitaPendiente cp) {
        Call<AnularCita> peticionAnularPendiente = apiClinica.anularCita(cp.getIdCita(), "");

        peticionAnularPendiente.enqueue(new Callback<AnularCita>() {
            @Override
            public void onResponse(Call<AnularCita> call, Response<AnularCita> response) {
                AnularCita anCitaP = response.body();

                if (anCitaP .isError()) {
                    Toast.makeText(com.example.lawrenceclinics.ListaCitasPendientes.this, anCitaP.getErrorMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(com.example.lawrenceclinics.ListaCitasPendientes.this, "Cita pendiente anulada con éxito", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<AnularCita> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
