package com.example.lawrenceclinics.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServicioRetrofit {



    private static Retrofit retrofit = new Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://lawrenceclinics.000webhostapp.com/")
            .client(createClient())
            .build();

    private static OkHttpClient createClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
          OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new TokenAuthenticator())
                  .addInterceptor(interceptor)
                .build();

        return client;
    }

    public static ApiClinica generarApi() {

        return retrofit.create(ApiClinica.class);
    }

    public static ApiClinica anularCita() {
        return retrofit.create(ApiClinica.class);
    }

}
