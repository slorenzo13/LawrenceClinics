package com.example.lawrenceclinics.api.respuestas;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CitasPendientes {
    @SerializedName("datos")
    @Expose
    private List<CitaPendiente> datos;
    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("msg_error")
    @Expose
    private String errorMsg;
    public  List<CitaPendiente> getDatos() {
        return datos;
    }

    public void setDatos( List<CitaPendiente> datos) {
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
