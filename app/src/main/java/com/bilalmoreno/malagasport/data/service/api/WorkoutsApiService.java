package com.bilalmoreno.malagasport.data.service.api;

import com.bilalmoreno.malagasport.data.service.model.workout.Workouts;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WorkoutsApiService {
    @GET("deportes/maquinasmusculacion/maquinasmusculacion-4326.geojson")
    Call<Workouts> getWorkouts();
}
