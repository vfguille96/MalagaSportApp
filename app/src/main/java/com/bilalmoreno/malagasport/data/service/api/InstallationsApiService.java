package com.bilalmoreno.malagasport.data.service.api;

import com.bilalmoreno.malagasport.data.service.model.installation.Installations;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InstallationsApiService {
    @GET("deportes/equipamientos/da_deportesBaloncesto-4326.geojson")
    Call<Installations> getBaloncesto();
}
