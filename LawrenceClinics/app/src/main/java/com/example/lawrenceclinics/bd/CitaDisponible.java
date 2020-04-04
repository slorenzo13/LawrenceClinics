package com.example.lawrenceclinics.bd;

import androidx.annotation.NonNull;

import java.util.Date;

public class CitaDisponible {

    private Date horaInicio; //09:43
    private Date horaFin; //11:10

    public CitaDisponible(Date horaInicio, Date horaFin) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    @NonNull
    @Override
    public String toString() {
        return horaInicio.toString() + " - " + horaFin.toString();
    }
}
