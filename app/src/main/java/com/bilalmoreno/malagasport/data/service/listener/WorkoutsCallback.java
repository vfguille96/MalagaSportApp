package com.bilalmoreno.malagasport.data.service.listener;

import android.os.AsyncTask;

import com.bilalmoreno.malagasport.data.db.model.Machine;
import com.bilalmoreno.malagasport.data.db.model.Workout;
import com.bilalmoreno.malagasport.data.repository.InstallationRepository;
import com.bilalmoreno.malagasport.data.repository.MachineRepository;
import com.bilalmoreno.malagasport.data.service.model.workout.Feature;
import com.bilalmoreno.malagasport.data.service.model.workout.Workouts;

import retrofit2.Call;
import retrofit2.Response;

public class WorkoutsCallback extends Callback implements retrofit2.Callback<Workouts> {
    private OnDownloadErrorListener listener;

    public WorkoutsCallback(OnDownloadErrorListener listener) {
        this.listener = listener;
    }

    @Override
    public void onResponse(Call<Workouts> call, final Response<Workouts> response) {
        AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                Workouts workouts = response.body();
                for (Feature feature :
                        workouts.getFeatures()) {
                    Workout workout = new Workout(Integer.valueOf(feature.getProperties().getIdZonamusculacion()),
                            feature.getProperties().getNomzonamusculacion(),
                            feature.getProperties().getUbicacion()
                    );
                    workout.setLatitude(feature.getGeometry().getCoordinates().get(1));
                    workout.setLongitude(feature.getGeometry().getCoordinates().get(0));

                    if (InstallationRepository.getInstance().getWorkout(workout.getId()) == null) {
                        InstallationRepository.getInstance().add(workout);
                    } else {
                        InstallationRepository.getInstance().update(workout);
                    }

                    Machine machine = new Machine(Integer.valueOf(feature.getProperties().getIdMaquina()),
                            feature.getProperties().getNommaquina(),
                            Integer.valueOf(feature.getProperties().getNivel()),
                            feature.getProperties().getFuncion(),
                            feature.getProperties().getDesarrollo(),
                            feature.getProperties().getPrecauciones()
                    );
                    machine.setWorkout(workout.getId());

                    if (MachineRepository.getInstance().get(machine.getId()) == null) {
                        MachineRepository.getInstance().add(machine);
                    } else {
                        MachineRepository.getInstance().update(machine);
                    }
                }
            return null;
            }
        } .execute();


    }

    @Override
    public void onFailure(Call<Workouts> call, Throwable t) {
        listener.onDownloadError();
    }
}
