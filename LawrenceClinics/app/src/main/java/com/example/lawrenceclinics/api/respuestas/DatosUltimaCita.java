
package com.example.lawrenceclinics.api.respuestas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatosUltimaCita {

    @SerializedName("nombre_area")
    @Expose
    private String nombreArea;

    @SerializedName("nombre_doctor")
    @Expose
    private String nombreDoctor;

    @SerializedName("id_cita")
    @Expose
    private String idCita;

    @SerializedName("fecha")
    @Expose
    private String fecha;

    @SerializedName("id_area")
    @Expose
    private String idArea;

    @SerializedName("id_doctor")
    @Expose
    private String idDoctor;

    @SerializedName("hora")
    @Expose
    private String hora;

    public String getNombreDoctor() {
        return nombreDoctor;
    }

    public void setNombreDoctor(String nombreDoctor) {
        this.nombreDoctor = nombreDoctor;
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getIdArea() {
        return idArea;
    }

    public void setIdArea(String idArea) {
        this.idArea = idArea;
    }

    public String getNombreArea() {
        return nombreArea;
    }

    public String getIdCita() {
        return idCita;
    }

    public String getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }
}
