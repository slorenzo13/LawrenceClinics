package com.example.lawrenceclinics.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioRetrofit {

    private static Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://lawrenceclinics.000webhostapp.com/")
            .build();

    public static ApiClinica generarApi() {
        return retrofit.create(ApiClinica.class);
    }

    public static ApiClinica anularCita() {
        return retrofit.create(ApiClinica.class);
    }

}
