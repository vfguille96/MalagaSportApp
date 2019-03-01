package com.bilalmoreno.malagasport.data.service.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InstallationsApiAdapter {
    public static final String BASE_URL = "https://datosabiertos.malaga.eu/recursos/";
    private static InstallationsApiService API_SERVICE;

    public static synchronized InstallationsApiService getInstance() {

        if (API_SERVICE == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(5, TimeUnit.SECONDS)
                    .build();

            Gson gson = new GsonBuilder()
                    .create();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();

            API_SERVICE = retrofit.create(InstallationsApiService.class);
        }
        return API_SERVICE;
    }
}
