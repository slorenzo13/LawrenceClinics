
package com.example.lawrenceclinics.api.respuestas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UltimaCita {

    @SerializedName("datos")
    @Expose
    private DatosUltimaCita datos;
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("msg_error")
    @Expose
    private String errorMsg;

    public DatosUltimaCita getDatos() {
        return datos;
    }

    public void setDatos(DatosUltimaCita datos) {
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
