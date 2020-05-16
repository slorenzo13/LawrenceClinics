package com.example.lawrenceclinics.api;

public class Sesion {

    private String token;
    private String id;

    private static Sesion instancia;

    public static Sesion getInstance() {
        if(instancia == null)
            instancia = new Sesion();
        return instancia;
    }

    public String getToken() {
        return token;
    }

    public String getId() {
        return id;
    }

    public void setDatos(String token, String id) {
        this.token = token;
        this.id = id;
    }

    public boolean isIniciada() {
        return token != null && id != null;
    }
}
