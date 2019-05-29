package com.bilalmoreno.malagasport.data.service.api;

import com.bilalmoreno.malagasport.data.service.model.installation.Installations;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InstallationsApiService {
    @GET("deportes/equipamientos/da_deportesBaloncesto-4326.geojson")
    Call<Installations> getBaloncesto();

    @GET("deportes/equipamientos/da_deportesVoleybol-4326.geojson")
    Call<Installations> getVoley();

    @GET("deportes/equipamientos/da_deportesCentrosDeportivos-4326.geojson")
    Call<Installations> getCentrosDeportivos();

    @GET("deportes/equipamientos/da_deportesSenderismo-4326.geojson")
    Call<Installations> getSenderimos();

    @GET("deportes/equipamientos/da_deportesAjedrez-4326.geojson")
    Call<Installations> getAjedrez();

    @GET("deportes/equipamientos/da_deportesPetanca-4326.geojson")
    Call<Installations> getPetanca();

    @GET("deportes/equipamientos/da_deportesCircuitosMotor-4326.geojson")
    Call<Installations> getMotor();

    @GET("deportes/equipamientos/da_deportesPiscinas-4326.geojson")
    Call<Installations> getPiscinas();

    @GET("deportes/equipamientos/da_deportesTenis-4326.geojson")
    Call<Installations> getTenis();

    @GET("deportes/equipamientos/da_deportesVoleyPlaya-4326.geojson")
    Call<Installations> getVoleyPlaya();

    @GET("deportes/equipamientos/da_deportesPistasPadel-4326.geojson")
    Call<Installations> getPadel();

    @GET("deportes/equipamientos/da_deportesAtletismo-4326.geojson")
    Call<Installations> getAtletismo();

    @GET("deportes/equipamientos/da_deportesBadminton-4326.geojson")
    Call<Installations> getBadminton();

    @GET("deportes/equipamientos/da_deportesEscalada-4326.geojson")
    Call<Installations> getEscalada();

    @GET("deportes/equipamientos//da_deportesFutbol3Contra3-4326.geojson")
    Call<Installations> getFutbol3();

    @GET("deportes/equipamientos/da_deportesFutbolSala-4326.geojson")
    Call<Installations> getFutbolSala();

    @GET("deportes/equipamientos/da_deportesSalasDeportivas-4326.geojson")
    Call<Installations> getSalasDeportivas();

    @GET("deportes/equipamientos//da_deportesPatinajeSkateBmx-4326.geojson")
    Call<Installations> getSkateBMX();

    @GET("deportes/equipamientos/da_deportesAparatos-4326.geojson")
    Call<Installations> getAparatos();

    @GET("deportes/equipamientos/da_deportesPistasPolideportivas-4326.geojson")
    Call<Installations> getPistasPolideportivas();

    @GET("deportes/equipamientos/da_deportesTenisMesa-4326.geojson")
    Call<Installations> getTenisMesa();

    @GET("deportes/equipamientos/da_deportesFutbolPlaya-4326.geojson")
    Call<Installations> getFutbolPlaya();

    @GET("deportes/equipamientos//da_deportesFutbol-4326.geojson")
    Call<Installations> getFutbol();

    @GET("deportes/equipamientos/da_deportesPabellones-4326.geojson")
    Call<Installations> getPabellones();
}