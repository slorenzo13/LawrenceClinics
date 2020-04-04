package com.example.lawrenceclinics.api;

import com.example.lawrenceclinics.api.respuestas.AgregarCita;
import com.example.lawrenceclinics.api.respuestas.DoctoresEspecialidad;
import com.example.lawrenceclinics.api.respuestas.Especialidades;
import com.example.lawrenceclinics.api.respuestas.HorariosDisponibles;
import com.example.lawrenceclinics.api.respuestas.UltimaCita;
import com.example.lawrenceclinics.api.respuestas.AnularCita;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiClinica {

    @GET("ultima_cita.php?token=usahjsada737837")
    Call<UltimaCita> obtenerUltimaCita(@Query("numeroCedula") String numeroCedula);


    @FormUrlEncoded
    @POST("agregar_cita.php?token=usahjsada737837")
    Call<AgregarCita> insertarCita(@Field("idDoctor") String idDoctor,
                                   @Field("fecha") String fecha,
                                   @Field("hora") String idHora);

    @FormUrlEncoded
    @POST("anular_cita.php?token=usahjsada737837")
    Call<AnularCita> anularCita(@Field("idCita") String idCita,
                                @Field("motivoAnulacion") String motivo);

    @GET("especialidades.php?token=usahjsada737837")
    Call<Especialidades> especialidades();

    @GET("doctores_especialidad.php?token=usahjsada737837")
    Call<DoctoresEspecialidad> doctoresEspecialidad(@Query("idEspecialidad") String idEspecialidad);

    @GET("horarios_disponibles.php?token=usahjsada737837")
    Call<HorariosDisponibles> horariosDisponibles(@Query("idDoctor") String idDoctor, @Query("fecha") String fecha);

    @GET("ultima_cita.php?token=usahjsada737837")
    Call<UltimaCita> ultimaCita(@Query("numeroCedula") String numeroCedula);
}
