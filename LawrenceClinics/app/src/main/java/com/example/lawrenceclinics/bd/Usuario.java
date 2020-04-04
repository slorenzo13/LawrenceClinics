package com.example.lawrenceclinics.bd;

public class Usuario {

    private long id = -1;
    private String numeroCedula;
    private String password;
    private String mail;
    private String nombre;

    // Constructor para usuarios ya creados
    public Usuario(long id, String numeroCedula,
                   String password, String mail, String nombre) {
        this.id = id;
        this.numeroCedula = numeroCedula;
        this.password = password;
        this.mail = mail;
        this.nombre = nombre;
    }

    // Constructor para usuarios que leemos de BD
    public Usuario(String numeroCedula, String password, String mail,
                   String nombre) {
        this.numeroCedula = numeroCedula;
        this.password = password;
        this.mail = mail;
        this.nombre = nombre;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNumeroCedula() {
        return numeroCedula;
    }

    public void setNumeroCedula(String numeroCedula) {
        this.numeroCedula = numeroCedula;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
