package com.example.lawrenceclinics.api.respuestas;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DatosLogin {
    @SerializedName("token")
    @Expose
    private String token;

    @SerializedName("id")
    @Expose
    private String id;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
