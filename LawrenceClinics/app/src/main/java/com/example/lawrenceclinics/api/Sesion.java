package com.example.lawrenceclinics.api;

public class Sesion {

    private String token;

    private static Sesion instancia;

    public static Sesion getInstance() {
        if(instancia == null)
            instancia = new Sesion();
        return instancia;
    }

    public String getToken() {
        return token;
    }

    public void setDatos(String token) {
        this.token = token;
    }

    public boolean isIniciada() {
        return token != null;
    }
}
