package com.example.lawrenceclinics.api;

import com.example.lawrenceclinics.api.respuestas.AgregarCita;
import com.example.lawrenceclinics.api.respuestas.AnularCitaPendiente;
import com.example.lawrenceclinics.api.respuestas.CitasPendientes;
import com.example.lawrenceclinics.api.respuestas.ContactoMail;
import com.example.lawrenceclinics.api.respuestas.DoctoresEspecialidad;
import com.example.lawrenceclinics.api.respuestas.Especialidades;
import com.example.lawrenceclinics.api.respuestas.HorariosDisponibles;
import com.example.lawrenceclinics.api.respuestas.Login;
import com.example.lawrenceclinics.api.respuestas.Registrar;
import com.example.lawrenceclinics.api.respuestas.RestablecerPasswd;
import com.example.lawrenceclinics.api.respuestas.UltimaCita;
import com.example.lawrenceclinics.api.respuestas.AnularCita;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiClinica {

    //Llamada al php de obtener la ultima cita a la hora de anularla
    @GET("ultima_cita.php")
    Call<UltimaCita> obtenerUltimaCita(@Query("numeroCedula") String numeroCedula);

    //Llamada al php de agregar una cita
    @FormUrlEncoded
    @POST("agregar_cita.php")
    Call<AgregarCita> insertarCita(@Field("idDoctor") String idDoctor,
                                   @Field("fecha") String fecha,
                                   @Field("hora") String idHora);

    //Llamada al php de anular una cita
    @FormUrlEncoded
    @POST("anular_cita.php")
    Call<AnularCita> anularCita(@Field("idCita") String idCita,
                                @Field("motivoAnulacion") String motivo);

    //Llamada al php de registrar un usuario
    @FormUrlEncoded
    @POST("registrar_usuario.php")
    Call<Registrar> registrar(@Field("numeroCedula") String numCedula, @Field("pass") String password,
                              @Field("nombre") String nombre, @Field("email")String email);

    //Llamada al php de iniciar sesión
    @FormUrlEncoded
    @POST("login.php")
    Call<Login> login(@Field("numeroCedula") String numCedula, @Field("pass") String pass);

    //Llamada al php para mostrar todas las especialidades o áreas médicas
    @GET("especialidades.php")
    Call<Especialidades> especialidades();

    //Llamada al php para mostrar todos las doctores
    @GET("doctores_especialidad.php")
    Call<DoctoresEspecialidad> doctoresEspecialidad(@Query("idEspecialidad") String idEspecialidad);

    //Llamada al php para mostrar todos los horarios disponibles en tiempo real a la hora de agregar una cita
    @GET("horas_disponibles.php")
    Call<HorariosDisponibles> horariosDisponibles(@Query("idDoctor") String idDoctor, @Query("fecha") String fecha);

    @GET("ultima_cita.php")
    Call<UltimaCita> ultimaCita(@Query("numeroCedula") String numeroCedula);

    @GET("citas_pendientes.php")
    Call<CitasPendientes> citasPendientes();

    //Llamada al php para poder mandar un mensaje de contacto por correo electrónico
    @FormUrlEncoded
    @POST("contacto_mail.php")
    Call<ContactoMail> contactoMail(@Field("destinatario") String destinatario,
                                    @Field("asunto") String asunto,
                                    @Field("descripcion") String descripcion);

    //Llamada al php para poder cambiar la contraseña
    @FormUrlEncoded
    @POST("restablecer_password.php")
    Call<RestablecerPasswd> restablecerPasswd(@Field("email") String mail);


}
