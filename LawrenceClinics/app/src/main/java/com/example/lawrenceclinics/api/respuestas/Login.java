package com.example.lawrenceclinics.api.respuestas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Login {
    @SerializedName("datos")
    @Expose
    private DatosLogin datos;
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("msg_error")
    @Expose
    private String errorMsg;

    public DatosLogin getDatos() {
        return datos;
    }

    public void setDatos(DatosLogin datos) {
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
