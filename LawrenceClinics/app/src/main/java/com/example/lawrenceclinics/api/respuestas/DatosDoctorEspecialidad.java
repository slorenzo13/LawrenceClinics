
package com.example.lawrenceclinics.api.respuestas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatosDoctorEspecialidad {

    @SerializedName("nombre_doctor")
    @Expose
    private String nombreDoctor;

    @SerializedName("id_doctor")
    @Expose
    private String idDoctor;

    @SerializedName("area_doctor")
    @Expose
    private String idArea;

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
}
