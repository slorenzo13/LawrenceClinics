package com.example.lawrenceclinics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lawrenceclinics.api.ApiClinica;
import com.example.lawrenceclinics.api.ServicioRetrofit;
import com.example.lawrenceclinics.api.respuestas.UltimaCita;
import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModificarCita extends AppCompatActivity {

    private int DiaM, MesM, AnyoM;

    private EditText introducirNumCedula_Modificar;
    private TextInputEditText campoFechModificar, campoMotiModificar, campoEspec, campoDoct, campoHor;
    private ImageButton Lupa_BuscarCodigo_Modificar;
    private Button ModificarCita, EnviarMotivo_modificarCita;

    private ApiClinica apiClinica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_cita);

        apiClinica = ServicioRetrofit.generarApi();

        introducirNumCedula_Modificar = findViewById(R.id.codigo_ModificarCita);

        //campoEspec = findViewById(R.id.campoEspecialidad_Modificar);
        campoDoct = findViewById(R.id.campoDoctor_Modificar);
        campoHor = findViewById(R.id.campoHorario_Modificar);

        campoFechModificar = findViewById(R.id.campoFechaModificar);
        campoMotiModificar = findViewById(R.id.campoMotivoModificar);


        Lupa_BuscarCodigo_Modificar = findViewById(R.id.botonBusquedaCita_Modificar);

        ModificarCita = findViewById(R.id.boton_ModificarCita2);
        EnviarMotivo_modificarCita = findViewById(R.id.boton_EnviarMotivo_Modificar);

        EnviarMotivo_modificarCita.setEnabled(false);
/*
        Lupa_BuscarCodigo_Modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cedulaModificar = campoFechModificar.getText().toString();
                Call<UltimaCita> peticionM = apiClinica.obtenerUltimaCita(cedulaModificar);
                peticionM.enqueue(new Callback<UltimaCita>() {
                    @Override
                    public void onResponse(Call<UltimaCita> call, Response<UltimaCita> response) {
                        UltimaCita ultimaCitaM = response.body();
                        if(ultimaCitaM.isError()) {
                            Toast.makeText(ModificarCita.this, ultimaCitaM.getErrorMsg(), Toast.LENGTH_SHORT).show();
                        }
                        else {
                            String nombreDoctor_Modificar = ultimaCitaM.getDatos().getNombreDoctor();
                            String nombreArea_Modificar = ultimaCitaM.getDatos().getNombreArea();
                            String fecha_Modificar = ultimaCitaM.getDatos().getFecha();

                            SimpleDateFormat sdf_modificar = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            SimpleDateFormat diaMesAnno_m = new SimpleDateFormat("yyyy-MM-dd");
                            SimpleDateFormat hora = new SimpleDateFormat("HH:mm");
                            try {
                                Date date = sdf_modificar.parse(fecha_Modificar);

                                Calendar calendar = Calendar.getInstance();
                                calendar.setTime(date);

                                campoFechModificar.setText(diaMesAnno_m.format(date));
                                campoHor.setText(hora.format(date));
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }

                            campoDoct.setText(nombreDoctor_Modificar);
                            campoEspec.setText(nombreArea_Modificar);

                        }
                    }

                    @Override
                    public void onFailure(Call<UltimaCita> call, Throwable t) {

                    }
                });
            }
        });

        ModificarCita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (campoEspec.getText().toString().length() == 0 && campoDoct.getText().toString().length() == 0 &&
                        campoFechModificar.getText().toString().length() == 0 && campoHor.getText().toString().length() == 0){
                    Toast.makeText(ModificarCita.this, "No puede modificarse una cita vacía", Toast.LENGTH_SHORT).show();
                }

                else if(campoMotiModificar.getText().toString().length() == 0){
                    Toast.makeText(ModificarCita.this, "Debe insertar un motivo para cancelar su cita", Toast.LENGTH_SHORT).show();
                }
            }
        });
*/
        //Spinner especialidad
    }

}
