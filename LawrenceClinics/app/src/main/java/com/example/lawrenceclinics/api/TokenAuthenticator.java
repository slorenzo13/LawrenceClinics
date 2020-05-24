package com.example.lawrenceclinics.api;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenAuthenticator implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Sesion sesion = Sesion.getInstance();
        Request request = chain.request();
        if(sesion.isIniciada()) {
            request = request.newBuilder()
                    .header("Authorization-Token",sesion.getToken())
                    .method(request.method(), request.body())
                    .build();
        }

        return chain.proceed(request);
    }
}
