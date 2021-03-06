package com.example.lawrenceclinics.api.respuestas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HorariosDisponibles {
    @SerializedName("datos")
    @Expose
    private List<HorarioDisponible> datos;
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("msg_error")
    @Expose
    private String errorMsg;

    public  List<HorarioDisponible> getDatos() {
        return datos;
    }

    public void setDatos( List<HorarioDisponible> datos) {
        this.datos = datos;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
