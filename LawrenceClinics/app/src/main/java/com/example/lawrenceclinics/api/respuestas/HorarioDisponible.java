
package com.example.lawrenceclinics.api.respuestas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class HorarioDisponible {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("hora")
    @Expose
    private String hora;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String horarioFormateado() {
        Date fechaInicio = null;
        try {
            fechaInicio = dateFormat.parse(getHora());
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fechaInicio);

            int minutosInicio = calendar.get(Calendar.MINUTE);

            calendar.set(Calendar.MINUTE, minutosInicio + 30);

            Date fechaFin = calendar.getTime();
            return dateFormat.format(fechaInicio) + " - " + dateFormat.format(fechaFin);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;


    }
}
