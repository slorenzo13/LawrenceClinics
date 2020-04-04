package com.example.lawrenceclinics;

public class Cita {
    private String tEspecialidad, tDoctor, tFecha, tHorario;

    public Cita(String tEspecialidad, String tDoctor, String tFecha, String tHorario) {
        this.tEspecialidad = tEspecialidad;
        this.tDoctor = tDoctor;
        this.tFecha = tFecha;
        this.tHorario = tHorario;
    }

    public String gettEspecialidad() {
        return tEspecialidad;
    }

    public String gettDoctor() {
        return tDoctor;
    }

    public String gettFecha() {
        return tFecha;
    }

    public String gettHorario() {
        return tHorario;
    }

    @Override
    public String toString() {
        return "Cita{" +
                "tEspecialidad='" + tEspecialidad + '\'' +
                ", tDoctor='" + tDoctor + '\'' +
                ", tFecha='" + tFecha + '\'' +
                ", tHorario='" + tHorario + '\'' +
                '}';
    }
}
