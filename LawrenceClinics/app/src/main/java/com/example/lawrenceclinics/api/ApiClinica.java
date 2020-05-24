package com.example.lawrenceclinics.api;

import com.example.lawrenceclinics.api.respuestas.AgregarCita;
import com.example.lawrenceclinics.api.respuestas.ContactoMail;
import com.example.lawrenceclinics.api.respuestas.DoctoresEspecialidad;
import com.example.lawrenceclinics.api.respuestas.Especialidades;
import com.example.lawrenceclinics.api.respuestas.HorariosDisponibles;
import com.example.lawrenceclinics.api.respuestas.Login;
import com.example.lawrenceclinics.api.respuestas.Registrar;
import com.example.lawrenceclinics.api.respuestas.UltimaCita;
import com.example.lawrenceclinics.api.respuestas.AnularCita;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiClinica {

    @GET("ultima_cita.php")
    Call<UltimaCita> obtenerUltimaCita(@Query("numeroCedula") String numeroCedula);


    @FormUrlEncoded
    @POST("agregar_cita.php")
    Call<AgregarCita> insertarCita(@Field("idDoctor") String idDoctor,
                                   @Field("fecha") String fecha,
                                   @Field("hora") String idHora);

    @FormUrlEncoded
    @POST("anular_cita.php")
    Call<AnularCita> anularCita(@Field("idCita") String idCita,
                                @Field("motivoAnulacion") String motivo);

    @FormUrlEncoded
    @POST("registrar_usuario.php")
    Call<Registrar> registrar(@Field("numeroCedula") String numCedula, @Field("pass") String password,
                              @Field("nombre") String nombre, @Field("email")String email);

    @FormUrlEncoded
    @POST("login.php")
    Call<Login> login(@Field("numeroCedula") String numCedula, @Field("pass") String pass);

    @GET("especialidades.php")
    Call<Especialidades> especialidades();

    @GET("doctores_especialidad.php")
    Call<DoctoresEspecialidad> doctoresEspecialidad(@Query("idEspecialidad") String idEspecialidad);

    @GET("horas_disponibles.php")
    Call<HorariosDisponibles> horariosDisponibles(@Query("idDoctor") String idDoctor, @Query("fecha") String fecha);

    @GET("ultima_cita.php")
    Call<UltimaCita> ultimaCita(@Query("numeroCedula") String numeroCedula);

    @FormUrlEncoded
    @POST("contacto_mail.php")
    Call<ContactoMail> contactoMail(@Field("destinatario") String destinatario,
                                    @Field("asunto") String asunto,
                                    @Field("descripcion") String descripcion);
}
