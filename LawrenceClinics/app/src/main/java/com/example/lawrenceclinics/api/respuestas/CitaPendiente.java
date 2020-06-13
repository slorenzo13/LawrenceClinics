
package com.example.lawrenceclinics.api.respuestas;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CitaPendiente {

    @SerializedName("nombre_doctor")
    @Expose
    private String nombreDoctor;

    public String getNombreDoctor() {
        return nombreDoctor;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }

    public String getIdCita() {
        return idCita;
    }

    public void setIdCita(String idCita) {
        this.idCita = idCita;
    }

    public String getAreaDoctor() {
        return areaDoctor;
    }

    public void setAreaDoctor(String areaDoctor) {
        this.areaDoctor = areaDoctor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @SerializedName("id_cita")
    @Expose
    private String idCita;

    @SerializedName("nombre_area")
    @Expose
    private String areaDoctor;

    @SerializedName("fecha")
    @Expose
    private String fecha;

    @SerializedName("hora")
    @Expose
    private String hora;

    @NonNull
    @Override
    public String toString() {
        return nombreDoctor;
    }
}
